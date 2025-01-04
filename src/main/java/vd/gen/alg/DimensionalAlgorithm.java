package vd.gen.alg;

public abstract class DimensionalAlgorithm implements Algorithm
{
    @Override
    public float get(int[] pos)
    {
        if(!isDimensionalityCompatible(pos.length))
        { throw new IllegalArgumentException("Argument dimensionality mismatch"); }

        return getImpl(pos);
    }

    @Override
    public boolean isDimensionalityCompatible(int dimensionality)
    {
        if(dimensionality == 0 ||
           this.getSignature().dimensionality == 0 ||
           dimensionality == this.getSignature().dimensionality)
        { return true; }
        return false;
    }

    protected abstract float getImpl(int[] pos);
}