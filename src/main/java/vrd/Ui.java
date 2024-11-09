package vrd;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vrd.render.Renderer;
import vrd.ui.Button;

public class Ui
{
    public static void init()
    {
        // Arrange panels to structure UI

        renderer = new Renderer();

        generators_panel = new JPanel();
            generators_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            generators_panel.setPreferredSize(new Dimension(300, FRAME_HEIGHT));

        apply_button = new Button("Apply");//d

        control_panel = new JPanel(new BorderLayout());
            control_panel.setPreferredSize(new Dimension(FRAME_WIDTH, 200));
            control_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            control_panel.add(generators_panel, BorderLayout.WEST);
            control_panel.add(apply_button, BorderLayout.CENTER);

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
            static JPanel generators_panel;
            static Button apply_button;

    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
}