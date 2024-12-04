package vrd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import vrd.gen.Generator;
import vrd.ui.MoveTileOperation.Direction;
import vrd.ui.gen_dialog.GeneratorDialog;
import vrd.ui.std.Button;

public class GeneratorTile extends JPanel
{
    // todo: delete button for generator lmao there's none
    public GeneratorTile(
        Generator generator, 
        MoveTileOperation move_operation, 
        Runnable remove_operation, 
        Runnable update_notifier)
    {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        
        this.generator = generator;

        this.update_notifier = update_notifier;

        this.up_button = new Button("⬆");
            this.up_button.addActionListener((ActionEvent _)->
            { move_operation.request(Direction.Up); });

        this.down_button = new Button("⬇");
            this.down_button.addActionListener((ActionEvent _)->
            { move_operation.request(Direction.Down); });

        this.modify_button = new Button("...");
            this.modify_button.addActionListener((ActionEvent _)->
            { openDialog(); });
        
        this.remove_button = new Button("✖");
            this.remove_button.addActionListener((ActionEvent _)->
            { remove_operation.run(); });
        
        this.name = new JLabel(this.generator.name);
            this.name.setPreferredSize(new Dimension(100, this.modify_button.getPreferredSize().height));

        this.left_panel = new JPanel();
            this.left_panel.setMinimumSize(this.name.getPreferredSize());
            this.left_panel.add(this.name);

        this.right_panel = new JPanel();
            this.right_panel.add(this.up_button);
            this.right_panel.add(this.down_button);
            this.right_panel.add(this.modify_button);
            this.right_panel.add(this.remove_button);

        this.add(this.left_panel, BorderLayout.WEST);
        this.add(this.right_panel, BorderLayout.EAST);
    }

    public void setDirectionEnabled(Direction direction, boolean enabled)
    {
        if(direction == Direction.Up)
        { this.up_button.setEnabled(enabled); }
        else
        { this.down_button.setEnabled(enabled); }
    }

    private void openDialog()// todo: the same method is in addgenbutton so put them both in gendialog (it has to be not null in cosntructor then)
    {
        if(this.dialog == null || !this.dialog.isVisible())
        {
            this.dialog = new GeneratorDialog(
                (JFrame)SwingUtilities.getWindowAncestor(this),
                this.generator,
                (Generator generator)->
                { onDialogSave(generator); });
        }
    }

    private void onDialogSave(Generator generator)
    {
        // can't just assign the whole generator at once because it's f java ofc
        this.generator.algorithm = generator.algorithm;
        this.generator.name = generator.name;

        this.update_notifier.run();
    }

    private JPanel left_panel;
        private JLabel name;
    private JPanel right_panel;
        private Button up_button;
        private Button down_button;
        private Button modify_button;
        private Button remove_button;

    private Generator generator;
    private GeneratorDialog dialog;
    // Used to notify about changes to generator_list
    private final Runnable update_notifier;
}