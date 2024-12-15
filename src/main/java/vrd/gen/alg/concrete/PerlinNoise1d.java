package vrd.gen.alg.concrete;

import vrd.gen.alg.Signature;
import vrd.gen.alg.noise.Noise1d;
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
    public void seed(SeedProperty seed_property)
    {
        super.seed(seed_property);
        this.i = 0;
        this.vertex1 = random_seeded.nextFloat();
        this.vertex2 = random_seeded.nextFloat();
    }

    @Override
    protected float getImpl(int[] pos)
    {
        // Generate a new vertex when the whole curve is interpolated
        if((float)pos[0] / (this.scale * (this.i + 1)) >= 1)
        {
            this.vertex1 = this.vertex2;
            this.vertex2 = random_seeded.nextFloat();
            ++i;
        }

        // This evaluates to progression percentage between edge values
        float weight = (pos[0] % this.scale) / this.scale;

        return interpolate(weight, vertex1, vertex2);
    }

    private float interpolate(float weight, float y1, float y2)
    { return (float)((y2 - y1) * (3 * Math.pow(weight, 2) - 2 * Math.pow(weight, 3)) + y1); }

    private int i = 0;
    private float vertex1;
    private float vertex2;
}