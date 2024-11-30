package vrd.alg;

public class Property
{
    public enum Constraint
    {
        None,
        Integer
    }

    public Property(float value, String name, Constraint constraint)
    {
        this.name = name;
        this.constraint = constraint;
    }

    public float value;
    public String name;
    // Used by text input fields to validate input
    public Constraint constraint;
}