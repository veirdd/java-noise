package vrd.gen;

import vrd.noise.Noise;
import vrd.util.Content;
import vrd.util.Util;

public class Generator {
    public Generator(Noise algorithm, BlendMode blend_mode)
    {
        this.algorithm = algorithm;
        this.blend_mode = blend_mode;
    }

    public Content generate(int[] dimensions, int[] offset)
    {
        // Validate dimensionality of arguments
        if(dimensions.length != algorithm.dimensionality ||
           offset.length != algorithm.dimensionality)
        { throw new IllegalArgumentException(); }

        Content content = new Content(dimensions);

        int[] indices;
        for(int i = 0; i < content.getSize(); ++i)
        {
            indices = content.mapIndexToIndices(i);

            content.set(indices, algorithm.get(Util.arraySum(indices, offset)));
            // Apply scale in noise and offset here (scale is a noise property and offset is a content property)
        }

        return content;
    }

    protected final Noise algorithm;
    protected final BlendMode blend_mode;
}


// todo: maybe actually generate the whole content with offset on each update (scale, move, reseed) instead of getting single values every frame