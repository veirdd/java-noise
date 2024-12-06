package vrd.alg.noise;

import vrd.alg.Algorithm;
import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;

public abstract class Noise extends Algorithm
{ 
    private enum PropertyId
    {
        Scale
    }

    public Noise()
    {
        this.scale = 1;
    }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty(this.scale, "Scale"),
        };
    }

    @Override
    public void setProperties(Property[] properties)
    { this.scale = ((FloatProperty)properties[PropertyId.Scale.ordinal()]).value; }

    protected float scale;
}