package vrd.alg;

public class Random extends Algorithm //todo seeds, properties label hide when none
{
    public Random()
    { this.random = new java.util.Random(); }

    @Override
    public float algorithm(int[] pos)
    { return this.random.nextFloat(); }

    @Override
    public Signature getSignature()
    { return new Signature(SignatureList.Id.Random, "Random", 0); }

    @Override
    public Property[] getProperties()
    { return new Property[]{}; }

    @Override
    public void setProperties(Property[] properties) {}

    private java.util.Random random;
}