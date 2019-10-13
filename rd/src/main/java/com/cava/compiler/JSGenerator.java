/*
 * Copyright (C) 2019 Digitoy Games.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cava.compiler;

import com.cava.compiler.model.Clazz;
import com.cava.compiler.model.Method;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mustafa
 */
public class JSGenerator extends Generator {
    Map<Object, Integer> names = new HashMap();
    public String nameFor(Object o) {
        return "_"+Integer.toHexString(names.computeIfAbsent(o, (k) -> names.size()));
    }

    @Override
    public void generate() throws Exception {
        super.generate(); 
        if(!globals.isEmpty()) {
            out.print("var ");
            int count = 0;
            for(int g : globals.values()) {
                if(count++ > 0) out.print(",");
                out.print("$g%d",g);
            }
            out.println(";");
        }
        
        if(!strings.isEmpty()) {
            out.print("var ");
            int count=0;
            for(Map.Entry<String,Integer> e : strings.entrySet()) {
                if(count++ > 0) out.print(",");
                out.print("$str%d", e.getValue());
            }
            out.println(";");
        }
        
        for(Clazz cls : classList) {
            defineClass(cls);
        }
        
        Method main = CompilerContext.getMainMethod();
        out.println("var vm={fp:0,frames:[{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0},{trap:0}]};")
            .println("var VM={};")
            .println("VM.allocObject=function(cls) {return {$c:cls};}")
            .println("vm.newArray=function(cls,len){return {$c:cls,$l:len,$a:[]};}")
            .println("vm.cast=function(o,c){return o;}");
        
        List<Method> initializers = new BootstrapSorter().process();
        for(Method m : initializers) {
            out.println("%s(vm);", nameFor(m));
        }
        out.ln();
        out.println("%s(vm);", nameFor(main));
        new FileOutputStream("index.js").write(out.toString().getBytes());
    }
    
    void defineClass(Clazz cls) {
        out.print("var cls%d={", getClassIndex(cls.name))
        .print("n:\"%s\"",cls.name)
        .print(",s:");
        if(cls.superName != null)
            out.print("cls%d", getClassIndex(cls.superName));
        else out.print("null");
        
        List<Method> virtualMethods = new ArrayList();
        Set<String> virtualMethodSignatures = new HashSet();
        int tableSize = 0;
        Clazz vc = cls;
        while(vc != null) {
            for(Method vm : vc.methods) {
                if(vm.usedInProject && vm.virtualBaseClass != null) {
                    String sign = vm.name+":"+vm.signature;
                    if(!virtualMethodSignatures.contains(sign)) {
                        virtualMethods.add(vm);
                        virtualMethodSignatures.add(sign);
                        tableSize = Math.max(tableSize, vm.virtualTableIndex+1);
                    }
                }
            }
            if(vc.superName == null) break;
            vc = CompilerContext.resolve(vc.superName);
        }
        
        if(cls.isInterface || virtualMethods.isEmpty())
            out.print(",$vt:cls%d.$vt", getClassIndex("java/lang/Object"));
        else {
            Method vms[] = new Method[tableSize];
            for(Method m : virtualMethods) vms[m.virtualTableIndex] = m;
            out.print(",$vt:[");
            for(int i=0; i<vms.length; i++) {
                if(i>0) out.print(",");
                Method vm = vms[i];
                if(vm == null || vm.isAbstract() || vm.isNative())
                    out.print("null");
                else
                out.print("%s",nameFor(vm));
            }
            out.print("]");
        }
        
        out.println("};");
        String name = cls.name;
        for(int i=0; i<6; i++) {
            String arrayName = "["+name;
            if(classIndex.containsKey(arrayName)) {
                defineArrayClass(name);
            } else break;
            name = arrayName;
        }
    }
    
    void defineArrayClass(String element) {
        int jlo = classIndex.get("java/lang/Object");
        out.print("var cls%d={", classIndex.get("["+element))
           .print("n:\"[%s\"",element)
           .print(",s:cls%d", jlo)
           .print(",e:cls%d", classIndex.get(element))
           .print(",$vt:cls%d.$vt",jlo)
           .print(",$it:cls%d.$it",jlo)
           .println("};");
    }
    
    @Override
    public void generateMethod(Method m) {
        out.println("//"+m.declaringClass+":"+m.name);
        out.print("function %s(vm", nameFor(m));
        for(int i=0; i<m.args.size(); i++) {
            out.print(",a"+i);
        }
        out.println("){");
        if(m.name.equals("<clinit>")) {
            int clsIndex = getClassIndex(m.declaringClass);
            out.println("if(cls%d.$ok) return; cls%d.$ok=1;", clsIndex, clsIndex);
            Set<Method> depends = new ClassInitInserter().process(m);
            if(depends != null && !depends.isEmpty()) {
                for(Method cm : depends) {
                    out.println("%s(vm);", nameFor(cm));
                }
            }
        }
        if(!m.locals.isEmpty()) {
            out.print("var ");
            for(int i=0; i<m.locals.size(); i++) {
                if(i>0) out.print(",");
                out.print("v%d",i);
            }
            out.println(";");
        }
        new JSMethodGenerator(this, m).generate();
        out.println("}");
    }

    
}