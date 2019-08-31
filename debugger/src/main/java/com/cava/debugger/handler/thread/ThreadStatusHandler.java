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

package com.cava.debugger.handler.thread;

import cava.platform.NativeCode;
import com.cava.debugger.JdwpConsts;
import com.cava.debugger.OutBuffer;
import com.cava.debugger.Packet;
import com.cava.debugger.VM;

/**
 *
 * @author mustafa
 */
public class ThreadStatusHandler extends ThreadHandler {

    @Override
    public int handle(Packet packet, OutBuffer out) {
        long threadId = packet.readLong();
        Thread[] threads = VM.getAllThreads();
        if(threads != null) {
            for(Thread t : threads)
                if(t != null && t.getId() == threadId) {
                    //always running 
                    out.writeInt(JdwpConsts.ThreadStatus.RUNNING);
                    int suspendCount = NativeCode.Int("((JvmThread*)%s)->suspendCount", t);
                    out.writeInt(suspendCount > 0 ? JdwpConsts.SuspendStatus.SUSPEND_STATUS_SUSPENDED : 0); 
                    return JdwpConsts.Error.NONE;
                }
        }
           
        System.out.println("!!Thread not found - StatusHandler !!!");
        return JdwpConsts.Error.INVALID_THREAD;
    }

    @Override
    public int getCommand() {
        return 4;
    }
    
}
