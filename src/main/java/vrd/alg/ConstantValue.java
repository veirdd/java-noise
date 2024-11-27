package vrd.alg;

public abstract class ConstantValue implements Algorithm
{
    public enum Properties
    {
        Value
    }

    @Override
    public float get(int[] pos)
    {
        if(pos.length != this.getDimensionality())
        { throw new IllegalArgumentException("Invalid dimensionality of position"); }

        return properties.getProperty(Properties.Value);
    }

    private AlgorithmProperties properties;
}