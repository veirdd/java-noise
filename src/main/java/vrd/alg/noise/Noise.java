package vrd.alg.noise;

import vrd.alg.Algorithm;
import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;

public abstract class Noise implements Algorithm
{
    @Override
    public float get(int[] pos)
    {
        // Dimensionality 0 is universal
        if(pos.length != this.getDimensionality() && this.getDimensionality() != 0)
        { throw new IllegalArgumentException("Invalid dimensionality of position"); }

        return algorithm(pos);
    }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty(this.scale, "Scale")
        };
    }

    // Outputs the noise value at given pos
    protected abstract float algorithm(int[] pos);

    protected float scale;
}