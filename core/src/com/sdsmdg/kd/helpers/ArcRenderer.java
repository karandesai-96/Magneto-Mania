package com.sdsmdg.kd.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Haresh Khanna
 */
public class ArcRenderer {
    public enum ShapeType {
        Point(GL20.GL_POINTS), Line(GL20.GL_LINES), Filled(GL20.GL_TRIANGLES);

        private final int glType;

        ShapeType(int glType) {
            this.glType = glType;
        }

        public int getGlType() {
            return glType;
        }
    }

    private final ImmediateModeRenderer renderer;
    private boolean matrixDirty = false;
    private final Matrix4 projectionMatrix = new Matrix4();
    private final Matrix4 transformMatrix = new Matrix4();
    private final Matrix4 combinedMatrix = new Matrix4();
    private final Vector2 tmp = new Vector2();
    private final Color color = new Color(1, 1, 1, 1);
    private ShapeType shapeType;
    private boolean autoShapeType;
    private float defaultRectLineWidth = 0.75f;

    public ArcRenderer() {
        this(5000);
    }

    public ArcRenderer(int maxVertices) {
        this(maxVertices, null);
    }

    public ArcRenderer(int maxVertices, ShaderProgram defaultShader) {
        if (defaultShader == null) {
            renderer = new ImmediateModeRenderer20(maxVertices, false, true, 0);
        } else {
            renderer = new ImmediateModeRenderer20(maxVertices, false, true, 0, defaultShader);
        }
        projectionMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        matrixDirty = true;
    }

    /**
     * Sets the color to be used by the next shapes drawn.
     */
    public void setColor(Color color) {
        this.color.set(color);
    }

    /**
     * Sets the color to be used by the next shapes drawn.
     */
    public void setColor(float r, float g, float b, float a) {
        this.color.set(r, g, b, a);
    }

    public Color getColor() {
        return color;
    }

    public void updateMatrices() {
        matrixDirty = true;
    }

    /**
     * Sets the projection matrix to be used for rendering. Usually this will be set to {@link Camera#combined}.
     *
     * @param matrix
     */
    public void setProjectionMatrix(Matrix4 matrix) {
        projectionMatrix.set(matrix);
        matrixDirty = true;
    }

    /**
     * If the matrix is modified, {@link #updateMatrices()} must be called.
     */
    public Matrix4 getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setTransformMatrix(Matrix4 matrix) {
        transformMatrix.set(matrix);
        matrixDirty = true;
    }

    /**
     * If the matrix is modified, {@link #updateMatrices()} must be called.
     */
    public Matrix4 getTransformMatrix() {
        return transformMatrix;
    }

    /**
     * Sets the transformation matrix to identity.
     */
    public void identity() {
        transformMatrix.idt();
        matrixDirty = true;
    }

    /**
     * Multiplies the current transformation matrix by a translation matrix.
     */
    public void translate(float x, float y, float z) {
        transformMatrix.translate(x, y, z);
        matrixDirty = true;
    }

    /**
     * Multiplies the current transformation matrix by a rotation matrix.
     */
    public void rotate(float axisX, float axisY, float axisZ, float degrees) {
        transformMatrix.rotate(axisX, axisY, axisZ, degrees);
        matrixDirty = true;
    }

    /**
     * Multiplies the current transformation matrix by a scale matrix.
     */
    public void scale(float scaleX, float scaleY, float scaleZ) {
        transformMatrix.scale(scaleX, scaleY, scaleZ);
        matrixDirty = true;
    }

    /**
     * If true, when drawing a shape cannot be performed with the current shape type, the batch is flushed and the shape type is
     * changed automatically. This can increase the number of batch flushes if care is not taken to draw the same type of shapes
     * together. Default is false.
     */
    public void setAutoShapeType(boolean autoShapeType) {
        this.autoShapeType = autoShapeType;
    }

    /**
     * Begins a new batch without specifying a shape type.
     *
     * @throws IllegalStateException if {@link #autoShapeType} is false.
     */
    public void begin() {
        if (!autoShapeType)
            throw new IllegalStateException("autoShapeType must be true to use this method.");
        begin(ShapeType.Line);
    }

