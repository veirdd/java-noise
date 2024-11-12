package vrd.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GeneratorsPanel extends JScrollPane
{
    public GeneratorsPanel()
    {
        super(null, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setViewportView(this.panel);
        
        this.generators = new ArrayList<JComponent>();

        this.plus = new Button("+");

        updateComponents();
    }

    private void updateComponents()
    {
        this.panel.removeAll();

        for(JComponent component : this.generators)
        {
            this.panel.add(component);
            this.panel.add(Box.createRigidArea(new Dimension(5, 5)));
        }

        this.panel.add(this.plus);
    }

    private JPanel panel;
    private final ArrayList<JComponent> generators;
    private Button plus;
}

// next: make plus button work lol