package vrd.alg;

import vrd.alg.property.FloatProperty;
import vrd.alg.property.Property;

public class ConstantValue implements Algorithm
{
    public ConstantValue() {}

    public ConstantValue(int value)
    { this.value = value; }

    @Override
    public float get(int[] pos)
    { return this.value; }

    @Override
    public Signature getSignature()
    {
        return new Signature(SignatureList.Id.ConstantValue, "ConstantValue", 0);
    }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty(this.value, "Value")
        };
    }

    private float value;
}