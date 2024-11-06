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

    public Content generate(int[] size, int[] offset)
    {
        // Validate dimensionality of arguments
        assert size.length == algorithm.dimensionality;
        assert offset.length == algorithm.dimensionality;

        Content content = new Content(size);

        for(Content.Iterator it = content.iterator(); !it.end(); it.next())
        {
            content.set(it.index, algorithm.get(Util.add(it.index, offset)));
            // Apply scale in noise and offset here (scale is a noise property and offset is a content property)
        }

        return content;
    }

    protected final Noise algorithm;
    protected final BlendMode blend_mode;
}


// maybe actually generate the whole content with offset on each update (scale, move, reseed) instead of getting single values every frame