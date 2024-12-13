package vrd.gen.alg;

import vrd.gen.alg.property.Property;
import vrd.gen.alg.property.SeedProperty;

public class Random extends Algorithm //todo seeds
{
    private enum PropertyId
    {
        Seed
    }

    public Random()
    {
        this.seed_macro = SeedProperty.Macro.Random;

        this.random_time = new java.util.Random();
    }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.Random, "Random", 0); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            makeSeedProperty()
        };
    }

    @Override
    public void setProperties(Property[] properties)
    {
        SeedProperty seed_property = (SeedProperty)properties[PropertyId.Seed.ordinal()];
        if(seed_property.macro == null)
        {
            this.seed = seed_property.value;
            this.seed_macro = null;
        }
        else
        { this.seed_macro = seed_property.macro; }
    }

    @Override
    public void reseed(SeedProperty seed_property)
    {
        if(this.seed_macro == SeedProperty.Macro.Random)
        { return; }
        else if(this.seed_macro == SeedProperty.Macro.Default)
        { this.random_seeded = new java.util.Random(seed_property.getSeedInt()); }//todo
        else
        { this.random_seeded = new java.util.Random(this.seed); }
    }

    @Override
    protected float algorithm(int[] pos)
    {
        if(this.seed_macro == SeedProperty.Macro.Random)
        { return this.random_time.nextFloat(); } 

        // macro == Default || macro == null
        return this.random_seeded.nextFloat();
    }

    private SeedProperty makeSeedProperty()
    {
        // If this.seed_macro is null, then the SeedProperty with value is returned
        SeedProperty seed_property = new SeedProperty("Seed", this.seed);
        seed_property.macro = this.seed_macro;

        return seed_property;
    }

    private int seed;
    private SeedProperty.Macro seed_macro;

    private java.util.Random random_time;
    private java.util.Random random_seeded;
}