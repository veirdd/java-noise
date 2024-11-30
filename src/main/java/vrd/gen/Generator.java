package vrd.gen;

import vrd.alg.Algorithm;
import vrd.util.Content;
import vrd.util.Util;

public class Generator {
    public Generator(Algorithm algorithm, BlendMode blend_mode)
    {
        this.algorithm = algorithm;
        this.blend_mode = blend_mode;
    }

    public Content generate(int[] dimensions, int[] offset)
    {
        // Validate dimensionality of arguments
        if(dimensions.length != algorithm.getDimensionality() ||
           offset.length != algorithm.getDimensionality())
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

    public final Algorithm algorithm;
    public final BlendMode blend_mode;
}


// maybe: generate the whole content with offset on each update (scale, move, reseed) instead of getting single values every frame