    /**
     * Starts a new batch of shapes. Shapes drawn within the batch will attempt to use the type specified. The call to this method
     * must be paired with a call to {@link #end()}.
     *
     * @see #setAutoShapeType(boolean)
     */
    public void begin(ShapeType type) {
        if (shapeType != null)
            throw new IllegalStateException("Call end() before beginning a new shape batch.");
        shapeType = type;
        if (matrixDirty) {
            combinedMatrix.set(projectionMatrix);
            Matrix4.mul(combinedMatrix.val, transformMatrix.val);
            matrixDirty = false;
        }
        renderer.begin(combinedMatrix, shapeType.getGlType());
    }

    public void set(ShapeType type) {
        if (shapeType == type) return;
        if (shapeType == null) throw new IllegalStateException("begin must be called first.");
        if (!autoShapeType) throw new IllegalStateException("autoShapeType must be enabled.");
        end();
        begin(type);
    }

    public void arc(float x, float y, float radius, float start, float degrees) {
        arc(x, y, radius, start, degrees, Math.max(1, (int) (6 * (float) Math.cbrt(radius) * (degrees / 360.0f))));
    }

    public void arc(float x, float y, float radius, float start, float degrees, int segments) {
        if (segments <= 0) throw new IllegalArgumentException("segments must be > 0.");
        float colorBits = color.toFloatBits();
        float theta = (2 * MathUtils.PI * (degrees / 360.0f)) / segments;
        float cos = MathUtils.cos(theta);
        float sin = MathUtils.sin(theta);
        float cx = radius * MathUtils.cos(start * MathUtils.degreesToRadians);
        float cy = radius * MathUtils.sin(start * MathUtils.degreesToRadians);

        if (shapeType == ShapeType.Line) {
            check(ShapeType.Line, ShapeType.Filled, segments * 2 + 2);

            renderer.color(colorBits);
            for (int i = 0; i < segments; i++) {
                renderer.color(colorBits);
                renderer.vertex(x + cx, y + cy, 0);
                float temp = cx;
                cx = cos * cx - sin * cy;
                cy = sin * temp + cos * cy;
                renderer.color(colorBits);
                renderer.vertex(x + cx, y + cy, 0);
            }
            renderer.color(colorBits);
        } else {
            check(ShapeType.Line, ShapeType.Filled, segments * 3 + 3);

            for (int i = 0; i < segments; i++) {
                renderer.color(colorBits);
                renderer.vertex(x, y, 0);
                renderer.color(colorBits);
                renderer.vertex(x + cx, y + cy, 0);
                float temp = cx;
                cx = cos * cx - sin * cy;
                cy = sin * temp + cos * cy;
                renderer.color(colorBits);
                renderer.vertex(x + cx, y + cy, 0);
            }
            renderer.color(colorBits);
            renderer.vertex(x, y, 0);
            renderer.color(colorBits);
            renderer.vertex(x + cx, y + cy, 0);
        }

        float temp = cx;
        cx = 0;
        cy = 0;
        renderer.color(colorBits);
        renderer.vertex(x + cx, y + cy, 0);
    }

    private void check (ShapeType preferred, ShapeType other, int newVertices) {
        if (shapeType == null) throw new IllegalStateException("begin must be called first.");

        if (shapeType != preferred && shapeType != other) {
            // Shape type is not valid.
            if (!autoShapeType) {
                if (other == null)
                    throw new IllegalStateException("Must call begin(ShapeType." + preferred + ").");
                else
                    throw new IllegalStateException("Must call begin(ShapeType." + preferred + ") or begin(ShapeType." + other + ").");
            }
            end();
            begin(preferred);
        } else if (matrixDirty) {
            // Matrix has been changed.
            ShapeType type = shapeType;
            end();
            begin(type);
        } else if (renderer.getMaxVertices() - renderer.getNumVertices() < newVertices) {
            // Not enough space.
            ShapeType type = shapeType;
            end();
            begin(type);
        }
    }

    /** Finishes the batch of shapes and ensures they get rendered. */
    public void end () {
        renderer.end();
        shapeType = null;
    }

    public void flush () {
        ShapeType type = shapeType;
        end();
        begin(type);
    }
}
