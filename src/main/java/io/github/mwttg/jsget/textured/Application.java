package io.github.mwttg.jsget.textured;


import io.github.mwttg.jsget.EngineConfiguration;
import io.github.mwttg.jsget.EntityHelper;
import io.github.mwttg.sjge.graphics.window.GameWindow;

import io.github.mwttg.sjge.utilities.CleanUpUtilities;

import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException {
    final var configuration = EngineConfiguration.create();
    final var gameWindowId = GameWindow.create(configuration);
    EntityHelper.createDefaultCube(configuration); // This line I need because one VAO must be bind before creating a shader program ToDo!

    final var mainLoop = new MainLoop(configuration);

    mainLoop.loop(gameWindowId);

    CleanUpUtilities.purge();
  }
}
