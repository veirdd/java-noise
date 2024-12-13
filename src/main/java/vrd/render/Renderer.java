package vrd.render;

import vrd.render.view.View;
import vrd.ui.std.Canvas;

import vrd.util.Content;

public class Renderer
{
    // Creates a renderer without output canvas and view
    public Renderer() {}

    public Renderer(Canvas canvas, View view)
    {
        this();
        this.canvas = canvas;
        this.view = view;
    }

    public void render(Content content)
    {
        this.canvas.clear();

        if(content != null)
        { view.render(content, this.canvas); }

        this.canvas.repaint();
    }

    public View view;
    public Canvas canvas;
}