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

package cava.c;

import cava.annotation.Include;
import cava.annotation.Inline;
import cava.platform.NativeCode;

/**
 *
 * @author mustafa
 */
@Include("<stdlib.h> <stdio.h> <string.h>")
public class CLib {
    
    public final static int BYTE_SIZE = NativeCode.Int("sizeof(JBYTE)");
    public final static int CHAR_SIZE = NativeCode.Int("sizeof(JCHAR)");
    public final static int SHORT_SIZE = NativeCode.Int("sizeof(JSHORT)");
    public final static int BOOL_SIZE = NativeCode.Int("sizeof(JBOOL)");
    public final static int INT_SIZE = NativeCode.Int("sizeof(JINT)");
    public final static int FLOAT_SIZE = NativeCode.Int("sizeof(JFLOAT)");
    public final static int LONG_SIZE = NativeCode.Int("sizeof(JLONG)");
    public final static int DOUBLE_SIZE = NativeCode.Int("sizeof(JDOUBLE)");
    
    @Inline public static VoidPtr malloc(int size) {
        return NativeCode.VoidPtr("malloc(%s)", size);
    }
    @Inline public static void free(Object ptr) {
        NativeCode.Void("free(%s)", ptr);
    }
    
    @Inline public static void memcpy(Object dst, int dstOffset, Object src, int srcOffset, int sizeInBytes) {
        NativeCode.Void("memcpy(((char*)%s)+%s, ((char*)%s)+%s, %s)", dst, dstOffset, src, srcOffset, sizeInBytes);
    }
    @Inline public static void memmove(Object dst, int dstOffset, Object src, int srcOffset, int sizeInBytes) {
        NativeCode.Void("memmove(((char*)%s)+%s, ((char*)%s)+%s, %s)", dst, dstOffset, src, srcOffset, sizeInBytes);
    }
    @Inline public static int strlen(Object str) {
        return NativeCode.Int("strlen(%s)", str);
    } 
    
    
}
