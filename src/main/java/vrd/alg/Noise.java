package vrd.alg;

import vrd.alg.property.FloatProperty;
import vrd.alg.property.NaturalProperty;

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
            new FloatProperty(this.scale, "Scale"),
            new NaturalProperty(this.octave_count, 1, 16, "Octave count")
        };
    }

    @Override
    public void setProperties(Property[] properties)
    // todo: this will obliterate itself if invalid properties are passed so redesign lol
    // actually just make a static method for generating property lists from uhh idk something
    {
        this.scale = ((FloatProperty)properties[PropertyId.Scale.ordinal()]).value;
        this.octave_count = ((NaturalProperty)properties[PropertyId.OctaveCount.ordinal()]).value;
    }

    protected float scale;
    protected int octave_count;
}