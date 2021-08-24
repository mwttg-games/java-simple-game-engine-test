package io.github.mwttg.jsget;

import io.github.mwttg.sjge.configuration.Configuration;
import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedDrawables;
import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedMatrixStack;
import io.github.mwttg.sjge.utilities.MatrixStackUtilities;
import org.joml.Matrix4f;

import java.util.ArrayList;

public final class InstancedEntityHelper {

  private InstancedEntityHelper() {
  }

  public static InstancedDrawables createSpherePikeFlat(final Configuration configuration) {
    final var modelMatrices = new ArrayList<Matrix4f>();
    final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
    final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
    final var matrixStack = new InstancedMatrixStack(modelMatrices, viewMatrix, projectionMatrix);

    return new InstancedDrawables(InstancedMeshFactory.INSTANCED_SPHERE_PIKE_SMOOTH, matrixStack, MaterialFactory.DEFAULT);
  }
}
