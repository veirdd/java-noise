package vrd.ui.std;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

public class FloatField extends JTextField
{
    public FloatField()
    {
        setColumns(Style.text_field_size);

        addActionListener((ActionEvent _)->
        { verifyContent(); });
    }

    public boolean verifyContent()
    {
        try
        { Float.parseFloat(this.getText()); }
        catch(NumberFormatException _)
        {
            this.setText("");

            return false;
        }

        return true;
    }
}