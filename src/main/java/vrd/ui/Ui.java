package vrd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vrd.gen.Generator;
import vrd.render.Renderer;
import vrd.ui.std.Button;

public class Ui
{
    public void init(ArrayList<Generator> generator_list)
    {
        // Arrange panels to structurize UI

        this.apply_button = new Button("Apply");//d

        this.middle_panel = new JPanel(new BorderLayout());
            middle_panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
            middle_panel.add(this.apply_button, BorderLayout.CENTER);

        this.generators_panel = new GeneratorPanel(generator_list);
            generators_panel.setBorder(BorderFactory.createBevelBorder(0));
            generators_panel.setPreferredSize(new Dimension(300, FRAME_HEIGHT));

        this.control_panel = new JPanel(new BorderLayout());
            control_panel.setPreferredSize(new Dimension(FRAME_WIDTH, 300));
            control_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            control_panel.add(this.generators_panel, BorderLayout.WEST);
            control_panel.add(this.middle_panel, BorderLayout.CENTER);
            
        this.renderer = new Renderer();

        this.render_panel = new JPanel(new BorderLayout());
            render_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            render_panel.add(this.renderer, BorderLayout.CENTER);

        this.main_panel = new JPanel(new BorderLayout());
            main_panel.add(this.render_panel, BorderLayout.CENTER);
            main_panel.add(this.control_panel, BorderLayout.SOUTH);

        // Initialize frame

        this.frame = new JFrame("Noise");
            frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            frame.setLocationRelativeTo(null);
            frame.setContentPane(this.main_panel);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public JFrame frame;
    private JPanel main_panel;
        private JPanel render_panel;
            private Renderer renderer;
        private JPanel control_panel;
            private GeneratorPanel generators_panel;
            private JPanel middle_panel;
                private Button apply_button;

    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
}