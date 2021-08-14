package com.github.mwttg.jsget;

import com.github.mwttg.sjge.graphics.window.GameWindow;
import com.github.mwttg.sjge.logic.MainLoop;
import com.github.mwttg.sjge.utilities.CleanUpUtilities;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        final var configuration = EngineConfiguration.create();
        final var gameWindowId = GameWindow.create(configuration);
        final var entity = EntityHelper.createDefaultPlane();
        final var mainLoop = new MainLoop(configuration);
        mainLoop.addEntity(entity);

        mainLoop.loop(gameWindowId);

        CleanUpUtilities.purge();
    }
}
