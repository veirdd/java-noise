package vrd.util;

import java.util.Arrays;

public class Content {
    public Content(int[] dimensions)
    {
        this.dimensions = dimensions.clone();

        int element_count = 1;
        // dimensions[0] * dimensions[1] * ... * dimensions[n]
        for(int i = 0; i < dimensions.length; ++i)
        { element_count *= dimensions[i]; }

        this.data = new Float[element_count];
    }

    public void set(int[] index, float value)
    { this.data[computeIndex(index)] = value; }

    public float get(int[] index)
    { return this.data[computeIndex(index)]; }

    public Content.Iterator iterator()
    { return new Iterator(this.dimensions); }

    // Converts n-dimensional index to a unique 1-dimensional index
    private int computeIndex(int[] index)
    {
        assert index.length == this.dimensions.length;

        // 0 <= index[i] < dimensions[i]
        for(int i = 0; i < index.length; ++i)
        { assert index[i] >= 0 && index[i] < this.dimensions[i]; }

        int int_index = 0;

        // index = {4, 3, 7} ->
        // 1 * 4 + dimensions[0] * 3 + dimensions[0] * dimensions[1] * 7
        for(int i = 0; i < index.length; ++i)
        { int_index += index[i] * dimensionMultipler(i); }

        return int_index;
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

    public class Iterator
    {
        protected Iterator(int[] dimensions)
        {
            this.dimensions = dimensions;
            // Create index to have the same numer of dimensions as content
            this.index = new int[dimensions.length];
        }

        public int[] next()
        {
            increment(0);

            return this.index;
        }

        // Increments the index, starting from dimension dim
        // Index is reset on overflow
        // 0 0 0 -> 1 0 0 -> 2 0 0 -> ... -> max 0 0 -> 0 1 0 -> ...
        private void increment(int dimension)
        {
            if(this.index.length == dimension)
            { return; }

            ++this.index[dimension];
            
            if(this.index[dimension] == this.dimensions[dimension])
            {
                this.index[dimension] = 0;
                increment(dimension + 1);
            }
        }

        public boolean end()
        {
            int[] index_plus_1 = this.index.clone(); // THIS THING WTF GOD
            
            for(int i = 0; i < index_plus_1.length; ++i)
            { ++index_plus_1[i]; }

            return Arrays.equals(index_plus_1, this.dimensions); // ALSO THIS LOL == WONT DO SHIT
        }

        public int[] index;
        final private int[] dimensions;
    }
}