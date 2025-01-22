package vd.gen.alg.concrete;

import vd.gen.alg.Algorithm;
import vd.gen.alg.AlgorithmList;
import vd.gen.alg.Signature;
import vd.gen.alg.property.FloatProperty;
import vd.gen.alg.property.Property;

public class Sine implements Algorithm
{
    enum PropertyId
    {
        Scale,
        Offset
    }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.Sine, "Sine", 0); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty("Scale", this.scale),
            new FloatProperty("Offset", this.offset)
        };
    }

    @Override
    public void setProperties(Property[] properties)
    {
        this.scale = ((FloatProperty)properties[PropertyId.Scale.ordinal()]).value;
        this.offset = ((FloatProperty)properties[PropertyId.Offset.ordinal()]).value;
    }    

    @Override
    public float get(int[] pos)
    {
        float value = 1;

        for(int i = 0; i < pos.length; ++i)
        { value *= Math.sin(pos[i] / this.scale - offset); }

        return value;
    }

    float scale = 1;
    float offset = 0;
}