package vrd.ui.std;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

public class FloatField extends JTextField
{
    public FloatField(float initial_value)
    {
        setColumns(Style.text_field_size);
        setText(Float.toString(initial_value));

        addActionListener((ActionEvent _)->
        { validateInput(); });
    }

    public float getValue()
    {
        if(validateInput())
        { return Float.parseFloat(this.getText()); }
        else
        { throw new IllegalStateException("Value of FloatField accessed without validation"); }
    }

    public boolean validateInput()
    {
        try
        { Float.parseFloat(this.getText()); }
        catch(NumberFormatException _)
        { return false; }

        return true;
    }
}