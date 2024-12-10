package vrd.alg.property;

public class UIntProperty extends Property
{
    public UIntProperty(int value, String name)
    {
        super(ValueType.UInt, name);

        if(value <= 0)
        { throw new IllegalArgumentException("UIntProperty initialized with nonpositive integer"); }

        this.value = value;
    }

    public int value;
}