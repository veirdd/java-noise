package vd.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;

import vd.gen.Generator;
import vd.ui.std.Button;
import vd.ui.std.ScrollPane;

public class GeneratorPanel extends ScrollPane
{
    public GeneratorPanel(ArrayList<Generator> generator_list)
    {        
        this.generator_list = generator_list;

        this.plus = new AddGeneratorButton(
            "+", 
            this.generator_list, 
            () ->
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
                (MoveTileOperation.Direction direction) ->
                { moveTile(I, direction); },
                () ->
                { removeTile(I); },
                () ->
                { updateComponents(); });

            gen_tile.setAlignmentX(LEFT_ALIGNMENT);

            // Disable move buttons
            if(this.generator_list.size() == 1)
            {
                gen_tile.setDirectionEnabled(MoveTileOperation.Direction.Up, false);
                gen_tile.setDirectionEnabled(MoveTileOperation.Direction.Down, false);
            }
            else if(i == 0)
            { gen_tile.setDirectionEnabled(MoveTileOperation.Direction.Up, false); }
            else if(i == this.generator_list.size() - 1)
            { gen_tile.setDirectionEnabled(MoveTileOperation.Direction.Down, false);}

            this.panel.add(gen_tile);

            this.panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        this.panel.add(this.plus);

        // Java       itself in amusing ways if we don't call these
        this.panel.repaint();
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

    private void removeTile(int index)
    {
        this.generator_list.remove(index);

        updateComponents();
    }

    private final ArrayList<Generator> generator_list;
    private final Button plus;
}