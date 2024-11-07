package vrd.util;

import java.util.Iterator;

public class Content implements Iterable<Float> {
    public Content(int[] dimensions)
    {
        this.dimensions = dimensions.clone(); // todo: check what happens for 0 dimensions lol

        int element_count = 1;
        // dimensions[0] * dimensions[1] * ... * dimensions[n]
        for(int i = 0; i < dimensions.length; ++i)
        { element_count *= dimensions[i]; }

        this.data = new Float[element_count];
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
        { throw new IllegalArgumentException(); }

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

    public int[] mapIndexToIndices(int index) // todo: does this work?
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

    // currently obsolete lol
    // q: can i use this somehow
    @Override
    public Iterator<Float> iterator()
    {
        return new ContentIterator(this.data);
    }

    // Returns the factor used for spanning n-dimensional data uniquely
    private int dimensionMultipler(int i)
    {
        int multipler = 1;

        // multipler *= dimensions[0] * dimensions[1] * ... * dimensions[i - 1]; 
        for(int j = i - 1; j > 0; --j)
        { multipler *= this.dimensions[j]; }

        return multipler;
    }

    final private Float[] data;
    final private int[] dimensions;
}

class ContentIterator implements Iterator<Float> // q: should this be in Content body or here
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