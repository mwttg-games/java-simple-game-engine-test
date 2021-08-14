package com.github.mwttg.jsget;

import com.github.mwttg.sjge.Entity;
import com.github.mwttg.sjge.graphics.draw.TextureLoader;
import com.github.mwttg.sjge.graphics.draw.VboDefinition;
import com.github.mwttg.sjge.graphics.draw.VertexArrayObject;
import com.github.mwttg.sjge.graphics.draw.VertexBufferObject;
import org.lwjgl.opengl.GL40;

import java.io.IOException;
import java.util.List;

public final class EntityHelper {

    private EntityHelper() {
    }

    public static Entity createDefaultPlane() {
        final var geometry = new float[] {
                1.0f, -1.0f, 0.0f,
                -1.0f, 1.0f, 0.0f,
                -1.0f, -1.0f, 0.0f,
                1.0f, -1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                -1.0f, 1.0f, 0.0f};
        final var uv = new float[] {
                1.0f, 0.0f,
                0.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f};
        try {
            final var geometryVboId = VertexBufferObject.create(geometry, GL40.GL_STATIC_DRAW);
            final var uvVboId = VertexBufferObject.create(uv, GL40.GL_STATIC_DRAW);
            final var definition = List.of(
                    new VboDefinition(geometryVboId, 3),
                    new VboDefinition(uvVboId, 2));
            final var vaoId = VertexArrayObject.create(definition);
            final var textureId = TextureLoader.createFrom("textures/simple.png");
            final var size = geometry.length / 3;
            final var entity = new Entity(vaoId, textureId, size);
            entity.moveTo(0,0, 0);
            return entity;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
