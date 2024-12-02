package vrd.alg.property;

public class FloatProperty extends Property
{
    public FloatProperty(float value, String name)
    {
        super(name);

        this.value = value;
    }

    public float value;
}