package vrd.util;

public class Vector3d
{
    public Vector3d(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void rotateY(float angle)
    {
        float old_x = this.x;
        float old_z = this.z;
        float sin = (float)Math.sin(angle);
        float cos = (float)Math.cos(angle);

        this.x = old_x * cos + old_z * sin;
        this.z = - old_x * sin + old_z * cos;
    }

    public void rotateX(float angle)
    {
        float old_y = this.y;
        float old_z = this.z;
        float sin = (float)Math.sin(angle);
        float cos = (float)Math.cos(angle);

        this.y = old_y * cos - old_z * sin;
        this.z = old_y * sin + old_z * cos;
    }

    public void rotateZ(float angle)
    {
        float old_x = this.x;
        float old_y = this.y;
        float sin = (float)Math.sin(angle);
        float cos = (float)Math.cos(angle);

        this.x = old_x * cos - old_y * sin;
        this.y = old_x * sin + old_y * cos;
    }

    public float x;
    public float y;
    public float z;

    public static Vector3d add(Vector3d left, Vector3d right)
    { return new Vector3d(left.x + right.x, left.y + right.y, left.z + right.z); }

    public static Vector3d subtract(Vector3d left, Vector3d right)
    { return new Vector3d(left.x - right.x, left.y - right.y, left.z - right.z); }
}