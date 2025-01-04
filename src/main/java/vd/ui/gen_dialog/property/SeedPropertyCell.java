package vd.ui.gen_dialog.property;

import vd.gen.alg.property.Property;
import vd.gen.alg.property.SeedProperty;
import vd.ui.std.SeedField;
import vd.ui.std.Style;

public class SeedPropertyCell extends PropertyCell//todo hover icon/button for displaying info about macros
{
    public SeedPropertyCell(SeedProperty property)
    {
        super(property);

        if(property.macro == null)
        { this.field = new SeedField(property.value); }
        else
        { this.field = new SeedField(property.macro); }
            this.field.setBackground(Style.enabled_color);
        
        add(this.field);
    }

    @Override
    public Property getProperty()
    {
        if(this.field.determineInput() == SeedField.InputType.Value)
        { return new SeedProperty(this.name_label.getText(), this.field.getValue()); }

        return new SeedProperty(this.name_label.getText(), this.field.getMacro()); 
    }

    @Override
    public boolean validateInput()
    {
        boolean valid = this.field.determineInput() != SeedField.InputType.Invalid;

        if(valid)
        { this.field.setBackground(Style.enabled_color);}
        else
        { this.field.setBackground(Style.bad_color);}

        return valid;
    }

    private SeedField field;
}