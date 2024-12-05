package vrd.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vrd.alg.noise.PerlinNoise1d;
import vrd.alg.noise.PerlinNoise2d;

public class SignatureList
{
    public enum Id
    {
        ConstantValue,
        PerlinNoise1d,
        PerlinNoise2d
    }

    public static Signature getSignature(Algorithm algorithm) // todo: is this necessary
    { return algorithm.getSignature(); }

    public static String[] getAlgorithmNames()
    { return getAlgorithmNames(0); }

    public static String[] getAlgorithmNames(int dimensionality)
    {
        ArrayList<String> names_list = new ArrayList<>();

        for(Algorithm algorithm : algorithm_list)
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
        for(Algorithm algorithm : algorithm_list)
        {
            if(algorithm.getSignature().id == id)
            { return algorithm; } // todo: is this cloned ffs java
        }
        
        throw new IllegalArgumentException("No algorithm with ID '" + id.toString() + "' was found");
    }
    
    public static boolean validateDimensionality(int dimensionality, Algorithm algorithm)// todo make this function and put it in a different file
    {

    }

    // List of Algorithms that should be included in runtime
    private static final List<Algorithm> algorithm_list = Collections.unmodifiableList(List.of
    (
        new ConstantValue(),
        new PerlinNoise1d(),
        new PerlinNoise2d()// showcase
    ));
}