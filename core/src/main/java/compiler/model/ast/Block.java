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

package compiler.model.ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mustafa
 */
public class Block extends Code {

    final static long serialVersionUID = 0;
    
    public List<Code> children = new ArrayList();

    @Override
    public void replaceClassName(String src, String dest) {
        super.replaceClassName(src, dest); 
        children.forEach(c -> c.replaceClassName(src, dest));
    }

    @Override
    public void visit(Visitor v) {
        v.block(this);
        children.forEach(c -> c.visit(v));
        v.endBlock(this);
    }
    
    
    
}
