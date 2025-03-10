package vd.gen.alg.concrete.custom_world;

import vd.gen.alg.AlgorithmList;
import vd.gen.alg.DimensionalAlgorithm;
import vd.gen.alg.Seeded;
import vd.gen.alg.Signature;
import vd.gen.alg.property.FloatProperty;
import vd.gen.alg.property.Property;
import vd.gen.alg.property.SeedProperty;

public class cwColorRandomizer extends DimensionalAlgorithm implements Seeded
{
    private enum PropertyId
    {
        Randomness,
        Seed
    }

    public cwColorRandomizer()
    { this(1); }

    public cwColorRandomizer(float randomness)
    { this.randomness = randomness; }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.cwColorRandomizer, "cwColorRandomizer [3D]", 3); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty("Randomness", this.randomness),
            getSeedProperty()
        };
    }

    @Override
    public void setProperties(Property[] properties)
    {
        this.randomness = ((FloatProperty)properties[PropertyId.Randomness.ordinal()]).value;
        this.seed_property = (SeedProperty)properties[PropertyId.Seed.ordinal()];
    }

    @Override
    protected float getImpl(int[] pos)
    {
        // Second layer
        if(pos[2] == 1)
        {
            return Math.round(random_seeded.nextFloat() * this.randomness) * 1001001;
        }
        return 0;
    }
    
    @Override
    public SeedProperty getSeedProperty()
    { return this.seed_property; }

    private float randomness;
    private SeedProperty seed_property = new SeedProperty("Seed", SeedProperty.Macro.Default);
}