package vrd.gen.alg.noise;

import vrd.gen.alg.Algorithm;
import vrd.gen.alg.property.FloatProperty;
import vrd.gen.alg.property.IntProperty;
import vrd.gen.alg.property.Property;

public abstract class Noise extends Algorithm
{ 
    private enum PropertyId
    {
        Scale,
        OctaveCount
    }

    public Noise()
    {
        this.scale = 1;
        this.octave_count = 1;
    }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty("Scale", this.scale),
            new IntProperty("Octave count", this.octave_count, 1, 16)
        };
    }

    @Override
    public void setProperties(Property[] properties)
    // todo: this will obliterate itself if invalid properties are passed so redesign lol
    // actually just make a static method for generating property lists from uhh idk something bc this looks cancerous
    {
        this.scale = ((FloatProperty)properties[PropertyId.Scale.ordinal()]).value;
        this.octave_count = ((IntProperty)properties[PropertyId.OctaveCount.ordinal()]).value;
    }

    protected float scale;
    protected int octave_count;
}