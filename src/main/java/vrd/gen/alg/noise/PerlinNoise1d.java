package vrd.gen.alg.noise;

import vrd.gen.alg.Signature;
import vrd.gen.alg.property.SeedProperty;
import vrd.gen.alg.AlgorithmList;

public class PerlinNoise1d extends Noise1d
{
    @Override
    public Signature getSignature()
    {
        return new Signature(AlgorithmList.Id.PerlinNoise1d, "PerlinNoise1D", 1);
    }

    @Override
    public void reseed(SeedProperty seed_property) {}

    @Override
    protected float algorithm(int[] pos)
    {
        return 0;
    }
}