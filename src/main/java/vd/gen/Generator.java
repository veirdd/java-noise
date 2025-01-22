package vd.gen;

import vd.gen.alg.Algorithm;
import vd.util.Content;

public class Generator {
    public Generator(Algorithm algorithm, BlendMode blend_mode)
    {
        this.algorithm = algorithm;
        this.blend_mode = blend_mode;
        // Set default name from algorithm's signature
        this.name = algorithm.getSignature().name;
        this.enabled = true;
    }

    public Content generate(int[] dimensions, Settings settings)
    {
        Content content = new Content(dimensions);

        // Apply settings
            algorithm.seed(settings.seed_property);

        int[] indices;
        for(int i = 0; i < content.getSize(); ++i)
        {
            indices = content.mapIndexToIndices(i);

            content.set(indices, algorithm.get(indices));
        }

        return content;
    }

    @Override
    public String toString()
    {
        return
            "[ " +
            " Name: " + name +
            " Algorithm: " + algorithm.getSignature().name +
            " Blend mode: " + blend_mode.name() +
            " Enabled: " + Boolean.toString(enabled) +
            " ]";
    }

    public String name;
    public Algorithm algorithm;
    public BlendMode blend_mode;
    public boolean enabled;
}


//todo generation offset
//todo throw correctness for all functions