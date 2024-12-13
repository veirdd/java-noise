package vrd.render.view;

import java.awt.Color;

import vrd.ui.std.Canvas;
import vrd.util.Content;

public class HeightMap1d extends View
{
    @Override
    public Signature getSignature()
    { return new Signature(ViewList.Id.HeightMap1d, "HeightMap1D", 1); }

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