package vrd.gen;

import vrd.util.FloatOperation;

public enum BlendMode
{
    Add,
    Multiply,
    Divide;

    public static String[] getBlendModeNames()
    {
        String[] names = new String[BlendMode.values().length];

        for(int i = 0; i < BlendMode.values().length; ++i)
        { names[i] = BlendMode.values()[i].name(); }

        return names;
    }

    public static FloatOperation getOperation(BlendMode mode)
    {
        switch(mode)
        {
            case Multiply:
                return (float a, float b)->{ return a * b; };
            case Divide:
                return (float a, float b)->{ return a / b; };
            default: // Add
                return (float a, float b)->{ return a + b; };
        }
    }
}