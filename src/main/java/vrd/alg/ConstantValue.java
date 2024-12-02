package vrd.alg;

public abstract class ConstantValue implements Algorithm
{
    @Override
    public float get(int[] pos)
    {
        if(pos.length != this.getDimensionality())
        { throw new IllegalArgumentException("Invalid dimensionality of position"); }

        return 0;
    }

    @Override
    public Property[] getProperties()
    {
        return new Property[]{new FloatProperty(this.value, "Value")};
    }

    protected float value;
}