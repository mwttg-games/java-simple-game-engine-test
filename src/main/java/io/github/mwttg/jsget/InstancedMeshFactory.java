package io.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedIds;
import io.github.mwttg.wavefront.WavefrontReader;

import java.io.IOException;

public interface InstancedMeshFactory {

  InstancedIds INSTANCED_SPHERE_PIKE_SMOOTH = create("wavefronts/sphere-pike-smooth.obj", TextureFactory.LOW_POLY);

  private static InstancedIds create(final String filename, final int textureId) {
    try {
      final var wavefront = WavefrontReader.fromResource(filename);
      return InstancedIds.create(wavefront.vertices(), wavefront.textureCoordinates(), wavefront.normals(), textureId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
