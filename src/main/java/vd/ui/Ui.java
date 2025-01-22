package vd.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import vd.gen.Generator;
import vd.gen.Settings;
import vd.gen.alg.property.SeedProperty;
import vd.render.Renderer;
import vd.render.view.ViewList;
import vd.ui.std.Button;
import vd.ui.std.Canvas;
import vd.ui.std.ComboBox;
import vd.ui.std.Panel;
import vd.ui.std.SeedField;
import vd.ui.std.Style;

public class Ui
{
    public Ui(
        ArrayList<Generator> generator_list, 
        Renderer renderer, 
        Settings settings, 
        Runnable generation_operation)
    {            
        this.settings = settings;
        this.generation_operation = generation_operation;

        // Arrange panels to structurize UI

        this.canvas = renderer.canvas;

        this.canvas_panel = new Panel();
            this.canvas_panel.setLayout(null);
            this.canvas_panel.setPreferredSize(new Dimension(frame_init_size.width, canvas_height));
            this.canvas_panel.setBorder(Style.etched_border);
            this.canvas_panel.setBackground(Color.black);
            this.canvas_panel.add(this.canvas);

        this.seed_field = new SeedField(SeedProperty.Macro.Default);
            this.seed_field.setText(SeedProperty.macro_literals.get(SeedProperty.Macro.Random.ordinal()));

        this.seed_label = new JLabel("Default Seed");

        this.settings_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.settings_panel.setPreferredSize(new Dimension(300, big));
            this.settings_panel.add(Box.createRigidArea(new Dimension(0, 0)));
            this.settings_panel.add(this.seed_label);
            this.settings_panel.add(this.seed_field);

        this.settings_label = new JLabel("Settings");

        this.settings_label_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.settings_label_panel.add(this.settings_label);

        this.right_panel = new Panel();
            this.right_panel.setLayout(new BoxLayout(this.right_panel, BoxLayout.Y_AXIS));
            this.right_panel.setBorder(Style.etched_border);
            this.right_panel.add(this.settings_label_panel);
            this.right_panel.add(this.settings_panel);

        this.view_combo_box = new ComboBox<>(ViewList.getViewNames());
            this.view_combo_box.addActionListener((ActionEvent _) ->
            { renderer.view = ViewList.makeViewFromId(ViewList.Id.values()[this.view_combo_box.getSelectedIndex()]); });
            // Set default view from combo box
            renderer.view = ViewList.makeViewFromId(ViewList.Id.values()[0]);

        this.view_label = new JLabel("View");

        this.view_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.view_panel.add(Box.createRigidArea(new Dimension(0, 0)));
            this.view_panel.add(this.view_label);
            this.view_panel.add(this.view_combo_box);

        this.renderer_label = new JLabel("Renderer");

        this.renderer_label_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.renderer_label_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.renderer_label_panel.add(this.renderer_label);

        this.lower_panel = new Panel();
            this.lower_panel.setLayout(new BoxLayout(this.lower_panel, BoxLayout.Y_AXIS));
            this.lower_panel.setBorder(Style.etched_border);
            this.lower_panel.add(this.renderer_label_panel);
            this.lower_panel.add(this.view_panel);

        this.generate_button = new Button("Generate");
            this.generate_button.setAlignmentX(0.5f);
            this.generate_button.addActionListener((ActionEvent _) ->
            { generate(); });

        this.upper_panel = new Panel();
            this.upper_panel.setLayout(new BoxLayout(this.upper_panel, BoxLayout.Y_AXIS));
            this.upper_panel.setBorder(Style.etched_border);
            this.upper_panel.add(Box.createRigidArea(new Dimension(0, 20)));
            this.upper_panel.add(this.generate_button);
            this.upper_panel.add(Box.createRigidArea(new Dimension(0, 20)));

        this.middle_panel = new Panel(new BorderLayout());
            this.middle_panel.setBorder(Style.etched_border);
            this.middle_panel.add(this.upper_panel, BorderLayout.NORTH);
            this.middle_panel.add(this.lower_panel, BorderLayout.CENTER);

        this.generators_label = new JLabel("Generators");

        this.generators_label_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
            this.generators_label_panel.add(this.generators_label);
            
        this.generator_panel = new GeneratorPanel(generator_list);
            this.generator_panel.setPreferredSize(new Dimension(400, big));
            this.generator_panel.setBorder(null);

        this.left_panel = new Panel();
            this.left_panel.setLayout(new BoxLayout(this.left_panel, BoxLayout.Y_AXIS));
            this.left_panel.setBorder(Style.etched_border);
            this.left_panel.add(this.generators_label_panel);
            this.left_panel.add(this.generator_panel);

        this.control_panel = new Panel(new BorderLayout());
            this.control_panel.setBorder(BorderFactory.createEmptyBorder(
                Style.empty_border.getBorderInsets(this.control_panel).top, 0, 0, 0));
            this.control_panel.add(this.left_panel, BorderLayout.WEST);
            this.control_panel.add(this.middle_panel, BorderLayout.CENTER);
            this.control_panel.add(this.right_panel, BorderLayout.EAST);

        this.main_panel = new Panel(new BorderLayout());
            this.main_panel.setBorder(Style.empty_border);
            this.main_panel.add(this.canvas_panel, BorderLayout.NORTH);
            this.main_panel.add(this.control_panel, BorderLayout.CENTER);
            this.main_panel.addComponentListener(new ComponentAdapter() 
            {
                // Effectively called when the frame is resized by user
                @Override
                public void componentResized(ComponentEvent e)
                { resizeComponents(e); }
            });

        // Initialize frame

        this.frame = new JFrame("Noise");
            this.frame.setSize(frame_init_size);
            this.frame.setLocationRelativeTo(null);
            this.frame.setContentPane(this.main_panel);
            this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.frame.setVisible(true);
    }

