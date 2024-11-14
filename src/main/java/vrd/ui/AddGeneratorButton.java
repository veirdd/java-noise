package vrd.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import vrd.gen.Algorithm;
import vrd.gen.BlendMode;
import vrd.gen.Generator;

public class AddGeneratorButton extends Button
{
    public AddGeneratorButton(String text, ArrayList<GeneratorTile> generator_list)
    {
        super(text);

        this.generator_list = generator_list;

        addActionListener((ActionEvent e)->
        { onClick(); });
    }

    private void onClick()
    {
        if(this.dialog == null || !this.dialog.isVisible())
        {
            this.dialog = new GeneratorDialog((JFrame)SwingUtilities.getWindowAncestor(this), ()->
            { onDialogSave(); });
        }
    }

    // Add new generator
    private void onDialogSave()
    {
        Algorithm algorithm = null;//d
        BlendMode blend_mode = null;//d

        generator_list.add(new GeneratorTile(new Generator(algorithm, blend_mode)));
    }

    private final ArrayList<GeneratorTile> generator_list;
    private GeneratorDialog dialog;
}