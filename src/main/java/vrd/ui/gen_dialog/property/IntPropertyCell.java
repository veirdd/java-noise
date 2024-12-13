package vrd.ui.gen_dialog.property;

import vrd.gen.alg.property.IntProperty;
import vrd.gen.alg.property.Property;
import vrd.ui.std.IntSpinner;
import vrd.ui.std.Style;

public class IntPropertyCell extends PropertyCell
{
    public IntPropertyCell(IntProperty property)
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
        return new IntProperty( 
            this.property.name,
            this.spinner.getInt(), 
            this.property.min_value, 
            this.property.max_value);
    }

    @Override
    public boolean validateInput()
    { return true; }

    private IntSpinner spinner;
    private IntProperty property;//todo make property a protected member of proeprtycell
}