package com.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.window.GameWindow;
import io.github.mwttg.sjge.logic.MainLoop;
import io.github.mwttg.sjge.utilities.CleanUpUtilities;

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
