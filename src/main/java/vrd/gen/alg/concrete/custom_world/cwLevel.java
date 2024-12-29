package vrd.gen.alg.concrete.custom_world;

import vrd.gen.alg.AlgorithmList;
import vrd.gen.alg.DimensionalAlgorithm;
import vrd.gen.alg.Signature;
import vrd.gen.alg.property.FloatProperty;
import vrd.gen.alg.property.Property;

public class cwLevel extends DimensionalAlgorithm
{
    private enum PropertyId
    {
        Value
    }

    public cwLevel()
    { this(0); }

    public cwLevel(float value)
    { this.value = value; }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.cwLevel, "cwLevel [3D]", 3); }

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
    protected float getImpl(int[] pos)
    {
        // First layer
        if(pos[2] == 0)
        { return this.value; }
        return 0;
    }

    private float value;
}