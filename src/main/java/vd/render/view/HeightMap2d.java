package vd.render.view;

import java.awt.Color;

import vd.ui.std.Canvas;
import vd.util.Content;

public class HeightMap2d extends View
{
    @Override
    public Signature getSignature()
    { return new Signature(ViewList.Id.HeightMap2d, "HeightMap [2D]", 2); }

    @Override
    public int[] getRequiredContentSize(Canvas canvas)
    { return new int[]{canvas.getWidth(), canvas.getHeight()}; }

    @Override
    public void renderImpl(Content content, Canvas canvas)
    {
        float alpha;
        for(int x = 0; x < canvas.getWidth(); ++x)
        for(int y = 0; y < canvas.getHeight(); ++y)
        {
            alpha = Math.clamp(content.get(new int[]{x, y}) / 255, 0, 1);
            canvas.set(
                new int[]{x, y}, 
                new Color(alpha, alpha, alpha));
        }
    }
}