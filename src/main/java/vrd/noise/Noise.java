package vrd.noise;

public abstract class Noise
{
    public Noise(int dimensionality)
    {
        this.dimensionality = dimensionality;
    }

    public abstract float get(int[] pos);

    public final int dimensionality;
}