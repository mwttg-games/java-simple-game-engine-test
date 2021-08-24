package io.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.draw.single.entity.Ids;
import io.github.mwttg.wavefront.WavefrontReader;

import java.io.IOException;

public interface MeshFactory {

  Ids PLANE = create("wavefronts/plane.obj", TextureFactory.SIMPLE);
  Ids CUBE = create("wavefronts/cube.obj", TextureFactory.LOW_POLY);
  Ids SPHERE_PIKE_FLAT = create("wavefronts/sphere-pike-flat.obj", TextureFactory.LOW_POLY);
  Ids SPHERE_PIKE_SMOOTH = create("wavefronts/sphere-pike-smooth.obj", TextureFactory.LOW_POLY);

  private static Ids create(final String filename, final int textureId) {
    try {
      final var wavefront = WavefrontReader.fromResource(filename);
      return Ids.create(wavefront.vertices(), wavefront.textureCoordinates(), wavefront.normals(), textureId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
