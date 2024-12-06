package vrd.ui.std;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Panel extends JPanel
{
    public Panel()
    {
        super();
        configure(); // todo: am i stupid bc i'm sure this can be more clean
    }

    public Panel(LayoutManager layout_manager)
    {
        super(layout_manager);
        configure();
    }

    private void configure()
    {
        setBackground(Style.background_color);
    }
}