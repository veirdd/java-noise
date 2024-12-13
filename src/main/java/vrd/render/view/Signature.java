package vrd.render.view;

// Core propertries of a View that do not vary across instances
public class Signature
{
    public Signature(ViewList.Id id, String name, int input_dimensionality)
    {
        this.id = id;
        this.name = name;
        this.input_dimensionality = input_dimensionality;
    }

    // Unique ID
    public ViewList.Id id;
    // Used for displaying in the UI
    public String name;
    // 0 indicates that the View is universal
    public int input_dimensionality;
}