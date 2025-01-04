package vd.gen.alg.concrete.custom_world;

import vd.gen.alg.AlgorithmList;
import vd.gen.alg.DimensionalAlgorithm;
import vd.gen.alg.Signature;
import vd.gen.alg.property.IntProperty;
import vd.gen.alg.property.Property;

public class cwColor extends DimensionalAlgorithm
{
    private enum PropertyId
    {
        Red,
        Green,
        Blue
    }

    public cwColor()
    { this(100, 100, 100); }

    public cwColor(int red, int green, int blue)
    { 
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public Signature getSignature()
    { return new Signature(AlgorithmList.Id.cwColor, "cwColor [3D]", 3); }

    @Override
    public Property[] getProperties()
    {
        return new Property[]
        {
            new IntProperty("Red", this.red, 0, 255),
            new IntProperty("Green", this.green, 0, 255),
            new IntProperty("Blue", this.blue, 0, 255)
        };
    }

    @Override
    public void setProperties(Property[] properties)
    {
        this.red = ((IntProperty)properties[PropertyId.Red.ordinal()]).value;
        this.green = ((IntProperty)properties[PropertyId.Green.ordinal()]).value;
        this.blue = ((IntProperty)properties[PropertyId.Blue.ordinal()]).value;
    }

    @Override
    protected float getImpl(int[] pos)
    {
        // Second layer
        if(pos[2] == 1)
        {
            // e.g. white = 255255255
            return red + green * 1000 + blue * 1000 * 1000;
        }
        return 0;
    }

    private int red;
    private int green;
    private int blue;
}