package vrd.alg.noise;

import vrd.alg.Signature;
import vrd.alg.SignatureList;

public class PerlinNoise1d extends Noise1d
{
    @Override
    protected float algorithm(int[] pos)
    {
        return 0;
    }

    @Override
    public Signature getSignature()
    {
        return new Signature(SignatureList.Id.PerlinNoise1d, "PerlinNoise1D", 1);
    }
}