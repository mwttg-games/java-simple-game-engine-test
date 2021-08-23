package io.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.draw.TextureLoader;

public interface TextureFactory {

  int SIMPLE = TextureLoader.createFrom("textures/simple.png");
  int LOW_POLY = TextureLoader.createFrom("textures/Imphenzia.png");
}
