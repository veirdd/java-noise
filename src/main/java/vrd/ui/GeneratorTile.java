package vrd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vrd.gen.Generator;
import vrd.ui.MoveTileOperation.Direction;
import vrd.ui.gen_dialog.GeneratorDialog;
import vrd.ui.std.Button;

public class GeneratorTile extends JPanel
{
    enum ButtonName
    {
        Up,
        Down,
        Modify
    }

    public GeneratorTile(Generator generator, MoveTileOperation move_operation)
    {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        
        this.generator = generator;

        this.up_button = new Button("⬆");
            this.up_button.addActionListener((ActionEvent _)->
            { move_operation.request(Direction.Up); });

        this.down_button = new Button("⬇");
            this.down_button.addActionListener((ActionEvent _)->
            { move_operation.request(Direction.Down); });

        this.modify_button = new Button("...");

        this.name = new JLabel("hej");//d
            this.name.setPreferredSize(modify_button.getPreferredSize());

        this.left_panel = new JPanel();
            this.left_panel.add(this.name);

        this.right_panel = new JPanel();
            this.right_panel.add(this.up_button);
            this.right_panel.add(this.down_button);
            this.right_panel.add(this.modify_button);

        this.add(this.left_panel, BorderLayout.WEST);
        this.add(this.right_panel, BorderLayout.EAST);
    }

    public void setButtonEnabled(ButtonName button_name, boolean enabled)
    {
        Button button;

        switch(button_name)
        {
            case Up:
                button = this.up_button;
                break;
            case Down:
                button = this.down_button;
                break;
            default:
                button = this.modify_button;
                break;
        }

        button.setEnabled(enabled);
    }

    private JPanel left_panel;
        private JLabel name;
    private JPanel right_panel;
        private Button up_button;
        private Button down_button;
        private Button modify_button;

    Generator generator;
    private GeneratorDialog dialog;
}