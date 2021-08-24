package io.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.draw.material.Material;
import org.joml.Vector3f;

public interface MaterialFactory {

  Material DEFAULT = createDefault();

  private static Material createDefault() {
    final var ambient = new Vector3f(0.2f, 0.2f, 0.2f);
    final var diffuse = new Vector3f(1.0f, 1.0f, 1.0f);
    final var specular = new Vector3f(1.0f, 1.0f, 1.0f);
    final var exponent = 1.2f;
    return new Material(ambient, diffuse, specular, exponent);
  }
}
