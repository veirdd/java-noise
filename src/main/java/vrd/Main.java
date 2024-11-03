package vrd;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

import vrd.gen.*;

import vrd.ui.Ui;

class Main
{
    public static void main(String[] args) // next: some actual rendering lmao
    {
        ArrayList<Integer> content;

        Generator generator = new SurfaceGenerator();
        content = generator.generate();

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            Ui.init();
        });
    }
}