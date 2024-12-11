package vrd.render.view;

import vrd.ui.std.Canvas;
import vrd.util.Content;

public abstract class View
{
    public void render(Content content, Canvas canvas)
    {
        if(content.getDimensionality() != getDimensionality())
        {
            throw new IllegalArgumentException(
                "Dimensionality of content )" + content.getDimensionality() +
                ") does not match this view's input dimensionality (" + getDimensionality() + 
                ")");
        }

        algorithm(content, canvas);
    }
    
    public abstract int getDimensionality();
    public abstract int[] getRequiredContentSize(Canvas canvas);

    protected abstract void algorithm(Content content, Canvas canvas);
}