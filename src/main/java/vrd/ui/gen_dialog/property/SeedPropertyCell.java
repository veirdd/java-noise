package vrd.ui.gen_dialog.property;

import vrd.gen.alg.property.SeedProperty;

import vrd.gen.alg.property.Property;
import vrd.ui.std.SeedField;
import vrd.ui.std.Style;

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
        { return new SeedProperty(this.name_label.getName(), this.field.getValue()); }

        return new SeedProperty(this.name_label.getName(), this.field.getMacro()); 
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