package vrd;

import javax.swing.SwingUtilities;

import vrd.ui.Ui;

class Main
{
    public static void main(String[] args)
    {
        Ui ui = new Ui();

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            ui.init();
        });
    }
}