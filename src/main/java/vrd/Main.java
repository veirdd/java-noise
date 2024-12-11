package vrd;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import vrd.gen.BlendMode;
import vrd.gen.Generator;
import vrd.render.Renderer;
import vrd.render.view.Heightmap1d;
import vrd.ui.Ui;
import vrd.ui.std.Canvas;
import vrd.util.Content;

class Main
{
    public static void main(String[] args)
    { (new Main()).run(); }

    private void run()
    {
        this.generator_list = new ArrayList<>();
        this.canvas = new Canvas();
        this.renderer = new Renderer(this.canvas, new Heightmap1d());//d

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            ui = new Ui(generator_list, canvas, ()->
            { generate(); });
        });
    }

    private void generate()
    {
        ArrayList<Generator> active_generator_list = getActiveGenerators();
        // Clear canvas and abort generation if no generators are active
        if(active_generator_list.size() == 0)
        {
            renderer.render(null);
            return;
        }

        ArrayList<Content> output_list = new ArrayList<>();
        Content content = new Content(this.renderer.view.getRequiredContentSize(this.canvas));//d //todo set view

        // Create generator output content layers
        try
        {
            for(Generator generator : active_generator_list)
            {
                output_list.add(generator.generate(
                    content.dimensions, 
                    new int[content.getDimensionality()]));
            }
        }
        catch(IllegalArgumentException _)
        {
            ui.log("Unable to generate: generator dimensionalities mismatch");
        }

        // Blend layers using corresponding generator's blend modes
        for(int i = 0; i < output_list.size(); ++i)
        {
            content = Content.combine(
                content, 
                output_list.get(i), 
                BlendMode.getOperation(active_generator_list.get(i).blend_mode));
        }

        this.renderer.render(content);
    }

    private ArrayList<Generator> getActiveGenerators()
    {
        ArrayList<Generator> active_generator_list = new ArrayList<>();

        for(Generator generator : generator_list)
        {
            if(generator.enabled)
            { active_generator_list.add(generator); }
        }

        return active_generator_list;
    }

    private ArrayList<Generator> generator_list;
    private Canvas canvas;
    private Renderer renderer;
    private Ui ui;
}