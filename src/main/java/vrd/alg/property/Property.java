package vrd.alg.property;

// Property of an Algorithm that may vary across instances
public abstract class Property
{
    public Property(String name)
    {
        this.name = name;
    }

    public String name;
}