package vrd.noise;

public abstract class Noise
{
    public Noise(int dimensionality)
    {
        this.dimensionality = dimensionality;
    }

    public float get(int[] pos)
    {
        if(pos.length != this.dimensionality)
        { throw new IllegalArgumentException(); }

        return algorithm(pos);
    }
    
    public final int dimensionality;

    // Outputs the noise value at given pos
    protected abstract float algorithm(int[] pos);
}