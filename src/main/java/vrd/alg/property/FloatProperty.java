package vrd.alg.property;

import vrd.alg.Property;

public class FloatProperty extends Property // todo: min_value and max_value
{
    public FloatProperty(float value, String name)
    {
        super(ValueType.Float, name);

        this.value = value;
    }

    public float value;
}