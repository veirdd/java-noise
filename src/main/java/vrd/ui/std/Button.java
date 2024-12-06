package vrd.ui.std;

import javax.swing.JButton;

public class Button extends JButton
{
    public Button(String text)
    {
        super(text);

        setFocusable(false);
        setBackground(Style.enabled_color);
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);

        if(enabled)
        { setBackground(Style.enabled_color); }
        else
        { setBackground(Style.disabled_color); }
    }
}