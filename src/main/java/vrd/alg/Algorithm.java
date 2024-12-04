package vrd.alg;

import vrd.alg.property.Property;

public interface Algorithm
{
    public float get(int[] pos);

    public Signature getSignature();
    public Property[] getProperties();
}