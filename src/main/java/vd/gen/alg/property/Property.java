package vd.gen.alg.property;

// Property of an Algorithm that may vary across instances
// Subclasses should define constraints and types for property values
public abstract class Property
{
    public enum ValueType
    {
        Int,
        Float,
        Seed
    }

    public Property(ValueType value_type, String name)
    {
        this.value_type = value_type;
        this.name = name;
    }

    public final ValueType value_type;
    public final String name;
}