package vrd.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane
{
    public ScrollPane()
    {
        super(null, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.panel = new JPanel();
            this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
            this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setViewportView(this.panel);

        getVerticalScrollBar().setUnitIncrement(5);
    }
    
    protected final JPanel panel;
}