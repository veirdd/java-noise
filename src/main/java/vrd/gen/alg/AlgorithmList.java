package vrd.gen.alg;

import java.util.ArrayList;
import java.util.List;

import vrd.gen.alg.concrete.ConstantValue;
import vrd.gen.alg.concrete.PerlinNoise1d;
import vrd.gen.alg.concrete.PerlinNoise2d;
import vrd.gen.alg.concrete.Random;
import vrd.gen.alg.concrete.Sine;

public class AlgorithmList
{
    public enum Id
    {
        ConstantValue,
        Random,
        Sine,
        PerlinNoise1d,
        PerlinNoise2d
    }

    public static String[] getAlgorithmNames()
    { return getAlgorithmNames(0); }

    public static String[] getAlgorithmNames(int dimensionality)
    {
        ArrayList<String> names_list = new ArrayList<>();

        for(Algorithm algorithm : algorithmList())
        {
            // Returns all algorithms if dimensionality = 0
            if(dimensionality == 0 || 
               algorithm.getSignature().dimensionality == dimensionality || 
               algorithm.getSignature().dimensionality == 0)
            { names_list.add(algorithm.getSignature().name); }
        }

        return names_list.toArray(new String[0]);
    }

    public static Algorithm makeAlgorithmFromId(Id id)
    {
        for(Algorithm algorithm : algorithmList())
        {
            if(algorithm.getSignature().id == id)
            { return algorithm; }
        }
        
        throw new IllegalArgumentException("No algorithm with ID '" + id.toString() + "' was found");
    }

    // List of Algorithms that should be included in runtime
    private static List<Algorithm> algorithmList()
    {
        return List.of
        (
            // Should be same order as Id
            new ConstantValue(),
            new Random(),
            new Sine(),
            new PerlinNoise1d(),
            new PerlinNoise2d()// showcase
        );
    }
}