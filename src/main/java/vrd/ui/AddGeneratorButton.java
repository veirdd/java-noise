package vrd.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import vrd.gen.Generator;
import vrd.ui.gen_dialog.GeneratorDialog;
import vrd.ui.std.Button;

public class AddGeneratorButton extends Button
{
    public AddGeneratorButton(
        String text, 
        ArrayList<Generator> generator_list, 
        Runnable update_notifier)
    {
        super(text);

        this.generator_list = generator_list;
        
        this.update_notifier = update_notifier;

        addActionListener((ActionEvent _)->
        { openDialog(); });
    }

    private void openDialog()
    {
        if(this.dialog == null || !this.dialog.isVisible())
        {
            this.dialog = new GeneratorDialog(
                (JFrame)SwingUtilities.getWindowAncestor(this), 
                null, 
                (Generator generator)->
                { onDialogSave(generator); });
        }
    }

    // Add new generator
    private void onDialogSave(Generator generator)
    {
        generator_list.add(generator);

        update_notifier.run();
    }

    private final ArrayList<Generator> generator_list;
    private GeneratorDialog dialog;
    // Used to notify about changes to generator_list
    private final Runnable update_notifier;
}