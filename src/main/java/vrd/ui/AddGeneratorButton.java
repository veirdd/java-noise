package vrd.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import vrd.gen.Generator;

public class AddGeneratorButton extends Button
{
    public AddGeneratorButton(String text, ArrayList<Generator> generators_list, Runnable update_notifier)
    {
        super(text);

        this.generators_list = generators_list;
        
        this.update_notifier = update_notifier;

        addActionListener((ActionEvent ignored)->
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
        generators_list.add(dialog.createGenerator());

        update_notifier.run();
    }

    private final ArrayList<Generator> generators_list;
    private GeneratorDialog dialog;
    // Used to notify about changes to generators_list
    private final Runnable update_notifier;
}