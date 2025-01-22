package vd.gen.alg;

import java.util.Random;

import vd.gen.alg.property.SeedProperty;

public interface Seeded extends Algorithm
{
    // Calling this default function respects the Algorithm's SeedProperty
    default void seed(SeedProperty default_seed_property)
    {
        if(setSeedFromProperty(random_seeded, getSeedProperty()))
        { return; }
        else
        { setSeedFromProperty(random_seeded, default_seed_property); }
    }

    // Should return the SeedProperty of the implementation
    SeedProperty getSeedProperty();

    Random random_seeded = new Random();

    private static boolean setSeedFromProperty(Random random, SeedProperty seed_property)
    {
        if(seed_property.macro == SeedProperty.Macro.Default)
        { return false; }
        else if(seed_property.macro == SeedProperty.Macro.Random)
        { random.setSeed(System.nanoTime()); }
        else // null
        { random.setSeed(seed_property.value); }

        return true;
    }
}