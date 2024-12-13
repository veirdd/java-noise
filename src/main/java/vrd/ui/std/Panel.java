package vrd.ui.std;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Panel extends JPanel
{
    public Panel()
    {
        super();
        setBackground(Style.background_color);
    }

    public Panel(LayoutManager layout_manager)
    {
        this();
        setLayout(layout_manager);
    }
}