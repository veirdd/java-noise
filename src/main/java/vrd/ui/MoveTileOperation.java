package vrd.ui;

public interface MoveTileOperation
{
    enum Direction
    {
        Up,
        Down
    }

    public void request(Direction direction);
}