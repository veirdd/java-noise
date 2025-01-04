package vd.gen.alg.concrete;

import vd.gen.alg.AlgorithmList;
import vd.gen.alg.DimensionalAlgorithm;
import vd.gen.alg.Seeded;
import vd.gen.alg.Signature;
import vd.gen.alg.property.Property;
import vd.gen.alg.property.SeedProperty;

public class Random extends DimensionalAlgorithm implements Seeded
{
    private enum PropertyId
    {
        Seed
    }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.Random, "Random", 0); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            getSeedProperty()
        };
    }

    @Override
    public void setProperties(Property[] properties)
    {
        this.seed_property = (SeedProperty)properties[PropertyId.Seed.ordinal()];
    }    

    @Override
    protected float getImpl(int[] pos)
    {
        return random_seeded.nextFloat();
    }

    @Override
    public SeedProperty getSeedProperty()
    { return this.seed_property; }

    private SeedProperty seed_property = new SeedProperty("Seed", SeedProperty.Macro.Default);
}