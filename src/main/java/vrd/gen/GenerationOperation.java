package vrd.gen;

@FunctionalInterface
public interface GenerationOperation
{
    // Runs a generation process using the specified settings
    public void run(Settings settings);
}