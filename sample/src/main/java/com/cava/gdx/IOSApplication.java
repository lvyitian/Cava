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
package com.cava.gdx;

import cava.apple.foundation.NSDictionary;
import cava.apple.uikit.UIApplication;
import cava.apple.uikit.UIApplicationDelegateAdapter;
import cava.apple.uikit.UIScreen;
import cava.apple.uikit.UIViewController;
import cava.apple.uikit.UIWindow;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;

/**
 *
 * @author mustafa
 */
public class IOSApplication implements Application {

    public static abstract class Delegate extends UIApplicationDelegateAdapter {

        private IOSApplication app;

        protected abstract IOSApplication createApplication();

        @Override
        public boolean didFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
            this.app = createApplication();
            return app.didFinishLaunching(application, launchOptions, this);
        }

        /*
        @Override
        public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
            application.addStrongRef(this); // Prevent this from being GCed until the ObjC UIApplication is deallocated
            this.app = createApplication();
            return app.didFinishLaunching(application, launchOptions);
        }

        @Override
        public void didBecomeActive(UIApplication application) {
            app.didBecomeActive(application);
        }

        @Override
        public void willEnterForeground(UIApplication application) {
            app.willEnterForeground(application);
        }

        @Override
        public void willResignActive(UIApplication application) {
            app.willResignActive(application);
        }

        @Override
        public void willTerminate(UIApplication application) {
            app.willTerminate(application);
        }*/
    }

    ApplicationListener listener;
    IOSGraphics graphics;
    UIWindow uiWindow;

    Array<Runnable> runnables = new Array<Runnable>();
    Array<Runnable> executedRunnables = new Array<Runnable>();
    Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();

    public IOSApplication(ApplicationListener listener) {
        this.listener = listener;
    }

    final boolean didFinishLaunching(UIApplication uiApp, NSDictionary options, Delegate delegate) {
        Gdx.app = this;
        Gdx.graphics = this.graphics = new IOSGraphics(this);
        Gdx.gl = Gdx.gl20 = graphics.gl20;
        Gdx.gl30 = graphics.gl30;

        uiWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        delegate.setWindow(uiWindow);
        uiWindow.setRootViewController(graphics.viewController);
        uiWindow.makeKeyAndVisible();

        graphics.makeCurrent();
        return true;
    }

    /**
     * Return the UI view controller of IOSApplication
     *
     * @return the view controller of IOSApplication
     */
    public UIViewController getUIViewController() {
        return graphics.viewController;
    }

    /**
     * Return the UI Window of IOSApplication
     *
     * @return the window
     */
    public UIWindow getUIWindow() {
        return uiWindow;
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return listener;
    }

    @Override
    public Graphics getGraphics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Audio getAudio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Input getInput() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Files getFiles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Net getNet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void log(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void log(String string, String string1, Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void error(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void error(String string, String string1, Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void debug(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void debug(String string, String string1, Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLogLevel(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLogLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setApplicationLogger(ApplicationLogger al) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApplicationType getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getJavaHeap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getNativeHeap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Preferences getPreferences(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clipboard getClipboard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postRunnable(Runnable runnable) {
        synchronized (runnables) {
            runnables.add(runnable);
            Gdx.graphics.requestRendering();
        }
    }

    public void processRunnables() {
        synchronized (runnables) {
            executedRunnables.clear();
            executedRunnables.addAll(runnables);
            runnables.clear();
        }
        for (int i = 0; i < executedRunnables.size; i++) {
            try {
                executedRunnables.get(i).run();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    
    @Override
    public void exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addLifecycleListener(LifecycleListener ll) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeLifecycleListener(LifecycleListener ll) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
