package vrd.alg.noise;

import vrd.alg.Algorithm;
import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;

public abstract class Noise extends Algorithm
{ 
    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty(this.scale, "Scale")
        };
    }

    protected float scale;
}