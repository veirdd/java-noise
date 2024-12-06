package vrd.gen;

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
};