    public void log(String message)
    {
        //todo console interface
        System.out.println(message);//d
    }

    private void resizeComponents(ComponentEvent e)
    {
        this.canvas.setSize(this.canvas_panel.getSize());
    }

    private boolean generate()
    {
        if(!validateInputs())
        { return false; }

        SeedProperty seed_property = new SeedProperty("Seed", 0);
            // The only enabled global macro is random
            if(this.seed_field.determineInput() == SeedField.InputType.Macro)
            { seed_property.macro = SeedProperty.Macro.Random; }
            else
            // Concrete seed must be the case assuming validation happened
            { seed_property.value = this.seed_field.getValue(); }
            this.settings.seed_property = seed_property;

        generation_operation.run();

        return true;
    }

    private boolean validateInputs()
    {
        // seed_field
            // Valid only if a concrete seed or the random macro is inputted
            SeedField.InputType seed_field_input = this.seed_field.determineInput();
            boolean seed_field_valid = 
                seed_field_input == SeedField.InputType.Value ||
                (seed_field_input == SeedField.InputType.Macro && this.seed_field.getMacro() == SeedProperty.Macro.Random);

        if(seed_field_valid)
        { this.seed_field.setBackground(Style.enabled_color);}
        else
        {
            this.seed_field.setBackground(Style.bad_color);

            return false;
        }

        //

        return true;
    }

    private Settings settings;
    private Runnable generation_operation;

    public JFrame frame;
    private Panel main_panel;
        private Panel control_panel;
            private Panel left_panel;
                private Panel generators_label_panel;
                    private JLabel generators_label;
                private GeneratorPanel generator_panel;
            private Panel middle_panel;
                private Panel upper_panel;
                    private Button generate_button;
                private Panel lower_panel;
                    private Panel renderer_label_panel;
                        private JLabel renderer_label;
                    private Panel view_panel;
                        private JLabel view_label;
                        private ComboBox<String> view_combo_box;
            private Panel right_panel;
                private Panel settings_label_panel;
                    private JLabel settings_label;
                        private JLabel seed_label;
                        private SeedField seed_field;
                private Panel settings_panel;
        private Panel canvas_panel;
            private Canvas canvas;

    private static final Dimension frame_init_size = new Dimension(1200, 800);
    private static final int canvas_height = 400;
    private static final int big = 9999;//todo wtf xd
}