package vrd.alg.noise;

import vrd.alg.Signature;
import vrd.alg.SignatureList;

public class PerlinNoise2d extends Noise2d
{
    @Override
    protected float algorithm(int[] pos)
    {
        return 0;
    }

    @Override
    public Signature getSignature()
    {
        return new Signature(SignatureList.Id.PerlinNoise2d, "PerlinNoise2D", 2);
    }
}