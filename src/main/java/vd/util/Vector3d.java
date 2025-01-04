package vd.util;

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

    public Vector3d getUnitVector()
    {
        if(this.x == 0 & this.y == 0 & this.z == 0)
        { throw new IllegalStateException("Cannot get unit vector of a null vector"); }

        float length = (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vector3d(this.x / length, this.y / length, this.z / length);
    }

    public float x;
    public float y;
    public float z;

    public static Vector3d add(Vector3d left, Vector3d right)
    { return new Vector3d(left.x + right.x, left.y + right.y, left.z + right.z); }

    public static Vector3d subtract(Vector3d left, Vector3d right)
    { return new Vector3d(left.x - right.x, left.y - right.y, left.z - right.z); }

    public static Vector3d crossProduct(Vector3d left, Vector3d right)
    {
        return new Vector3d(
            left.y * right.z - left.z * right.y,
            left.z * right.x - left.x * right.z,
            left.x * right.y - left.y * right.x);
    }

    public static float dotProduct(Vector3d left, Vector3d right)
    { return left.x * right.x + left.y * right.y + left.z * right.z; }
}