package vd.ui.gen_dialog.property;

import vd.gen.alg.property.FloatProperty;
import vd.gen.alg.property.Property;
import vd.ui.std.FloatField;
import vd.ui.std.Style;

public class FloatPropertyCell extends PropertyCell
{
    public FloatPropertyCell(FloatProperty property)
    {
        super(property);

        this.field = new FloatField(property.value);
            this.field.setBackground(Style.enabled_color);
        
        add(field);
    }

    @Override
    public Property getProperty()
    { return new FloatProperty(this.name_label.getText(), this.field.getValue()); }

    @Override
    public boolean validateInput()
    {
        boolean valid = this.field.validateInput();

        if(valid)
        { this.field.setBackground(Style.enabled_color);}
        else
        { this.field.setBackground(Style.bad_color);}

        return valid;
    }

    private FloatField field;
}