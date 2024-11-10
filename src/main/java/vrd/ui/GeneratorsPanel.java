package vrd.ui;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GeneratorsPanel extends JPanel
{
    public GeneratorsPanel()
    {
        this.setLayout(mgr);

        this.generators = new ArrayList();
        this.plus = new Button("+");

        this.add(this.plus);
    }

    private final ArrayList<JComponent> generators;
    private Button plus;
}