package vrd.gen.alg.concrete;

import vrd.gen.alg.Signature;
import vrd.gen.alg.noise.Noise2d;
import vrd.gen.alg.AlgorithmList;

public class PerlinNoise2d extends Noise2d
{
    @Override
    public Signature getSignature()
    {
        return new Signature(AlgorithmList.Id.PerlinNoise2d, "PerlinNoise2D", 2);
    }

    @Override
    protected float getImpl(int[] pos)
    {
        return 0;
    }
}