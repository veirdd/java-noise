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

    public Macro macro;
    public int value;

    // Used for literal String input in UI
    // Should be same order as enum
    public static final List<String> macro_literals = List.of
    (
        "default", 
        "random"
    );
}