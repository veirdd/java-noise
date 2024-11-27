package vrd.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;

import vrd.gen.Generator;
import vrd.ui.GeneratorTile.ButtonName;

public class GeneratorsPanel extends ScrollPane
{
    public GeneratorsPanel(ArrayList<Generator> generators_list)
    {        
        this.generators_list = generators_list;

        this.plus = new AddGeneratorButton("+", this.generators_list, ()->
        { updateComponents(); });
            this.plus.setAlignmentX(LEFT_ALIGNMENT);

        updateComponents();
    }

    private void updateComponents()
    {
        this.panel.removeAll();

        for(int i = 0; i < this.generators_list.size(); ++i)
        {
            final int I = i;

            GeneratorTile gen_tile = new GeneratorTile(
                this.generators_list.get(i), 
                (MoveTileOperation.Direction direction)->
                { moveTile(I, direction); });

            gen_tile.setAlignmentX(LEFT_ALIGNMENT);

            // Disable move buttons
            if(this.generators_list.size() == 1)
            {
                gen_tile.setButtonEnabled(ButtonName.Up, false);
                gen_tile.setButtonEnabled(ButtonName.Down, false);
            }
            else if(i == 0)
            { gen_tile.setButtonEnabled(ButtonName.Up, false); }
            else if(i == this.generators_list.size() - 1)
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
        { Collections.swap(this.generators_list, index, index - 1); }
        else
        { Collections.swap(this.generators_list, index, index + 1); }

        updateComponents();
    }

    private final ArrayList<Generator> generators_list;
    private final Button plus;
}