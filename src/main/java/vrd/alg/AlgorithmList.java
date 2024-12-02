package vrd.alg;

public class AlgorithmList
{
    public enum Id
    {
        ConstantValue,
        PerlinNoise1d,
        PerlinNoise2d
    }

    public static String[] getAlgorithmNames()
    {
        return new String[]
        {
            "ConstantValue",
            "PerlinNoise1d",
            "PerlinNoise2d"
        };
    }
} // todo: maybe redo this with toString because it's not actually dumb