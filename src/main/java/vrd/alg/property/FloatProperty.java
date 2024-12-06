package vrd.alg.property;

public class FloatProperty extends Property // todo: can this be done with Property<T>?
{
    public FloatProperty(float value, String name)
    {
        super(ValueType.Float, name);

        this.value = value;
    }

    public float value;
}