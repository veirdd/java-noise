package vrd.render.view;

import java.awt.Color;

import vrd.ui.std.Canvas;
import vrd.util.Content;

public class Heightmap1d extends View
{
    @Override
    public int getDimensionality()
    { return 1; }

    @Override
    public int[] getRequiredContentSize(Canvas canvas)
    { return new int[]{canvas.getWidth()}; }

    @Override
    protected void algorithm(Content content, Canvas canvas)
    {
        for(int x = 0; x < canvas.getWidth(); ++x)
        for(int y = 0; y < canvas.getHeight(); ++y)
        {
            if(y <= content.get(new int[]{x}))
            { canvas.set(new int[]{x, y}, Color.white); }
        }
    }
}