package vrd.alg.noise;

import vrd.alg.Algorithm;
import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;
import vrd.alg.property.UIntProperty;

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
            new UIntProperty(this.octave_count, "Octave count")
        };
    }

    @Override
    public void setProperties(Property[] properties)
    // todo: this will obliterate itself if invalid properties are passed so redesign lol
    // actually just make a static method for generating property lists from uhh idk something
    {
        this.scale = ((FloatProperty)properties[PropertyId.Scale.ordinal()]).value;
        this.octave_count = ((UIntProperty)properties[PropertyId.OctaveCount.ordinal()]).value;
    }

    protected float scale;
    protected int octave_count;
}