package vd.util;

import java.awt.Point;

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

    public static boolean isPointInsideTriangle(Point point, Point vertex1, Point vertex2, Point vertex3)
    {
        float d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(point, vertex1, vertex2);
        d2 = sign(point, vertex2, vertex3);
        d3 = sign(point, vertex3, vertex1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }

    public static float sign(Point p1, Point p2, Point p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }
}