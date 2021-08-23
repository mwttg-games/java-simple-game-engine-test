package io.github.mwttg.jsget.phong2;

import io.github.mwttg.jsget.EntityHelper;
import io.github.mwttg.jsget.LightFactory;
import io.github.mwttg.sjge.configuration.Configuration;
import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.draw.phong.PhongPipeline;
import io.github.mwttg.sjge.graphics.draw.textured.Draw;
import io.github.mwttg.sjge.graphics.draw.textured.TexturedPipeline;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.graphics.entity.MatrixStack;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private static final PhongPipeline PIPELINE = new PhongPipeline();

  private static final TexturedPipeline LIGHT_PIPELINE = new TexturedPipeline();

  private final Configuration configuration;

  public MainLoop(final Configuration configuration) {
    this.configuration = configuration;
  }

  public void loop(final long gameWindowId) {
    var light = LightFactory.DEFAULT;
    var lightEntity = EntityHelper.createDefaultCube(configuration, light.position());

    var entity1 = EntityHelper.createSpherePikeFlat(configuration);
    var entity2 = EntityHelper.createSpherePikeSmooth(configuration);

    var rotation = 0.0f;

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      rotation = rotation + 0.01f;
      if (rotation > 360) rotation = 0.0f;

      entity1 = rotate(entity1, rotation);
      entity2 = rotate(entity2, rotation);

      lightEntity = moveLightEntity(lightEntity, rotation);
      light = moveLight(light, rotation);

      PIPELINE.draw(entity1, light);
      PIPELINE.draw(entity2, light);
      LIGHT_PIPELINE.draw(lightEntity);

      GLFW.glfwSwapBuffers(gameWindowId);
      GLFW.glfwPollEvents();
    }
  }

  private Drawable rotate(final Drawable entity, final float rotation) {
    final Vector3f original = new Vector3f();
    entity.matrixStack().modelMatrix().getTranslation(original);
    final var modified = new Matrix4f().translate(original).rotateY(rotation).rotateZ(rotation);
    final var modifiedMatrixStack = new MatrixStack(modified, entity.matrixStack().viewMatrix(), entity.matrixStack().projectionMatrix());

    return new Drawable(entity.ids(), modifiedMatrixStack, entity.material());
  }

  private Drawable moveLightEntity(final Drawable light, final float rotation) {
    final var radius = 25.0f;
    final Vector3f translation = new Vector3f();
    light.matrixStack().modelMatrix().getTranslation(translation);

    final var newX = (float) (radius * Math.cos(rotation));
    final var newZ = (float) (radius * Math.sin(rotation));

    final Matrix4f modified = new Matrix4f().translate(newX, translation.y(), newZ);
    final var modifiedMatrixStack = new MatrixStack(modified, light.matrixStack().viewMatrix(), light.matrixStack().projectionMatrix());

    return new Drawable(light.ids(), modifiedMatrixStack, light.material());
  }

  private PointLight moveLight(final PointLight light, final float rotation) {
    final var radius = 25.0f;
    final var newX = (float) (radius * Math.cos(rotation));
    final var newZ = (float) (radius * Math.sin(rotation));

    return new PointLight(light.color(), new Vector3f(newX, light.position().y(), newZ), light.gamma());
  }
}
