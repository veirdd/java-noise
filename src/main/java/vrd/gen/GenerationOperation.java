package vrd.gen;

import vrd.gen.alg.property.SeedProperty;

@FunctionalInterface
public interface GenerationOperation
{
    // Contains the default SeedProperty for Algorithms requiring seeds to operate
    public void run(SeedProperty seed);
}