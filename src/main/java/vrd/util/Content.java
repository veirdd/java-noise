package vrd.util;

import javax.swing.JComponent;

// Acts as a n-dimensional array of floats
public class Content extends JComponent {
    public Content(int[] size)
    {
        this.size = size;
        // todo: float[] with size[0] * size[1] * ... * size[n] slots
        // access methods for getting the correct index via int[] index
    }

    public void set(int[] index, float value)
    {

    }

    public float get()
    {
        return 0;
    }

    public Content.Iterator iterator()
    { return new Iterator(size); }

    private Float[] data;
    private int[] size;

    public class Iterator
    {
        protected Iterator(int[] size)
        {
            this.size = size;
            // Create index to have the same numer of dimensions as size of content
            this.index = new int[size.length];
        }

        public int[] next()
        {
            increment(0);

            return index;
        }

        // Increments the index, starting from dimension dim
        // Index is reset on overflow
        // 0 0 0 -> 1 0 0 -> 2 0 0 -> ... -> max 0 0 -> 0 1 0 -> ...
        private void increment(int dim)
        {
            if(index.length == dim)
            { return; }

            ++index[dim];
            
            if(index[dim] == size[dim])
            {
                index[dim] = 0;
                increment(dim + 1);
            }
        }

        public int[] end()
        { return size; }

        public int[] index;
        private int[] size;
    }
}