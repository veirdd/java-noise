package vd.gen.alg.concrete.custom_world;

import vd.gen.alg.AlgorithmList;
import vd.gen.alg.Signature;
import vd.gen.alg.concrete.SimplexNoise2d;

public class cwNoise extends SimplexNoise2d
{
    public cwNoise()
    { this(1); }

    public cwNoise(float scale)
    { this.scale = scale; }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.cwNoise, "cwNoise [3D]", 3); }

    @Override
    protected float getImpl(int[] pos)
    {
        if(pos[2] == 0)
        { return super.getImpl(pos); }
        else
        { return 0; }

    }
}