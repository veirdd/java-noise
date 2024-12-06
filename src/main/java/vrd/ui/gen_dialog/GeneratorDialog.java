package vrd.ui.gen_dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import vrd.ui.std.Panel;
import javax.swing.JTextField;

import vrd.alg.Algorithm;
import vrd.alg.SignatureList;
import vrd.gen.BlendMode;
import vrd.gen.Generator;
import vrd.ui.gen_dialog.property.PropertyPanel;
import vrd.ui.std.Button;
import vrd.ui.std.ComboBox;
import vrd.ui.std.Style;

public class GeneratorDialog extends JDialog
{
    public GeneratorDialog(
        JFrame frame, 
        Generator generator, 
        SaveOperation save_operation)
    {
        super(frame);

        this.save_operation = save_operation;

        // Create interface
        this.save_button = new Button("Save");
            this.save_button.addActionListener((ActionEvent _)->
            { save(); });
            
        this.save_panel = new Panel(new BorderLayout());
            this.save_panel.add(save_button, BorderLayout.SOUTH);

        this.blend_mode_combo_box = new ComboBox<>(BlendMode.getBlendModeNames());

        this.blend_mode_label = new JLabel("Blend mode");

        this.blend_mode_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.blend_mode_panel.add(blend_mode_label);
            this.blend_mode_panel.add(blend_mode_combo_box);

        this.bottom_panel = new Panel(new BorderLayout());
            this.bottom_panel.add(this.blend_mode_panel, BorderLayout.WEST);
            this.bottom_panel.add(this.save_panel, BorderLayout.EAST);

        this.algorithm_combo_box = new ComboBox<>(SignatureList.getAlgorithmNames());
            this.algorithm_combo_box.addActionListener((ActionEvent _)->
            { updateProperties(); });
        
        this.algorithm_label = new JLabel("Algorithm");

        this.algorithm_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.algorithm_panel.add(this.algorithm_label);
            this.algorithm_panel.add(this.algorithm_combo_box);

        this.properties_label = new JLabel("Algorithm properties");

        this.properties_label_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.properties_label_panel.add(this.properties_label);

        this.property_panel = new PropertyPanel();
            // Visual
            this.property_panel.setBorder(BorderFactory.createEtchedBorder());

        this.name_field = new JTextField(Style.text_field_size);

        this.name_label = new JLabel("Name");

        this.name_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.name_panel.add(this.name_label);
            this.name_panel.add(this.name_field);

        this.main_panel = new Panel();
            this.main_panel.setLayout(new BoxLayout(this.main_panel, BoxLayout.Y_AXIS));
            this.main_panel.add(this.name_panel);
            this.main_panel.add(this.algorithm_panel);
            this.main_panel.add(this.properties_label_panel);
            this.main_panel.add(this.property_panel);
            this.main_panel.add(this.bottom_panel);
            // Visual
            this.main_panel.setBorder(Style.empty_border);

        // Import generator data

        if(generator != null)
        { 
            this.algorithm_combo_box.setSelectedItem(generator.algorithm.getSignature().name);
            this.blend_mode_combo_box.setSelectedItem(generator.blend_mode.name());
            this.name_field.setText(generator.name);
            this.property_panel.updateProperties(generator.algorithm.getProperties());
        }
        else
        {
            updateProperties();
        }

        // Configure dialog

        setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setLocationRelativeTo(null);
        setContentPane(this.main_panel);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Generator");
        getRootPane().setDefaultButton(save_button);
        setVisible(true);
    }

    private void save()
    {
        // Validate property value inputs

        if(!this.property_panel.validateInputs())
        { return; }

        // Close the dialog

        dispose();
        
        // Create the generator

        Algorithm selected_algorithm = SignatureList.makeAlgorithmFromId(
            SignatureList.Id.values()[this.algorithm_combo_box.getSelectedIndex()]);

        selected_algorithm.setProperties(this.property_panel.getProperties());

        BlendMode selected_blend_mode = BlendMode.values()[this.blend_mode_combo_box.getSelectedIndex()];

        Generator generator = new Generator(selected_algorithm, selected_blend_mode);

        if(!this.name_field.getText().equals(""))
        { generator.name = this.name_field.getText(); }

        //

        save_operation.run(generator);
    }

    private void updateProperties()
    {
        this.property_panel.updateProperties(SignatureList.makeAlgorithmFromId(
            SignatureList.Id.values()[this.algorithm_combo_box.getSelectedIndex()]).getProperties());
    }

    private final SaveOperation save_operation;

    private Panel main_panel;
        private Panel name_panel;
            private JLabel name_label;
            private JTextField name_field;
        private PropertyPanel property_panel;
        private Panel properties_label_panel;
            private JLabel properties_label;
        private Panel algorithm_panel; 
            private JLabel algorithm_label;
            private ComboBox<String> algorithm_combo_box;
        private Panel bottom_panel;
            private Panel blend_mode_panel;
                private JLabel blend_mode_label;
                private JComboBox<String> blend_mode_combo_box;
            private Panel save_panel;
                private Button save_button;
    
    private static final int DIALOG_WIDTH = 600;
    private static final int DIALOG_HEIGHT = 400;
}