package vrd.alg.noise;

import vrd.alg.Algorithm;

public abstract class Noise implements Algorithm
{
    @Override
    public float get(int[] pos)
    {
        if(pos.length != this.getDimensionality())
        { throw new IllegalArgumentException("Invalid dimensionality of position"); }

        return algorithm(pos);
    }

    // Outputs the noise value at given pos
    protected abstract float algorithm(int[] pos);
}