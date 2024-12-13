package vrd.gen.alg.property;

import java.util.List;

public class SeedProperty extends Property
{
    public enum Macro
    {
        Default,
        Random
    }

    public SeedProperty(String name, Macro macro)
    {
        super(ValueType.Seed, name);

        this.macro = macro;
    }

    public SeedProperty(String name, int value)
    {
        super(ValueType.Seed, name);

        this.macro = null;
        this.value = value;
    }

    public int getSeedInt()
    {
        if(this.macro == SeedProperty.Macro.Random)
        { return (int)System.nanoTime(); }
        else if(this.macro == SeedProperty.Macro.Default)
        { return 0; }
        // null
        return this.value;
    }

    public Macro macro;
    public int value;

    public static final List<String> macros = List.of("default", "random");
}//next add seed property to seed related algorithms and add global seed macro