package vrd.alg.property;

import vrd.alg.Property;

public class NaturalProperty extends Property
{
    public NaturalProperty(int value, int min_value, int max_value, String name)
    {
        super(ValueType.Natural, name);

        if(value < min_value || value > max_value)
        { throw new IllegalArgumentException("NaturalProperty initialized with value out of range"); }

        this.value = value;
        this.min_value = min_value;
        this.max_value = max_value;
    }

    public int value;
    public final int min_value;
    public final int max_value;
}