package vrd.gen;

import java.util.ArrayList;

import vrd.noise.Noise;

public abstract class Generator {
    public Generator(Noise algorithm, BlendMode blend_mode)
    {
        this.algorithm = algorithm;
        this.blend_mode = blend_mode;
    }

    public abstract ArrayList generate(Object size, Object offset); // todo: u sure object is fine here lol
    // todo: add strategies for generating in different dimensions

    protected final Noise algorithm;
    protected final BlendMode blend_mode;
}


// maybe actually generate the whole content with offset on each update (scale, move, reseed) instead of getting single values every frame