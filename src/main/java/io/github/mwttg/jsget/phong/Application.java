package io.github.mwttg.jsget.phong;

import com.github.mwttg.sjge.graphics.window.GameWindow;
import com.github.mwttg.sjge.utilities.CleanUpUtilities;
import io.github.mwttg.jsget.EngineConfiguration;

import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException {
    final var config = EngineConfiguration.create();
    final var gameWindowId = GameWindow.create(config);
    final var mainLoop = new MainLoop(config);

    mainLoop.loop(gameWindowId);

    CleanUpUtilities.purge();
  }
}
