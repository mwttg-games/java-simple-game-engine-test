package io.github.mwttg.jsget.phong;

import io.github.mwttg.sjge.configuration.Configuration;
import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.draw.phong.PhongPipeline;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.graphics.entity.MatrixStack;
import io.github.mwttg.jsget.EntityHelper;
import io.github.mwttg.jsget.LightFactory;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private static final PhongPipeline PIPELINE = new PhongPipeline();

  private final Configuration configuration;

  public MainLoop(final Configuration configuration) {
    this.configuration = configuration;
  }

  public void loop(final long gameWindowId) {
    var light = LightFactory.DEFAULT;
    var entity = EntityHelper.createDefaultCube(configuration);
    PIPELINE.addEntity(entity);

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      entity = rotateCube(entity);
      light = modifyLight(light);

      PIPELINE.draw(light);

      GLFW.glfwSwapBuffers(gameWindowId);
      GLFW.glfwPollEvents();
    }
  }

  private Drawable rotateCube(final Drawable entity) {
    final var modified = entity.matrixStack().modelMatrix().translate(0.0f, 0.0f, 0.0f).rotateY(0.05f).rotateZ(0.05f);
    final var modifiedMatrixStack = new MatrixStack(modified, entity.matrixStack().viewMatrix(), entity.matrixStack().projectionMatrix());

    return new Drawable(entity.ids(), modifiedMatrixStack, entity.material());
  }

  private PointLight modifyLight(final PointLight light) {
    var c = light.color().x;
    if (c < 0.1f) {
      c = 1.0f;
    } else {
      c = c - 0.01f;
    }

    final var modified = new Vector3f(c, c, c);
    return new PointLight(modified, light.position(), light.gamma());
  }
}
