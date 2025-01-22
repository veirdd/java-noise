package vd.render.view;

import vd.ui.std.Canvas;
import vd.util.Content;

public abstract class DimensionalView implements View
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

    protected abstract void renderImpl(Content content, Canvas canvas);
}