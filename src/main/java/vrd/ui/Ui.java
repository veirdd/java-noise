package vrd.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import vrd.ui.std.Panel;

import vrd.gen.Generator;
import vrd.ui.std.Button;
import vrd.ui.std.Canvas;
import vrd.ui.std.Style;

public class Ui
{
    public Ui(ArrayList<Generator> generator_list, Canvas canvas, Runnable generation_operation) // todo: add Generators label above generator panel
    {            
        // Arrange panels to structurize UI

        this.canvas = canvas;

        this.render_panel = new Panel();
            this.render_panel.setLayout(null);
            this.render_panel.setPreferredSize(new Dimension(frame_init_size.width, canvas_height));
            this.render_panel.setBorder(Style.etched_border);
            this.render_panel.setBackground(Color.black);
            this.render_panel.add(this.canvas);

        this.generate_button = new Button("Generate");
            this.generate_button.addActionListener((ActionEvent _)->
            { generation_operation.run(); });

        this.middle_panel = new Panel();
            this.middle_panel.setBorder(Style.etched_border);
            this.middle_panel.add(this.generate_button);

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

        this.main_panel = new Panel(new BorderLayout());
            this.main_panel.setBorder(Style.empty_border);
            this.main_panel.add(this.render_panel, BorderLayout.NORTH);
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

    }

    private void resizeComponents(ComponentEvent e)
    {
        this.canvas.setSize(this.render_panel.getSize());
    }

    public JFrame frame;
    private Panel main_panel;
        private Panel control_panel;
            private Panel left_panel;
                private Panel generators_label_panel;
                    private JLabel generators_label;
                private GeneratorPanel generator_panel;
            private Panel middle_panel;
                private Button generate_button;
        private Panel render_panel;
            private Canvas canvas;

    private static final Dimension frame_init_size = new Dimension(1200, 800);
    private static final int canvas_height = 400;
    private static final int big = 9999; // todo: wtf xd
}