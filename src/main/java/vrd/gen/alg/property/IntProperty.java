package vrd.gen.alg.property;

public class IntProperty extends Property
{
    public IntProperty(String name, int value, int min_value, int max_value)
    {
        super(ValueType.Int, name);

        this.value = value;
        this.min_value = min_value;
        this.max_value = max_value;
    }

    public int value;
    public final int min_value;
    public final int max_value;
}