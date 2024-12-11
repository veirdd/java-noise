package vrd.alg;

import vrd.alg.property.FloatProperty;

public class ConstantValue extends Algorithm
{
    private enum PropertyId
    {
        Value
    }

    public ConstantValue()
    {
        this.value = 0;
    }

    public ConstantValue(int value)
    { this.value = value; }

    @Override
    public float algorithm(int[] pos)
    {
        return this.value;
    }

    @Override
    public Signature getSignature()
    { return new Signature(SignatureList.Id.ConstantValue, "ConstantValue", 0); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty(this.value, "Value")
        };
    }

    @Override
    public void setProperties(Property[] properties)
    { this.value = ((FloatProperty)properties[PropertyId.Value.ordinal()]).value; }

    private float value;
}