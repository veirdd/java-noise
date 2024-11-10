package vrd;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vrd.render.Renderer;
import vrd.ui.Button;
import vrd.ui.GeneratorsPanel;

public class Ui
{
    public static void init()
    {
        // Arrange panels to structure UI

        apply_button = new Button("Apply");//d

        middle_panel = new JPanel(new BorderLayout());
            middle_panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
            middle_panel.add(apply_button, BorderLayout.CENTER);

        generators_panel = new GeneratorsPanel();
            generators_panel.setBorder(BorderFactory.createBevelBorder(0));
            generators_panel.setPreferredSize(new Dimension(300, FRAME_HEIGHT));

        control_panel = new JPanel(new BorderLayout());
            control_panel.setPreferredSize(new Dimension(FRAME_WIDTH, 300));
            control_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            control_panel.add(generators_panel, BorderLayout.WEST);
            control_panel.add(middle_panel, BorderLayout.CENTER);
            
        renderer = new Renderer();

        render_panel = new JPanel(new BorderLayout());
            render_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            render_panel.add(renderer, BorderLayout.CENTER);

        main_panel = new JPanel(new BorderLayout());
            main_panel.add(render_panel, BorderLayout.CENTER);
            main_panel.add(control_panel, BorderLayout.SOUTH);

        // Initialize frame

        frame = new JFrame("Noise");
            frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            frame.setLocationRelativeTo(null);
            frame.setContentPane(main_panel);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    static JFrame frame;
    static JPanel main_panel;
        static JPanel render_panel;
            static Renderer renderer;
        static JPanel control_panel;
            static GeneratorsPanel generators_panel;
            static JPanel middle_panel;
                static Button apply_button;

    static final int FRAME_WIDTH = 1200;
    static final int FRAME_HEIGHT = 800;
}