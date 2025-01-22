package vd.render.view;

import vd.ui.std.Canvas;
import vd.util.Content;

public interface View
{
    public void render(Content content, Canvas canvas);

    public abstract Signature getSignature();
    public abstract int[] getRequiredContentSize(Canvas canvas);
}