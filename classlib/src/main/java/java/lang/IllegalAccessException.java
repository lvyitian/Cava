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

package java.lang;
/**
 * Thrown when an application tries to load in a class, but the currently executing method does not have access to the definition of the specified class, because the class is not public and in another package.
 * An instance of this class can also be thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the current method does not have access to the appropriate zero-argument constructor.
 * Since: JDK1.0, CLDC 1.0 See Also:Class.forName(java.lang.String), Class.newInstance()
 */
public class IllegalAccessException extends java.lang.Exception{
    /**
     * Constructs an IllegalAccessException without a detail message.
     */
    public IllegalAccessException(){
    }

    /**
     * Constructs an IllegalAccessException with a detail message.
     * s - the detail message.
     */
    public IllegalAccessException(java.lang.String s){
         super(s);
    }

}
