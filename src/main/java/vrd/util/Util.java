package vrd.util;

public class Util {
    public static int[] add(int[] a, int[] b)
    {
        assert a.length == b.length;

        // is there a java structured binding?
        for(int i = 0; i < a.length; ++i)
        {
            a[i] += b[i];
        }

        return a;
    }
}