package vrd.ui.gen_dialog.property;

import java.util.ArrayList;

import javax.swing.BoxLayout;

import vrd.alg.property.NaturalProperty;
import vrd.alg.Property;
import vrd.alg.property.FloatProperty;
import vrd.ui.std.Panel;

public class PropertyPanel extends Panel
{
    public PropertyPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        cell_list = new ArrayList<>();
    }

    public PropertyPanel(Property[] properties)
    {
        this();
        setProperties(properties);
    }

    public void setProperties(Property[] properties)
    {
        removeAll();
        cell_list.clear();

        for(Property property : properties)
        {
            switch(property.value_type) // todo: switch bad (look at how it was solved with algs maybe)
            {
                case Natural:
                    cell_list.add(new NaturalPropertyCell((NaturalProperty)property));
                    break;
                case Float:
                    cell_list.add(new FloatPropertyCell((FloatProperty)property));
                    break;
            }
        }

        for(PropertyCell property_cell : cell_list)
        { add(property_cell); }
        
        repaint();
        revalidate();
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