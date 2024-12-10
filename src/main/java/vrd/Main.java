package vrd;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import vrd.gen.Generator;
import vrd.render.Renderer;
import vrd.ui.Ui;

class Main
{
    public static void main(String[] args)
    {
        ArrayList<Generator> generator_list = new ArrayList<>();
        Renderer renderer = new Renderer();

        // Generator -> Content -> Renderer -> Canvas

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        { Ui _ = new Ui(generator_list, renderer); });

    }
}