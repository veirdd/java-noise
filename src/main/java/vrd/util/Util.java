package vrd.util;

public class Util {
    // Returns a sum of arrays
    public static int[] arraySum(final int[] a, final int[] b)
    {
        assert a.length == b.length;

        int[] c = new int[a.length];

        // q: is there a java structured binding?
        for(int i = 0; i < c.length; ++i)
        {
            c[i] = a[i] + b[i];
        }

        return c;
    }
}