package vrd.alg;

public interface Algorithm
{
    public float get(int[] pos);

    public Property[] getProperties();

    // Similar to number of arguments in a function
    public int getDimensionality();
}