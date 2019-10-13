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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mustafa
 */
public class AllocArray extends Code {
    final static long serialVersionUID = 0;
    
    public List<Code> sizes = new ArrayList();
    public String type;
    
    public AllocArray(String type) {
        this.type = type;
    }
    
    public AllocArray(Code size, String type) {
        sizes.add(size);
        this.type = type;
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.newArray(this);
        sizes.forEach(size -> size.visit(visitor));
        visitor.visitClassReference(type);
    }

    
    @Override
    public String toString() {
        return "newArray("+type+","+sizes+")";
    }
    
    
}