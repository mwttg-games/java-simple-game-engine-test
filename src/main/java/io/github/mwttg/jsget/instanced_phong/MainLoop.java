package io.github.mwttg.jsget.instanced_phong;

import io.github.mwttg.jsget.InstancedEntityHelper;
import io.github.mwttg.jsget.LightFactory;
import io.github.mwttg.sjge.configuration.Configuration;
import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedDrawables;
import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedMatrixStack;
import io.github.mwttg.sjge.graphics.draw.instanced.phong.PhongInstancedRendering;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

import java.util.ArrayList;
import java.util.Random;

public class MainLoop {
  private static final PhongInstancedRendering PHONG_INSTANCED_RENDERING = new PhongInstancedRendering();

  private final Configuration configuration;

  public MainLoop(final Configuration configuration) {
    this.configuration = configuration;
  }

  public void loop(final long gameWindowId) {
    var light = LightFactory.DEFAULT;
    var entities = createInstances();
    var rotation = 0.0f;

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      rotation = rotation + 0.01f;
      if (rotation > 360) rotation = 0.0f;
      entities = rotateCube(entities, rotation);

      PHONG_INSTANCED_RENDERING.draw(entities, light);

      GLFW.glfwSwapBuffers(gameWindowId);
      GLFW.glfwPollEvents();
    }
  }

  private InstancedDrawables rotateCube(final InstancedDrawables entities, final float rotation) {
    final var modelMatrices = entities.instancedMatrixStack().modelMatrices();

    final var modifiedModelMatrices = new ArrayList<Matrix4f>();
    for (final Matrix4f modelMatrix : modelMatrices) {
      final Vector3f original = new Vector3f();
      modelMatrix.getTranslation(original);
      final var modified = new Matrix4f().translate(original).rotateY(rotation).rotateZ(rotation);
      modifiedModelMatrices.add(modified);
    }
    final var newMatrixStack = new InstancedMatrixStack(modifiedModelMatrices, entities.instancedMatrixStack().viewMatrix(), entities.instancedMatrixStack().projectionMatrix());

    return new InstancedDrawables(entities.ids(), newMatrixStack, entities.material());
  }

  private InstancedDrawables createInstances() {
    final var min = -0.4f;
    final var max = 0.4f;
    final var random = new Random();
    final var modelMatrices = new ArrayList<Matrix4f>();
    for (int z = 0; z > -40; z--) {
      for (int x = -20; x <= 20; x++) {
        final var xRnd = (min + random.nextFloat() * (max - min));
        final var zRnd = (min + random.nextFloat() * (max - min));
        final var newX = 3.0f * x + xRnd;
        final var newZ = 3.0f * z + zRnd;
        final var modelMatrix = new Matrix4f().translate(newX, 0.0f, newZ);

        modelMatrices.add(modelMatrix);
      }
    }

    final var entities = InstancedEntityHelper.createSpherePikeFlat(configuration);
    final var matrixStack = new InstancedMatrixStack(modelMatrices, entities.instancedMatrixStack().viewMatrix(), entities.instancedMatrixStack().projectionMatrix());

    return new InstancedDrawables(entities.ids(), matrixStack, entities.material());
  }
}
