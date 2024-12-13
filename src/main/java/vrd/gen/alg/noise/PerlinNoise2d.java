package vrd.gen.alg.noise;

import vrd.gen.alg.Signature;
import vrd.gen.alg.property.SeedProperty;
import vrd.gen.alg.AlgorithmList;

public class PerlinNoise2d extends Noise2d
{
    @Override
    public Signature getSignature()
    {
        return new Signature(AlgorithmList.Id.PerlinNoise2d, "PerlinNoise2D", 2);
    }

    @Override
    public void reseed(SeedProperty seed_property) {}

    @Override
    protected float algorithm(int[] pos)
    {
        return 0;
    }
}