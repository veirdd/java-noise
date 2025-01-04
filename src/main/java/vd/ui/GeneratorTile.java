package vd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import vd.gen.Generator;
import vd.ui.MoveTileOperation.Direction;
import vd.ui.gen_dialog.GeneratorDialog;
import vd.ui.std.Button;
import vd.ui.std.Panel;
import vd.ui.std.Style;

public class GeneratorTile extends Panel
{
    public GeneratorTile(
        Generator generator, 
        MoveTileOperation move_operation, 
        Runnable remove_operation, 
        Runnable update_notifier)
    {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setBorder(Style.etched_border);
        setLayout(new BorderLayout());
        
        this.generator = generator;
        this.enabled = this.generator.enabled;
        this.update_notifier = update_notifier;

        this.power_button = new Button("ON");
            this.power_button.addActionListener((ActionEvent _)->
            { switchEnabled(); });
            // Visual
            this.power_button.setBackground(Style.good_color);
            this.power_button.setMargin(new Insets(
                this.power_button.getMargin().top, 0, 
                this.power_button.getMargin().bottom, 0));
            this.power_button.setPreferredSize(new Dimension(
                (int)(this.power_button.getPreferredSize().height * 2), 
                this.power_button.getPreferredSize().height));

        this.up_button = new Button("↑");
            this.up_button.addActionListener((ActionEvent _)->
            { move_operation.request(Direction.Up); });

        this.down_button = new Button("↓");
            this.down_button.addActionListener((ActionEvent _)->
            { move_operation.request(Direction.Down); });

        this.modify_button = new Button("...");
            this.modify_button.addActionListener((ActionEvent _)->
            { openDialog(); });
        
        this.remove_button = new Button("✖");
            this.remove_button.addActionListener((ActionEvent _)->
            { remove_operation.run(); });
        
        this.name_label = new JLabel(this.generator.name);
            this.name_label.setPreferredSize(new Dimension(100, this.modify_button.getPreferredSize().height));

        this.left_panel = new Panel();
            this.left_panel.setMinimumSize(this.name_label.getPreferredSize());
            this.left_panel.add(this.name_label);

        this.right_panel = new Panel();
            this.right_panel.add(this.power_button);
            this.right_panel.add(this.up_button);
            this.right_panel.add(this.down_button);
            this.right_panel.add(this.modify_button);
            this.right_panel.add(this.remove_button);

        this.add(this.left_panel, BorderLayout.WEST);
        this.add(this.right_panel, BorderLayout.EAST);

        setEnabled(this.generator.enabled);
    }

    public void setDirectionEnabled(Direction direction, boolean enabled)
    {
        if(direction == Direction.Up)
        { this.up_button.setEnabled(enabled); }
        else
        { this.down_button.setEnabled(enabled); }
    }

    public void setEnabled(boolean enabled)
    {
        if(enabled)
        {
            this.power_button.setText("ON");
            this.power_button.setBackground(Style.good_color);
            this.name_label.setEnabled(true);
            this.generator.enabled = true;

            this.enabled = true;
        }
        else
        {
            this.power_button.setText("OFF");
            this.power_button.setBackground(Style.bad_color);
            this.name_label.setEnabled(false);
            this.generator.enabled = false;

            this.enabled = false;
        }
    }

    public boolean isEnabled()
    { return this.enabled; }

    private void switchEnabled()
    {
        if(this.enabled)
        { setEnabled(false); }
        else
        { setEnabled(true); }
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
        // can't just reassign the generator because it's f java ofc
        this.generator.algorithm = generator.algorithm;
        this.generator.blend_mode = generator.blend_mode;
        this.generator.name = generator.name;
        this.generator.enabled = generator.enabled;

        this.update_notifier.run();
    }

    private Generator generator;
    private GeneratorDialog dialog;
    // Used to notify about changes to generator_list
    private boolean enabled;
    private final Runnable update_notifier;

    private Panel left_panel;
        private JLabel name_label;
    private Panel right_panel;
        private Button power_button;
        private Button up_button;
        private Button down_button;
        private Button modify_button;
        private Button remove_button;
}