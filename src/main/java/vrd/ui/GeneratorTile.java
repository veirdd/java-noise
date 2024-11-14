package vrd.ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import vrd.gen.Generator;

public class GeneratorTile extends JPanel
{
    public GeneratorTile(Generator generator)
    {
        this.generator = generator;
    }

    private JButton up_button;
    private JButton down_button;
    private JButton modify_button;

    Generator generator;
    private GeneratorDialog dialog;
}