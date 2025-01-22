package vd.gen;

import vd.util.FloatOperation;

public enum BlendMode
{
    Add,
    Multiply,
    Divide,
    Fill;

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
            default: // Add
                return 
                    (float first, float second) -> 
                    { return first + second; };
            case Multiply:
                return 
                    (float first, float second) -> 
                    { return first * second; };
            case Divide:
                return 
                    (float first, float second) -> 
                    { return first / second; };
            case Fill:
                return
                    (float first, float second) ->
                    { return first > second ? first : second; };
        }
    }
}