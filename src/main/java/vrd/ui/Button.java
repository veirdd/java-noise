package vrd.ui;

import javax.swing.JButton;

public class Button extends JButton
{
    public Button(String text)
    {
        super(text);

        this.setFocusable(false);
    }
}