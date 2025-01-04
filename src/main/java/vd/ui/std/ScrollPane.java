package vd.ui.std;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane
{
    public ScrollPane()
    {
        super(null, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.panel = new Panel();
            this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
            this.panel.setBorder(Style.empty_border);
            setViewportView(this.panel);

        getVerticalScrollBar().setUnitIncrement(5);
    }
    
    protected final Panel panel;
}