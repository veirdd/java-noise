package vrd.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;

import vrd.gen.Generator;
import vrd.ui.GeneratorTile.ButtonName;
import vrd.ui.std.Button;
import vrd.ui.std.ScrollPane;

public class GeneratorPanel extends ScrollPane
{
    public GeneratorPanel(ArrayList<Generator> generator_list)
    {        
        this.generator_list = generator_list;

        this.plus = new AddGeneratorButton("+", this.generator_list, ()->
        { updateComponents(); });
            this.plus.setAlignmentX(LEFT_ALIGNMENT);

        updateComponents();
    }

    private void updateComponents()
    {
        this.panel.removeAll();

        for(int i = 0; i < this.generator_list.size(); ++i)
        {
            final int I = i;

            GeneratorTile gen_tile = new GeneratorTile(
                this.generator_list.get(i), 
                (MoveTileOperation.Direction direction)->
                { moveTile(I, direction); });

            gen_tile.setAlignmentX(LEFT_ALIGNMENT);

            // Disable move buttons
            if(this.generator_list.size() == 1)
            {
                gen_tile.setButtonEnabled(ButtonName.Up, false);
                gen_tile.setButtonEnabled(ButtonName.Down, false);
            }
            else if(i == 0)
            { gen_tile.setButtonEnabled(ButtonName.Up, false); }
            else if(i == this.generator_list.size() - 1)
            { gen_tile.setButtonEnabled(ButtonName.Down, false);}

            this.panel.add(gen_tile);

            this.panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        this.panel.add(this.plus);

        this.panel.revalidate();
    }

    private void moveTile(int index, MoveTileOperation.Direction direction)
    {
        if(direction == MoveTileOperation.Direction.Up)
        { Collections.swap(this.generator_list, index, index - 1); }
        else
        { Collections.swap(this.generator_list, index, index + 1); }

        updateComponents();
    }

    private final ArrayList<Generator> generator_list;
    private final Button plus;
}