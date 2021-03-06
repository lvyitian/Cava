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
 * Thrown when a thread is waiting, sleeping, or otherwise paused for a long time and another thread interrupts it.
 * Since: JDK1.0, CLDC 1.0 See Also:Object.wait(), Object.wait(long), Object.wait(long, int), Thread.sleep(long)
 */
public class InterruptedException extends java.lang.Exception{
    /**
     * Constructs an InterruptedException with no detail message.
     */
    public InterruptedException(){
    }

    /**
     * Constructs an InterruptedException with the specified detail message.
     * s - the detail message.
     */
    public InterruptedException(java.lang.String s){
         super(s);
    }

}
