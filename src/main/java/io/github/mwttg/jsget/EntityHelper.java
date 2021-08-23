package io.github.mwttg.jsget;

import io.github.mwttg.sjge.configuration.Configuration;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.graphics.entity.MatrixStack;
import io.github.mwttg.sjge.utilities.MatrixStackUtilities;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class EntityHelper {

  private EntityHelper() {
  }

  public static Drawable createSpherePikeSmooth(final Configuration configuration) {
    final var modelMatrix = new Matrix4f().translate(2, 0, 0);
    final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
    final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
    final var matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);

    return new Drawable(MeshFactory.SPHERE_PIKE_SMOOTH, matrixStack, MaterialFactory.DEFAULT);
  }

  public static Drawable createSpherePikeFlat(final Configuration configuration) {
    final var modelMatrix = new Matrix4f().translate(-2, 0, 0);
    final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
    final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
    final var matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);

    return new Drawable(MeshFactory.SPHERE_PIKE_FLAT, matrixStack, MaterialFactory.DEFAULT);
  }

  public static Drawable createDefaultCube(final Configuration configuration) {
    final var translate = new Vector3f(0, 0, 0);
    return createDefaultCube(configuration, translate);
  }

  public static Drawable createDefaultCube(final Configuration configuration, final Vector3f translate) {
    final var modelMatrix = new Matrix4f().translate(translate.x(), translate.y(), translate.z());
    final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
    final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
    final var matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);

    return new Drawable(MeshFactory.CUBE, matrixStack, MaterialFactory.DEFAULT);
  }

  public static Drawable createDefaultPlane(final Configuration configuration) {
    final var modelMatrix = new Matrix4f().translate(0, 0, 0);
    final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
    final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
    final var matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);

    return new Drawable(MeshFactory.PLANE, matrixStack, MaterialFactory.DEFAULT);
  }
}
