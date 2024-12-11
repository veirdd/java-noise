package vrd.ui.gen_dialog.property;

import vrd.alg.Property;
import vrd.alg.property.NaturalProperty;
import vrd.ui.std.IntSpinner;
import vrd.ui.std.Style;

public class NaturalPropertyCell extends PropertyCell
{
    public NaturalPropertyCell(NaturalProperty property)
    {
        super(property);

        this.spinner = new IntSpinner(property.value, property.min_value, property.max_value);
            this.spinner.setBackground(Style.enabled_color);

        this.property = property;
        
        add(spinner);
    }

    @Override
    public Property getProperty()
    {
        return new NaturalProperty(
            this.spinner.getInt(), 
            this.property.min_value, 
            this.property.max_value, 
            this.property.name);
    }

    @Override
    public boolean validateInput()
    { return true; }

    private IntSpinner spinner;
    private NaturalProperty property;
}