package vrd.ui;

@FunctionalInterface
public interface MoveTileOperation
{
    public enum Direction
    {
        Up,
        Down
    }

    public void request(Direction direction);
}