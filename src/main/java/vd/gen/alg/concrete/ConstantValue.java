package vd.gen.alg.concrete;

import vd.gen.alg.Algorithm;
import vd.gen.alg.AlgorithmList;
import vd.gen.alg.Signature;
import vd.gen.alg.property.FloatProperty;
import vd.gen.alg.property.Property;

public class ConstantValue implements Algorithm
{
    private enum PropertyId
    {
        Value
    }

    public ConstantValue()
    { this(0); }

    public ConstantValue(int value)
    { this.value = value; }

    @Override
    public float get(int[] pos)
    { return this.value; }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.ConstantValue, "ConstantValue", 0); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty("Value", this.value)
        };
    }

    @Override
    public void setProperties(Property[] properties)
    { this.value = ((FloatProperty)properties[PropertyId.Value.ordinal()]).value; }

    private float value = 0;
}