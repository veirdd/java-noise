package vrd.ui.std;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import vrd.gen.alg.property.SeedProperty;

public class SeedField extends JTextField
{
    public enum InputType
    {
        Value,
        Macro,
        Invalid
    }

    public SeedField(SeedProperty.Macro initial_macro)
    {
        this();
        setText(SeedProperty.macros.get(initial_macro.ordinal()));
    }

    public SeedField(int initial_value)
    {
        this();
        setText(Integer.toString(initial_value));
    }

    private SeedField()
    {
        setColumns(Style.text_field_size);
        addActionListener((ActionEvent _)->
        { determineInput(); });
    }

    public int getValue()
    {
        if(determineInput() != InputType.Value)
        { throw new IllegalStateException("SeedField does not contain a value"); }

        return Integer.parseInt(this.getText());
    }

    public SeedProperty.Macro getMacro()
    {
        if(determineInput() != InputType.Macro)
        { throw new IllegalStateException("SeedField does not contain a macro"); }

        int i = 0;
        for(; i < SeedProperty.Macro.values().length; ++i)
        {
            if(getText().equals(SeedProperty.macros.get(i)))
            { break; }
        }

        return SeedProperty.Macro.values()[i];
    }

    public InputType determineInput()
    {
        try
        { Integer.parseInt(getText()); }
        // If input is not an integer
        catch(NumberFormatException _)
        {
            if(SeedProperty.macros.contains(getText()))
            { return InputType.Macro; }

            return InputType.Invalid;
        }

        return InputType.Value;
    }
}