package vrd.ui.std;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

// Should be used within layouts that do not enforce size
public class Canvas extends JPanel
{
    // Creates invalid canvas which cannot be seen and modified until setSize is called
    public Canvas()
    {
        setBackground(Color.black);
    }

    public Canvas(int width, int height)
    {
        this();

        setSize(width, height);
    }

    public void set(int[] pos, Color color)
    {
        this.image.setRGB(pos[0], this.image.getHeight() - pos[1] - 1, color.getRGB());
    }

    public void clear()
    { this.image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB); }

    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);

        clear();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(this.image, 0, 0, null);
    }

    private BufferedImage image;
}