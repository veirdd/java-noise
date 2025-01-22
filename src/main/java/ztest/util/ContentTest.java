package ztest.util;

import java.util.Arrays;

import vd.util.Content;

public class ContentTest {

    public static void main(String[] args)
    {
        Content content = new Content(new int[]{10, 20, 40, 5});

        new Test("pass", () -> { return true; });

        new Test("getDimensionality", () ->
        {
            return content.getDimensionality() == 4;
        });
        
        new Test("set/get", () ->
        {
            content.set(new int[]{1, 2, 3, 4}, 5);
            return content.get(new int[]{1, 2, 3, 4}) == 5;
        });

        new Test("getSize", () ->
        {
            return content.getSize() == 10 * 20 * 40 * 5;
        });

        new Test("mapIndexToIndices", () ->
        {
            return Arrays.equals(content.mapIndexToIndices(3), new int[]{3, 0, 0, 0});
        });

        new Test("mapIndicesToIndex", () ->
        {
            return content.mapIndicesToIndex(new int[]{3, 0, 0, 0}) == 3;
        });
        
        new Test("combine", () ->
        {
            Content content_a = new Content(new int[]{5, 10, 5});
            Content content_b = new Content(new int[]{5, 10, 5});
            content_a.set(new int[]{2, 2, 2}, 3);
            content_b.set(new int[]{2, 2, 2}, 6);
    
            Content content_c = Content.combine(content_a, content_b, (a, b) ->
            { return a + b; });
    
            return content_c.get(new int[]{2, 2, 2}) == 9;
        });
    }
}
