package io.github.mwttg.jsget.phong;

import com.github.mwttg.sjge.Entity;
import com.github.mwttg.sjge.graphics.draw.TextureLoader;
import com.github.mwttg.sjge.graphics.draw.VboDefinition;
import com.github.mwttg.sjge.graphics.draw.VertexArrayObject;
import com.github.mwttg.sjge.graphics.draw.VertexBufferObject;
import io.github.mwttg.wavefront.WavefrontReader;
import org.lwjgl.opengl.GL40;

import java.io.IOException;
import java.util.List;

public final class EntityHelper {

  private EntityHelper() {
  }

  public static Entity createDefaultCube() {
    try {
      final var wavefront = WavefrontReader.fromResource("wavefronts/cube.obj");
      final var geometryVboId = VertexBufferObject.create(wavefront.vertices(), GL40.GL_STATIC_DRAW);
      final var uvVboId = VertexBufferObject.create(wavefront.textureCoordinates(), GL40.GL_STATIC_DRAW);
      final var normalVboId = VertexBufferObject.create(wavefront.normals(), GL40.GL_STATIC_DRAW);
      final var definition = List.of(
         new VboDefinition(geometryVboId, 3),
         new VboDefinition(uvVboId, 2),
         new VboDefinition(normalVboId, 3));
      final var vaoId = VertexArrayObject.create(definition);
      final var textureId = TextureLoader.createFrom("textures/Imphenzia.png");
      final var size = wavefront.vertices().length / 3;
      final var entity = new Entity(vaoId, textureId, size);
      entity.moveTo(0, 0, 0);
      return entity;
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
}
