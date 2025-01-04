package vd.gen.alg;

// Core propertries of an Algorithm that do not vary across instances
public class Signature
{
    public Signature(AlgorithmList.Id id, String name, int dimensionality)
    {
        this.id = id;
        this.name = name;
        this.dimensionality = dimensionality;
    }

    // Unique ID
    public AlgorithmList.Id id;
    // Used for displaying in the UI
    public String name;
    // 0 indicates that the Algorithm is universal
    public int dimensionality;
}