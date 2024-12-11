package vrd.util;

public class Util {
    // Returns a sum of arrays
    public static int[] arraySum(final int[] a, final int[] b)
    {
        if(a.length != b.length)
        { throw new IllegalArgumentException("Array lengths mismatch"); }

        int[] c = new int[a.length];

        // q: is there a java structured binding?
        for(int i = 0; i < c.length; ++i)
        {
            c[i] = a[i] + b[i];
        }

        return c;
    }
}