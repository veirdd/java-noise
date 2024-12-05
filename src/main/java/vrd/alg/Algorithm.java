package vrd.alg;

import vrd.alg.property.Property;

public interface Algorithm
{
    public float get(int[] pos) throws IllegalArgumentException;

    public Signature getSignature();
    public Property[] getProperties();
}