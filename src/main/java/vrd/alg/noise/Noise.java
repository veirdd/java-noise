package vrd.alg.noise;

import vrd.alg.Algorithm;
import vrd.alg.Property;

public abstract class Noise implements Algorithm
{
    @Override
    public float get(int[] pos)
    {
        if(pos.length != this.getDimensionality())
        { throw new IllegalArgumentException("Invalid dimensionality of position"); }

        return algorithm(pos);
    }
    
    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new Property(this.scale, "Scale", Property.Constraint.None)
        };
    }

    // Outputs the noise value at given pos
    protected abstract float algorithm(int[] pos);

    protected float scale;
}