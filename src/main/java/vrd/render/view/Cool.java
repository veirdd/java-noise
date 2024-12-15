package vrd.render.view;

import java.awt.Color;

import vrd.ui.std.Canvas;
import vrd.util.Content;

public class Cool extends View
{
    @Override
    public Signature getSignature()
    { return new Signature(ViewList.Id.Cool, "Cool", 1); }

    @Override
    public int[] getRequiredContentSize(Canvas canvas)
    { return new int[]{canvas.getWidth()}; }

    @Override
    public void renderImpl(Content content, Canvas canvas)
    {
        final float smooth = 15;

        float alpha;
        for(int x = 0; x < canvas.getWidth(); ++x)
        for(int y = 0; y < canvas.getHeight(); ++y)
        {
            alpha = Math.clamp(content.get(new int[]{x}) / 255, 0, 1);
            canvas.set(
                new int[]{x, y}, 
                new Color(
                    alpha / ((float)(y + smooth) / smooth), 
                    alpha / ((float)(x + smooth) / smooth), 
                    alpha / ((float)(y + smooth) / smooth)));
        }
    }
}