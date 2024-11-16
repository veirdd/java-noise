package vrd;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import vrd.gen.Generator;
import vrd.ui.Ui;

class Main
{
    public static void main(String[] args)
    {
        Ui ui = new Ui();
        ArrayList<Generator> generators_list = new ArrayList<>();

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            ui.init(generators_list);
        });
    }
}