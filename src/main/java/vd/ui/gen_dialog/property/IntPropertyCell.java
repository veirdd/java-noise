package vd.ui.gen_dialog.property;

import vd.gen.alg.property.IntProperty;
import vd.gen.alg.property.Property;
import vd.ui.std.IntSpinner;
import vd.ui.std.Style;

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
    {
        boolean valid = this.spinner.validateInput();

        if(valid)
        { this.spinner.getEditor().getComponent(0).setBackground(Style.enabled_color);}
        else
        { this.spinner.getEditor().getComponent(0).setBackground(Style.bad_color);}

        return valid;
    }

    private IntSpinner spinner;
    private IntProperty property;//todo make property a protected member of proeprtycell
}