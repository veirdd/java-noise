package vd.render.view;

import java.awt.Color;

import vd.ui.std.Canvas;
import vd.util.Content;

public class HeightMap1d extends View
{
    @Override
    public Signature getSignature()
    { return new Signature(ViewList.Id.HeightMap1d, "HeightMap [1D]", 1); }

    @Override
    public int[] getRequiredContentSize(Canvas canvas)
    { return new int[]{canvas.getWidth()}; }

    @Override
    protected void renderImpl(Content content, Canvas canvas)
    {
        for(int x = 0; x < canvas.getWidth(); ++x)
        for(int y = 0; y < canvas.getHeight(); ++y)
        {
            if(y <= content.get(new int[]{x}))
            { canvas.set(new int[]{x, y}, Color.white); }
        }
    }
}