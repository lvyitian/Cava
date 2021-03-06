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

package cava.apple.opengles;

import cava.annotation.Framework;
import cava.annotation.Include;
import cava.apple.foundation.NSString;
import cava.c.VoidPtr;
import cava.platform.NativeCode;

/**
 *
 * @author mustafa
 */
@Include("<OpenGLES/EAGLDrawable.h>")
@Framework("OpenGLES.framework")
public enum EAGLColorFormat {
    RGBA8(NativeCode.VoidPtr("kEAGLColorFormatRGBA8")),
    RGB565(NativeCode.VoidPtr("kEAGLColorFormatRGB565"))
    ;

    VoidPtr value;
    EAGLColorFormat(VoidPtr value) { this.value = value; }
    
    public NSString value() {
        return new NSString(value, true);
    }
}
