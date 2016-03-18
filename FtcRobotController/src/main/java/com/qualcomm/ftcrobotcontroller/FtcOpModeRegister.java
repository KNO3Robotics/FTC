/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller;

import android.app.Application;
import android.content.Context;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.ftcOpmodes.NullOp;
import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import dalvik.system.DexFile;

import java.util.Enumeration;

/**
 * Register Op Modes
 * @Author Jaxon Brown
 */
public class FtcOpModeRegister implements OpModeRegister {
    public static OpModeManager opModeManager;

    public void register(OpModeManager manager) {
        opModeManager = manager;

        registerKNO3OpModes();
        registerFtc();
    }

    private void register(String name, Class<? extends OpMode> opModeClass) {
        opModeManager.register(name, opModeClass);
    }

    private void registerKNO3OpModes() {
        String packageName = "com.qualcomm.ftcrobotcontroller.season";

        DbgLog.msg("--------ENTER REGISTER OP MODES-----------");

        try {
            Context context = (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication").invoke(null, null);
            DexFile dex = new DexFile(context.getPackageCodePath());
            Enumeration<String> dexEntries = dex.entries();

            while (dexEntries.hasMoreElements()) {
                //DbgLog.msg("TESTING DEX ENTRY");
                try {
                    String clazzName = dexEntries.nextElement();
                    //DbgLog.msg("CLASS NAME " + clazzName);

                    if (clazzName.startsWith(packageName)) {
                        //DbgLog.msg("DOES START WITH-------------------------");
                        Class<?> clazz = Class.forName(clazzName, true, context.getClassLoader());
                        if(OpMode.class.isAssignableFrom(clazz)) {
                            //DbgLog.msg("IS ASSIGNABLE FROM");
                            Class<? extends OpMode> opMode = (Class<? extends OpMode>) clazz;
                            if (opMode.isAnnotationPresent(Program.class)) {
                                //DbgLog.msg("HAS PROGRAM");
                                Program annotation = opMode.getAnnotation(Program.class);
                                try {
                                    ProgramData data = new ProgramData(clazzName, annotation);
                                    if (annotation.enabled()) {
                                        DbgLog.msg("REGISTERED ENTRY");
                                        register(data.gameName + " " + data.robotName + " " + data.robotVersion + " " +
                                                data.programName, opMode);
                                        continue;
                                    }
                                } catch(Exception ex) {
                                    ex.printStackTrace();
                                    if (annotation.enabled()) {
                                        DbgLog.msg("REGISTERED ENTRY");
                                        register("UnAugmented " + annotation.name(), opMode);
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    DbgLog.logStacktrace(ex);
                }
                //DbgLog.msg("DID NOT REGISTER ENTRY");
            }
        } catch (Exception ex) {
            DbgLog.logStacktrace(ex);
        }
        DbgLog.msg("---------EXIT REGISTER OP MODES--------------");
    }

    private void registerFtc() {
        register("NullOp", NullOp.class);
    }

    private class ProgramData {
        String gameName;
        String robotName;
        String robotVersion;
        String programName;

        public ProgramData(String className, Program annotation) {
            System.out.println(className);
            String[] components = className.replace("com.qualcomm.ftcrobotcontroller.season.", "").split("(\\.)");
            gameName = components[0];
            robotName = components[1];
            robotVersion = components[2];
            programName = annotation.name();
        }
    }
}
