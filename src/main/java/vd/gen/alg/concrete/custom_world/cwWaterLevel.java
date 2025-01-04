package vd.gen.alg.concrete.custom_world;

import vd.gen.alg.AlgorithmList;
import vd.gen.alg.DimensionalAlgorithm;
import vd.gen.alg.Signature;
import vd.gen.alg.property.FloatProperty;
import vd.gen.alg.property.Property;

public class cwWaterLevel extends DimensionalAlgorithm
{
    private enum PropertyId
    {
        Value
    }

    public cwWaterLevel()
    { this(0); }

    public cwWaterLevel(float value)
    { this.value = value; }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.cwWaterLevel, "cwWaterLevel [3D]", 3); }

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
        if(pos[2] == 0)
        { return this.value; }
        if(pos[0] == 0 && pos[1] == 0 && pos[2] == 2)
        { return 1; }
        if(pos[0] == 1 && pos[1] == 0 && pos[2] == 2)
        { return this.value; }
        return 0;
    }

    private float value;
}