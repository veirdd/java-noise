package vrd.render;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import vrd.util.Content;

public class Renderer extends JPanel
{
    public Renderer() // todo: maybe add a way to change interpreter
    {
        
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);//d
    }

    Content content;
    Interpreter interpreter; // todo: (note) this is structurally similar to noise algorithms
}