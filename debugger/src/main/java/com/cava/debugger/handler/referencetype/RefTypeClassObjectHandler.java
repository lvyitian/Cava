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

package com.cava.debugger.handler.referencetype;

import com.cava.debugger.JdwpConsts;
import com.cava.debugger.OutBuffer;
import com.cava.debugger.Packet;

/**
 *
 * @author mustafa
 */
public class RefTypeClassObjectHandler extends RefTypeHandler {

    @Override
    public int handle(Packet packet, OutBuffer out) {
        long refId = packet.readLong();
        out.writeLong(refId);
        return JdwpConsts.Error.NONE;
    }

    @Override
    public int getCommand() {
        return 11;
    }
    
}