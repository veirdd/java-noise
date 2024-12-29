package vrd.util;

public class Util {
    // Returns a sum of arrays
    public static int[] arraySum(final int[] a, final int[] b)
    {
        if(a.length != b.length)
        { throw new IllegalArgumentException("Array lengths mismatch"); }

        int[] c = new int[a.length];

        for(int i = 0; i < c.length; ++i)
        {
            c[i] = a[i] + b[i];
        }

        return c;
    }

    // public static float interpolate(final float[] vertices, final float[] weight)
    // {
    //     if()

    //     if(vertices.length != Math.pow(2, weight.length))
    //     { throw new IllegalArgumentException("There must be 2^n vertices for n dimensions"); }

    //     for(int i = 0; i < weight.length; )
    // }
}