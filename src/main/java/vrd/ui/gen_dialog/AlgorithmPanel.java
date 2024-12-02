package vrd.ui.gen_dialog;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import vrd.alg.Property;
import vrd.ui.std.ScrollPane;

public class AlgorithmPanel extends ScrollPane
{
    public AlgorithmPanel(Property[] properties)
    {
        label_list = new ArrayList<>();
        text_field_list = new ArrayList<>();

        for(Property property : properties)
        {
            label_list.add(new JLabel(property.name));
            text_field_list.add(new JTextField());
            // todo
        }
    }

    private ArrayList<JLabel> label_list;
    private ArrayList<JTextField> text_field_list; // todo: these lists may be unnecessary (set everything in constructor add to panel and lets go)
}