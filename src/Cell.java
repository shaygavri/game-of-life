/**
 * Represents a single cell  in Conway's Game of Life.
 * A cell can be either alive or dead.
 */
public class Cell {
    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public Cell() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void toggle() {
        this.alive = !this.alive;
    }

    @Override
    public String toString() {
        return alive ? "■" : "□";
    }
}
