package vrd.ui.std;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Canvas extends JComponent
{
    public Canvas(int width, int height)
    {
        if(width <= 0 || height <= 0)
        { throw new IllegalArgumentException("Canvas initialized with invalid size"); }

        this.width = width;
        this.height = height;

        setSize(width, height);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    public final int width;
    public final int height;
}