package vrd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ui
{
    public static void init()
    {
        // Arrange panels to structure UI

        button = new JButton();//d

        control_panel = new JPanel(new GridLayout());
            control_panel.setPreferredSize(new Dimension(WIDTH, 200));
            control_panel.add(button);//d

        render_panel = new Renderer();

        main_panel = new JPanel(new BorderLayout());
            main_panel.add(render_panel, BorderLayout.NORTH);
            main_panel.add(control_panel, BorderLayout.SOUTH);

        // Initialize frame

        frame = new JFrame("Noise");
            frame.setSize(new Dimension(WIDTH, HEIGHT));
            frame.setLocationRelativeTo(null);
            frame.setContentPane(main_panel);
            frame.setResizable(false);
            frame.setVisible(true);
    }

    static JFrame frame;
    static JPanel main_panel;
        static JPanel render_panel;
        static JPanel control_panel;
            static JButton button;//d

    static final int WIDTH = 800;
    static final int HEIGHT = 600;
}