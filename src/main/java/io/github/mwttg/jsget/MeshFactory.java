package io.github.mwttg.jsget;

import io.github.mwttg.sjge.graphics.draw.VboDefinition;
import io.github.mwttg.sjge.graphics.draw.VertexArrayObject;
import io.github.mwttg.sjge.graphics.draw.VertexBufferObject;
import io.github.mwttg.sjge.graphics.entity.Ids;
import io.github.mwttg.wavefront.WavefrontReader;
import io.github.mwttg.wavefront.transformer.Wavefront;
import org.lwjgl.opengl.GL40;

import java.io.IOException;
import java.util.List;

public interface MeshFactory {

  Ids PLANE = create("wavefronts/plane.obj", TextureFactory.SIMPLE);
  Ids CUBE = create("wavefronts/cube.obj", TextureFactory.LOW_POLY);

  private static Ids create(final String filename, final int textureId) {
    try {
      final var wavefront = WavefrontReader.fromResource(filename);
      final var vboDefinitions = createVboDefinition(wavefront);
      final var vaoId = VertexArrayObject.create(vboDefinitions);
      final var size = wavefront.vertices().length / 3;
      return new Ids(vaoId, textureId, size);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static List<VboDefinition> createVboDefinition(final Wavefront wavefront) {
    final var geometryVboId = VertexBufferObject.create(wavefront.vertices(), GL40.GL_STATIC_DRAW);
    final var uvVboId = VertexBufferObject.create(wavefront.textureCoordinates(), GL40.GL_STATIC_DRAW);
    final int normalVboId;
    if (wavefront.normals() != null) {
      normalVboId = VertexBufferObject.create(wavefront.normals(), GL40.GL_STATIC_DRAW);
      return List.of(
         new VboDefinition(geometryVboId, 3),
         new VboDefinition(uvVboId, 2),
         new VboDefinition(normalVboId, 3));
    } else {
      return List.of(
         new VboDefinition(geometryVboId, 3),
         new VboDefinition(uvVboId, 2));
    }
  }
}
