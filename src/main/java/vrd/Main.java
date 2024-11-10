package vrd;

import javax.swing.SwingUtilities;

import vrd.gen.BlendMode;
import vrd.gen.Generator;
import vrd.noise.PerlinNoise1d;
import vrd.util.Content;

class Main
{
    public static void main(String[] args)
    {
        Generator generator = new Generator(new PerlinNoise1d(), BlendMode.Add);

        final int[] size = {10};
        final int[] offset = {0};

        Content content = generator.generate(size, offset);

        

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            Ui.init();
        });
    }
}