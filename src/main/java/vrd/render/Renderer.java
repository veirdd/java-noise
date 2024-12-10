package vrd.render;

import java.awt.Dimension;

import vrd.ui.std.Canvas;
import vrd.ui.std.Panel;

import vrd.util.Content;

// Should not be used in JPanels with a layout manager
public class Renderer extends Panel
{
    public Renderer() {}

    public Renderer(Canvas canvas)
    {
        super();
        setCanvas(canvas);
    }

    public void setCanvas(Canvas canvas)
    {
        this.canvas = canvas;

        add(canvas);
        setSize(new Dimension(canvas.width, canvas.height));
    }

    public void render(Content content)
    {
        Interpreter interpreter = Interpreter.make(content.getDimensionality());

        interpreter.draw(content, this.canvas);
    }

    @Override
    public int getWidth()
    {
        if(this.canvas.width != super.getWidth())
        { throw new IllegalStateException("Component's width does not match canvas width"); }

        return canvas.width;
    }

    @Override
    public int getHeight()
    {
        if(this.canvas.height != super.getHeight())
        { throw new IllegalStateException("Component's height does not match canvas height"); }

        return canvas.height;
    }

    private Canvas canvas;
}