package vrd.ui.std;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class IntSpinner extends JSpinner
{
    public IntSpinner(int init_value, int min_value, int max_value)
    { super(new SpinnerNumberModel(init_value, min_value, max_value, 1)); }

    public int getInt()
    { return (int)getValue(); }
}