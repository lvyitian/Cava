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

package com.cava.compiler.code;

import soot.jimple.ParameterRef;

/**
 *
 * @author mustafa
 */
public class Var extends Code {
    final static long serialVersionUID = 0;
    
    public String name;
    public int index;
    public String type;
    public boolean isParam;
    
    public Var(String name, int index, String type, boolean isParam) {
        this.name = name;
        this.index = index;
        this.type = type;
        this.isParam = isParam;
    }
    
    public Var(soot.Local local) {
        this(local.getName(), local.getIndex(), com.cava.compiler.SootClassLoader.toJavaType(local.getType()), false);
    }

    public Var(ParameterRef param) {
        this("param"+param.getIndex(), param.getIndex(), com.cava.compiler.SootClassLoader.toJavaType(param.getType()), true);
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visitClassReference(type);
    }
    
    
    @Override
    public String toString() {
        return name;
    }
    
    
}