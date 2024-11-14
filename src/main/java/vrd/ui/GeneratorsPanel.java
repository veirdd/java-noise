package vrd.ui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.annotation.processing.Generated;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GeneratorsPanel extends JScrollPane
{
    public GeneratorsPanel(ArrayList<GeneratorTile> generator_list)
    {
        super(null, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.generator_list = generator_list;

        this.panel = new JPanel();
            this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
            this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setViewportView(this.panel);

        this.plus = new AddGeneratorButton("+", this.generator_list);

        updateComponents();
    }

    private void updateComponents()
    {
        this.panel.removeAll();

        for(JComponent component : this.generator_list)
        {
            this.panel.add(component);
            this.panel.add(Box.createRigidArea(new Dimension(5, 5)));
        }

        this.panel.add(this.plus);
    }

    private final JPanel panel;
    private final ArrayList<GeneratorTile> generator_list;
    // todo: remake into ArrayList<Generator> and maybe get another list of GeneratorTiles to hold these
    private final Button plus;
}