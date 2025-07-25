package com.tony.sandbloast;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import kw.sanbloast.SandBlost;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title="Sand bloast";
        config.x = 1000;
        config.y = 0;
        config.width = (int) (360*1.5f);
        config.height = (int) (640*1.5f);
        new LwjglApplication(new SandBlost(),config);

    }
}
