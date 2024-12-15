package vrd.gen.alg.noise;

import vrd.gen.alg.DimensionalAlgorithm;
import vrd.gen.alg.Seeded;
import vrd.gen.alg.property.FloatProperty;
import vrd.gen.alg.property.Property;
import vrd.gen.alg.property.SeedProperty;

public abstract class Noise extends DimensionalAlgorithm implements Seeded
{ 
    private enum PropertyId
    {
        Scale,
        Seed
    }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new FloatProperty("Scale", this.scale),
            getSeedProperty()
        };
    }

    @Override
    public void setProperties(Property[] properties)
    {
        this.scale = ((FloatProperty)properties[PropertyId.Scale.ordinal()]).value;
        this.seed_property = (SeedProperty)properties[PropertyId.Seed.ordinal()];
    }

    @Override
    public SeedProperty getSeedProperty()
    { return this.seed_property; }

    protected float scale = 1;
    protected SeedProperty seed_property = new SeedProperty("Seed", SeedProperty.Macro.Default);
}