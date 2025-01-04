package vd.gen.alg.property;

public class FloatProperty extends Property // todo: min_value and max_value
{
    public FloatProperty(String name, float value)
    {
        super(ValueType.Float, name);

        this.value = value;
    }

    public float value;
}