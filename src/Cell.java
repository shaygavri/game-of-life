/**
 * Represents a single cell  in Conway's Game of Life.
 * A cell can be either alive or dead.
 */
public class Cell {
    private boolean alive;

    /**
     * Creates a new cell with the specified state.
     *
     * @param alive true if the cell should be alive, false if dead
     */
    public Cell(boolean alive) {
        this.alive = alive;
    }

    /**
     * Creates a new cell that is dead (not alive).
     */
    public Cell() {
        this.alive = false;
    }

    /**
     * Checks if this cell is alive.
     *
     * @return true if the cell is alive, false if dead
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the state of this cell.
     *
     * @param alive true to make the cell alive, false to make it dead
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Toggles the state of this cell.
     * If alive, becomes dead. If dead, becomes alive.
     */
    public void toggle() {
        this.alive = !this.alive;
    }

    /**
     * Returns a string representation of this cell.
     *
     * @return "■" if alive, "□" if dead
     */
    @Override
    public String toString() {
        return alive ? "■" : "□";
    }
}