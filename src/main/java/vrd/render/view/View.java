package vrd.render.view;

import vrd.ui.std.Canvas;
import vrd.util.Content;

public abstract class View
{
    public void render(Content content, Canvas canvas)
    {
        // 0 for input universality
        if(content.getDimensionality() != getSignature().input_dimensionality ||
           getSignature().input_dimensionality == 0)
        {
            throw new IllegalArgumentException(
                "Dimensionality of content (" + content.getDimensionality() +
                ") does not match this view's input dimensionality (" + getSignature().input_dimensionality + 
                ")");
        }

        renderImpl(content, canvas);
    }

    public abstract Signature getSignature();
    public abstract int[] getRequiredContentSize(Canvas canvas);

    protected abstract void renderImpl(Content content, Canvas canvas);
}