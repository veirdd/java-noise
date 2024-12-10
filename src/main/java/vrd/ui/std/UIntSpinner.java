package vrd.ui.std;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class UIntSpinner extends JSpinner
{
    public UIntSpinner(int initial_value)
    {
        super(new SpinnerNumberModel(initial_value, 1, 1000, 1));

        if(initial_value <= 0)
        { throw new IllegalArgumentException("UIntSpinner initialized with a nonpositive integer"); }
    }
}