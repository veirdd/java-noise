package vrd.render;

import java.awt.Color;
import java.awt.Graphics;

import vrd.ui.std.Panel;

import vrd.util.Content;

public class Renderer extends Panel
{
    public Renderer() // todo: maybe add a way to change interpreter
    {
        
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());//d
    }

    Content content;
    Interpreter interpreter; // todo: (note) this is structurally similar to noise algorithms
}