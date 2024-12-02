package vrd.ui.gen_dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vrd.alg.noise.PerlinNoise1d;
import vrd.gen.BlendMode;
import vrd.gen.Generator;
import vrd.ui.std.Button;

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

        this.save_button = new Button("Save");
            this.save_button.setEnabled(false);
            this.save_button.addActionListener((ActionEvent _)->
            { save(); });

        this.algorithm_combo_box = new JComboBox<>(algorithm_list);

        this.algorithm_label = new JLabel("Algorithm");

        this.algorithm_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            this.algorithm_panel.add(this.algorithm_label);
            this.algorithm_panel.add(this.algorithm_combo_box);

        this.name_field = new JTextField(16);

        this.name_label = new JLabel("Name");

        this.name_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            this.name_panel.add(this.name_label);
            this.name_panel.add(this.name_field);

        this.main_panel = new JPanel(new BorderLayout());
            this.main_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            this.main_panel.add(this.name_panel);

        // Configure dialog

        setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setLocationRelativeTo(null);
        setContentPane(this.main_panel);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Generator");
        setVisible(true);
    }

    public Generator createGenerator()
    {
        if(this.generator == null)
        { throw new IllegalStateException("The requested generator has not been formed"); }

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
        private JPanel name_panel;
            private JLabel name_label;
            private JTextField name_field;
        private JPanel algorithm_panel;
            private JLabel algorithm_label;
            private JComboBox<String> algorithm_combo_box;
        private JLabel properties_label;
        private AlgorithmPanel properties_panel;
        private JPanel bottom_panel;
            private JPanel blend_mode_panel;
                private JLabel blend_mode_label;
                private JTextField blend_mode_field;
            private Button save_button;
    
    private static final int DIALOG_WIDTH = 600;
    private static final int DIALOG_HEIGHT = 400;
}