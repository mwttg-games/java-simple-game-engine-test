package io.github.mwttg.jsget.phong;

import com.github.mwttg.sjge.configuration.Configuration;
import com.github.mwttg.sjge.graphics.draw.MatrixStack;
import com.github.mwttg.sjge.graphics.draw.phong.Material;
import com.github.mwttg.sjge.graphics.draw.phong.PhongPipeline;
import com.github.mwttg.sjge.graphics.draw.phong.PointLight;
import com.github.mwttg.sjge.utilities.MatrixStackUtilities;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final PhongPipeline pipeline;
  private final Material material;
  private MatrixStack matrixStack;
  private PointLight light;

  private Vector3f color;

  public MainLoop(final Configuration configuration) {
    final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
    final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
    final var modelMatrix = new Matrix4f().translate(0, 0, 0);
    this.matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);

    color = new Vector3f(1.0f, 1.0f, 1.0f);
    final var position = new Vector3f(5.0f, 10.0f, 5.0f);
    final var gamma = 1.2f;
    this.light = new PointLight(color, position, gamma);

    final var ambient = new Vector3f(0.2f, 0.2f, 0.2f);
    final var diffuse = new Vector3f(1.0f, 1.0f, 1.0f);
    final var specular = new Vector3f(1.0f, 1.0f, 1.0f);
    final var exponent = 1.2f;
    this.material = new Material(ambient, diffuse, specular, exponent);

    final var entity = EntityHelper.createDefaultCube();

    this.pipeline = new PhongPipeline();
    pipeline.addEntity(entity);
  }

  public void loop(final long gameWindowId) {
    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      pipeline.draw(matrixStack, light, material);

      rotateCube();
      modifyLight();

      GLFW.glfwSwapBuffers(gameWindowId);
      GLFW.glfwPollEvents();
    }
  }

  private void rotateCube() {
    final var modelMatrix = this.matrixStack.modelMatrix();
    final var modified = new Matrix4f(modelMatrix).translate(0.0f, 0.0f, 0.0f).rotateX(0.05f).rotateY(0.05f);
    this.matrixStack = new MatrixStack(modified, this.matrixStack.viewMatrix(), this.matrixStack.projectionMatrix());
  }

  private void modifyLight() {
    var  c = light.color().x;
    if (c < 0.1f) {
      c = 1.0f;
    } else  {
      c = c - 0.01f;
    }

    final var modified = new Vector3f(c, c, c);
    this.light = new PointLight(modified, light.position(), light.gamma());
  }
}
