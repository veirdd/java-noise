package vrd.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vrd.gen.Generator;

public class GeneratorDialog extends JDialog
// todo: this should only be responsible for getting properties for a generator from user
// and creating said generator object
// no methods for adding to generatorspanel or such (so that this can be used for modifying existing generators)

// todo: my brain is melting  idk how to 'notify' generatorspanel that the generator is created so that it can allocate it
// maybe analyze generatorspanel
{
    public GeneratorDialog(JFrame frame, Runnable save_operation)
    {
        super(frame);

        this.save_operation = save_operation;

        // Create interface

        this.add_button = new Button("Save");
            this.add_button.addActionListener((ActionEvent _)->
            { save(); });

        this.main_panel = new JPanel();
            this.main_panel.add(this.add_button);

        // Configure dialog

        this.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setLocationRelativeTo(null);
        this.setContentPane(this.main_panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public Generator createGenerator() // next: universal generator creation interface in dialog
    {
        if(this.generator == null)
        { throw new IllegalStateException(); }

        return this.generator;
    }

    private void save()
    {
        // Close the dialog
        dispose();

        save_operation.run();
    }

    private final Runnable save_operation;

    // The generator this dialog creates
    private Generator generator;

    private JPanel main_panel;
        private Button add_button;
    
    private static final int DIALOG_WIDTH = 600;
    private static final int DIALOG_HEIGHT = 400;
}