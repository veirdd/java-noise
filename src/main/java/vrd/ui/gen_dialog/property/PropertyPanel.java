package vrd.ui.gen_dialog.property;

import java.util.ArrayList;

import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;
import vrd.ui.std.ScrollPane;

public class PropertyPanel extends ScrollPane
{
    public PropertyPanel()
    {
        cell_list = new ArrayList<>();
    }

    public PropertyPanel(Property[] properties)
    {
        this();
        updateProperties(properties);
    }

    public void updateProperties(Property[] properties)
    {
        this.panel.removeAll();
        cell_list.clear();

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
        
        this.panel.repaint();
        this.panel.revalidate();
    }

    public Property[] getProperties()
    {
        Property[] properties = new Property[this.cell_list.size()];

        for(int i = 0; i < this.cell_list.size(); ++i)
        { properties[i] = cell_list.get(i).getProperty(); }

        return properties;
    }

    public boolean validateInputs()
    {
        for(PropertyCell property_cell : cell_list)
        {
            if(!property_cell.validateInput())
            { return false; }
        }

        return true;
    }

    private ArrayList<PropertyCell> cell_list;
}