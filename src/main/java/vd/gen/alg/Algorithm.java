package vd.gen.alg;

import vd.gen.alg.property.Property;
import vd.gen.alg.property.SeedProperty;

public interface Algorithm
{
    float get(int[] pos);

    default boolean isDimensionalityCompatible(int dimensionality)
    { return true; }
    
    Signature getSignature();
    Property[] getProperties();
    void setProperties(Property[] properties);

    // Used for seeding Algorithms that implement Seeded
    default void seed(SeedProperty default_seed_property) {};
}