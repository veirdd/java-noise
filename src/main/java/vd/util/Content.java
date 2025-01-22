package vd.util;

import java.util.Arrays;
import java.util.Iterator;

public class Content implements Iterable<Float> {
    public Content(int[] dimensions)
    {
        this.dimensions = dimensions.clone();

        int element_count = 1;
        // dimensions[0] * dimensions[1] * ... * dimensions[n]
        for(int i = 0; i < this.dimensions.length; ++i)
        { element_count *= this.dimensions[i]; }

        this.data = new Float[element_count];
            Arrays.fill(this.data, 0.f);
    }

    public void set(int[] indices, float value)
    { this.data[mapIndicesToIndex(indices)] = value; }

    public float get(int[] indices)
    { return this.data[mapIndicesToIndex(indices)]; }

    public int getDimensionality()
    { return this.dimensions.length; }
    
    public int getSize()
    { return this.data.length; }

    // Converts n-dimensional index (indices) to a unique 1-dimensional index
    // Public because why the fuck not
    public int mapIndicesToIndex(int[] indices)
    {
        if(indices.length != this.dimensions.length)
        { throw new IllegalArgumentException("Invalid dimensionality of indices"); }

        // 0 <= indices[i] < dimensions[i]
        for(int i = 0; i < indices.length; ++i)
        {
            if(indices[i] < 0 || indices[i] >= this.dimensions[i])
            { throw new IndexOutOfBoundsException(); }
        }

        int index = 0;

        // indices = {4, 3, 7} ->
        // 1 * 4 + dimensions[0] * 3 + dimensions[0] * dimensions[1] * 7
        for(int i = 0; i < indices.length; ++i)
        { index += indices[i] * dimensionMultipler(i); }

        return index;
    }

    public int[] mapIndexToIndices(int index)
    {
        if(index < 0 || index >= this.data.length)
        { throw new IndexOutOfBoundsException(); }

        int indices[] = new int[this.dimensions.length];
        int dimension_multipler;

        for(int i = indices.length - 1; i >= 0; --i)
        {
            dimension_multipler = dimensionMultipler(i);
            indices[i] = index / dimension_multipler;
            index %= dimension_multipler;
        }

        return indices;
    }

    // Returns the factor used for spanning n-dimensional data uniquely
    private int dimensionMultipler(int i)
    {
        int multipler = 1;

        // multipler *= dimensions[0] * dimensions[1] * ... * dimensions[i - 1]; 
        for(int j = i - 1; j >= 0; --j)
        { multipler *= this.dimensions[j]; }

        return multipler;
    }

    public static Content combine(Content a, Content b, FloatOperation cell_operation)
    {
        // if(a.dimensions != b.dimensions) doesn't work lmao java go kys
        if(!Arrays.equals(a.dimensions, b.dimensions))
        { throw new IllegalArgumentException("Content dimensions mismatch"); }

        Content out = new Content(a.dimensions);

        int[] indices;
        for(int i = 0; i < a.getSize(); ++i)
        {
            indices = a.mapIndexToIndices(i);
            out.set(indices, cell_operation.calculate(a.get(indices), b.get(indices)));
        }

        return out;
    }

    @Override
    public Iterator<Float> iterator()
    {
        return new ContentIterator(this.data);
    }

    final private Float[] data;
    final public int[] dimensions;
}

class ContentIterator implements Iterator<Float>
{
    ContentIterator(Float[] data)
    {
        this.data = data;
    }

    @Override
    public boolean hasNext()
    { return this.index < this.data.length && this.data[this.index] != null; }

    @Override
    public Float next()
    { return this.data[this.index++]; }

    @Override
    public void remove()
    { throw new UnsupportedOperationException(); }

    private int index = 0;
    final private Float[] data;
};