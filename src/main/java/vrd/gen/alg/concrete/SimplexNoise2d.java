package vrd.gen.alg.concrete;

import vrd.gen.alg.Signature;
import vrd.gen.alg.noise.Noise2d;
import vrd.gen.alg.property.SeedProperty;

import vrd.gen.alg.AlgorithmList;

import vrd.util.OpenSimplex2S;

public class SimplexNoise2d extends Noise2d
{
    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.SimplexNoise2d, "SimplexNoise [2D]", 2); }//todo make [#D] baked in

    @Override
    public void seed(SeedProperty seed_property)
    {
        super.seed(seed_property);
        this.internal_seed = random_seeded.nextInt();
    }

    @Override
    protected float getImpl(int[] pos)
    {
        return OpenSimplex2S.noise2(this.internal_seed, pos[0] / this.scale, pos[1] / this.scale);
    }

    private int internal_seed;
}