package vrd.gen.alg.concrete;

import vrd.gen.alg.Signature;
import vrd.gen.alg.noise.Noise1d;
import vrd.gen.alg.property.SeedProperty;

import vrd.gen.alg.AlgorithmList;

import vrd.util.OpenSimplex2S;

public class SimplexNoise1d extends Noise1d
{
    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.SimplexNoise1d, "SimplexNoise [1D]", 1); }

    @Override
    public void seed(SeedProperty seed_property)
    {
        super.seed(seed_property);
        this.internal_seed = random_seeded.nextInt();
    }

    @Override
    protected float getImpl(int[] pos)
    {
        return OpenSimplex2S.noise2(this.internal_seed, pos[0] / this.scale, 0);
    }

    private int internal_seed;
}