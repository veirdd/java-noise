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
        content.set(new int[]{2}, 2);//d

        // todo: Content content = new Content(size);
        //       content.update(generator, offset);
        // actually i think it's a bad idea (cycle dependency) (maybe resolve differently)

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            Ui.init();
        });
    }
}