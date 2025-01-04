package vd.ui.std;

import java.text.ParseException;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class IntSpinner extends JSpinner
{
    public IntSpinner(int init_value, int min_value, int max_value)
    { super(new SpinnerNumberModel(init_value, min_value, max_value, 1)); }

    public boolean validateInput()
    {
        try
        { commitEdit(); }
        // If input is not a float
        catch(ParseException _)
        { return false; }

        return true;
    }

    public int getInt()
    {
        if(validateInput())
        { return (int)getValue(); }
        else
        { throw new IllegalStateException("Value of IntSpinner accessed without validation"); }
    }
}