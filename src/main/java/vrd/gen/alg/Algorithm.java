package vrd.gen.alg;

import vrd.gen.alg.property.Property;
import vrd.gen.alg.property.SeedProperty;

public abstract class Algorithm
{
    public float get(int[] pos) throws IllegalArgumentException
    {
        if(!isDimensionalityCompatible(pos.length))
        { throw new IllegalArgumentException("Argument dimensionality mismatch"); }

        return algorithm(pos);
    }

    public boolean isDimensionalityCompatible(int dimensionality)
    {
        if(dimensionality == 0 ||
           this.getSignature().dimensionality == 0 ||
           dimensionality == this.getSignature().dimensionality)
        { return true; }
        return false;
    }
    
    public abstract Signature getSignature();
    public abstract Property[] getProperties();
    public abstract void setProperties(Property[] properties);

    public abstract void reseed(SeedProperty seed_property);//todo bad?

    protected abstract float algorithm(int[] pos);
}