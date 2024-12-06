package vrd.ui.gen_dialog.property;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;
import vrd.ui.std.ScrollPane;

public class PropertyPanel extends ScrollPane
{
    public PropertyPanel(Property[] properties)
    {
        cell_list = new ArrayList<>();

        for(Property property : properties)
        {
            switch(property.value_type) // todo: switch bad (look at how it was solved with algs maybe)
            {
                case Float:
                    cell_list.add(new FloatPropertyCell((FloatProperty)property));
                    break;
            }
        }

        for(PropertyCell property_cell : cell_list)
        { this.panel.add(property_cell); }
    }

    private ArrayList<PropertyCell> cell_list;
}