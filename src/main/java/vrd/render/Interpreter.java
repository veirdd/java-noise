package vrd.render;

import vrd.ui.std.Canvas;
import vrd.util.Content;

public abstract class Interpreter
{
    public void draw(Content content, Canvas canvas)
    {
        if(content.getDimensionality() != getDimensionality())
        {
            throw new IllegalArgumentException(
                "Dimensionality of content )" + content.getDimensionality() +
                ") does not match this interpreter's input dimensionality (" + getDimensionality() + 
                ")");
        }

        algorithm(content, canvas);
    }

    protected abstract void algorithm(Content content, Canvas canvas);

    public abstract int getDimensionality();

    public static Interpreter make(int dimensionality)
    {
        switch(dimensionality) // todo: various types of interpreters
        {
            case 1:
                return new Interpreter1d();
        }

        throw new IllegalArgumentException("Could not make an interpreter with dimensionality " + dimensionality);
    }
}