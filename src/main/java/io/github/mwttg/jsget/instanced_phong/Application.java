package io.github.mwttg.jsget.instanced_phong;

import io.github.mwttg.jsget.EngineConfiguration;
import io.github.mwttg.jsget.EntityHelper;
import io.github.mwttg.sjge.graphics.window.GameWindow;
import io.github.mwttg.sjge.utilities.CleanUpUtilities;

import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException {
    final var config = EngineConfiguration.create();
    final var gameWindowId = GameWindow.create(config);
    EntityHelper.createDefaultCube(config); // This line I need because one VAO must be bind before creating a shader program ToDo!
    final var mainLoop = new MainLoop(config);

    mainLoop.loop(gameWindowId);

    CleanUpUtilities.purge();
  }
}
