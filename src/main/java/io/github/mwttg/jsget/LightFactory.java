package io.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import org.joml.Vector3f;

public interface LightFactory {

  PointLight DEFAULT = createDefault();

  private static PointLight createDefault() {
    final var color = new Vector3f(1.0f, 1.0f, 1.0f);
    final var position = new Vector3f(5.0f, 0.0f, 5.0f);
    final var gamma = 1.2f;

    return new PointLight(color, position, gamma);
  }
}
