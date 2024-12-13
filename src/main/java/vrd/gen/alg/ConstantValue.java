package vrd.gen.alg;

import vrd.gen.alg.property.FloatProperty;
import vrd.gen.alg.property.Property;
import vrd.gen.alg.property.SeedProperty;

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

    @Override
    public void reseed(SeedProperty seed_property) {}

    @Override
    protected float algorithm(int[] pos)
    {
        return this.value;
    }

    private float value;
}