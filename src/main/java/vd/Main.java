package vd;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import vd.gen.BlendMode;
import vd.gen.Generator;
import vd.gen.Settings;
import vd.gen.alg.concrete.custom_world.cwColor;
import vd.gen.alg.concrete.custom_world.cwLevel;
import vd.gen.alg.concrete.custom_world.cwNoise;
import vd.render.Renderer;
import vd.render.view.ViewList;
import vd.ui.Ui;
import vd.ui.std.Canvas;
import vd.util.Content;

class Main
{
    public static void main(String[] args)
    { (new Main()).run(); }

    private void run()
    {
        this.generator_list = new ArrayList<>();
        this.renderer = new Renderer(new Canvas(), null);
        this.settings = new Settings();

        //d
            this.generator_list.add(new Generator(new cwLevel(20), BlendMode.Add));
            this.generator_list.add(new Generator(new cwNoise(50), BlendMode.Multiply));
            this.generator_list.add(new Generator(new cwColor(), BlendMode.Add));
            this.renderer.view = ViewList.makeViewFromId(ViewList.Id.CustomWorld);

        // Run AWT Component operations in ED thread
        SwingUtilities.invokeLater(()->
        {
            ui = new Ui(this.generator_list, this.renderer, this.settings, ()->
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
        Content content = new Content(this.renderer.getRequiredContentSize());

        // Create generator output content layers
        try
        {
            for(Generator generator : active_generator_list)
            {
                output_list.add(generator.generate(
                    content.dimensions,
                    this.settings));
            }
        }
        catch(IllegalArgumentException _)
        {
            ui.log("Unable to render: invalid view for generator output");
            renderer.render(null);
            return;
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
    private Renderer renderer;
    private Ui ui;
    private Settings settings;
}