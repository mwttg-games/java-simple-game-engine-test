package com.github.mwttg.jsget;

import com.github.mwttg.sjge.graphics.window.Configuration;
import com.github.mwttg.sjge.graphics.window.GameWindow;
import com.github.mwttg.sjge.graphics.window.OpenGlConfiguration;
import com.github.mwttg.sjge.logic.MainLoop;
import com.github.mwttg.sjge.utilities.CleanUpUtilities;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        final var openGlConfig = new OpenGlConfiguration(4, 1, true, false, true);
        final var configuration = new Configuration("JSGE Window", 800, 600, openGlConfig);

        final var gameWindowId = GameWindow.create(configuration);

        final var mainLoop = new MainLoop();
        mainLoop.loop(gameWindowId);

        CleanUpUtilities.purge();
    }
}
