package vd.ui.gen_dialog.property;

import java.util.ArrayList;

import javax.swing.BoxLayout;

import vd.gen.alg.property.FloatProperty;
import vd.gen.alg.property.IntProperty;
import vd.gen.alg.property.Property;
import vd.gen.alg.property.SeedProperty;
import vd.ui.std.Panel;

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
            switch(property.value_type)//todo switch bad (look at how it was solved with algs maybe)
            {
                case Int:
                    cell_list.add(new IntPropertyCell((IntProperty)property));
                    break;
                case Float:
                    cell_list.add(new FloatPropertyCell((FloatProperty)property));
                    break;
                case Seed:
                    cell_list.add(new SeedPropertyCell((SeedProperty)property));
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