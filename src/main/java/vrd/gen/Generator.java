package vrd.gen;

import vrd.alg.Algorithm;
import vrd.util.Content;
import vrd.util.Util;

public class Generator {
    public Generator(Algorithm algorithm, BlendMode blend_mode)
    {
        this.algorithm = algorithm;
        this.blend_mode = blend_mode;
        // Set default name from algorithm's signature
        this.name = algorithm.getSignature().name;
    }

    public Content generate(int[] dimensions, int[] offset)
    {
        // Validate dimensionality of arguments
        if(dimensions.length != algorithm.getSignature().dimensionality ||
           offset.length != algorithm.getSignature().dimensionality)
        { throw new IllegalArgumentException("Invalid dimensionality of arguments"); }

        Content content = new Content(dimensions);

        int[] indices;
        for(int i = 0; i < content.getSize(); ++i)
        {
            indices = content.mapIndexToIndices(i);

            content.set(indices, algorithm.get(Util.arraySum(indices, offset)));
        }

        return content;
    }

    public Algorithm algorithm;
    public BlendMode blend_mode;
    public String name;
}


// maybe: generate the whole content with offset on each update (scale, move, reseed) instead of getting single values every frame