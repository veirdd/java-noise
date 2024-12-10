package vrd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import vrd.ui.std.Panel;

import vrd.gen.Generator;
import vrd.render.Renderer;
import vrd.ui.std.Button;
import vrd.ui.std.Canvas;
import vrd.ui.std.Style;

public class Ui
{
    public Ui(ArrayList<Generator> generator_list, Renderer renderer) // todo: add Generators label above generator panel
    {
        Canvas canvas = new Canvas(renderer_width, renderer_height); // next: update canvas on frame resize and make it cool

        Objects.requireNonNull(renderer);
        renderer.setCanvas(canvas);
            
        // Arrange panels to structurize UI

        this.render_panel = new Panel();
            this.render_panel.setLayout(null);
            this.render_panel.setPreferredSize(new Dimension(renderer_width, renderer_height));
            this.render_panel.setBorder(Style.etched_border);
            this.render_panel.add(canvas);

        this.apply_button = new Button("Apply");//d

        this.middle_panel = new Panel();
            this.middle_panel.add(this.apply_button);

        this.generator_panel = new GeneratorPanel(generator_list);
            this.generator_panel.setBorder(Style.etched_border);
            this.generator_panel.setPreferredSize(new Dimension(500, frame_height));

        this.control_panel = new Panel(new BorderLayout());
            this.control_panel.setPreferredSize(new Dimension(frame_width, frame_height - this.render_panel.getHeight()));
            this.control_panel.setBorder(BorderFactory.createEmptyBorder(
                Style.empty_border.getBorderInsets(this.control_panel).top, 0, 0, 0));
            this.control_panel.add(this.generator_panel, BorderLayout.WEST);
            this.control_panel.add(this.middle_panel, BorderLayout.CENTER);

        this.main_panel = new Panel(new BorderLayout());
            this.main_panel.setBorder(Style.empty_border);
            this.main_panel.add(this.render_panel, BorderLayout.NORTH);
            this.main_panel.add(this.control_panel, BorderLayout.CENTER);

        // Initialize frame

        this.frame = new JFrame("Noise");
            this.frame.setSize(new Dimension(frame_width, frame_height));
            this.frame.setLocationRelativeTo(null);
            this.frame.setContentPane(this.main_panel);
            this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.frame.setVisible(true);
    }

    public JFrame frame;
    private Panel main_panel;
        private Panel control_panel;
            private GeneratorPanel generator_panel;
            private Panel middle_panel;
                private Button apply_button;
        private Panel render_panel;

    public static final int frame_width = 1000;
    public static final int frame_height = 800;
    
    private static final int renderer_width = 800;
    private static final int renderer_height = 400; 
}