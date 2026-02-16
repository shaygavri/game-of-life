import java.util.Random;

/**
 * Represents the grid for Conway's Game of Life.
 * Manages the 2D array of cells and implements the game rules.
 */
public class Grid {
    private Cell[][] cells;
    private int height;
    private int width;

    /**
     * Constructor for a new grid with size parameters
     * @param width the width of the grid
     * @param height the height of the grid
     */
    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        initCells();
    }

    /**
     * Default constructor for grid with size 50x50
     */
    public Grid() {
        this(50, 50);
    }

    /**
     * Private method that initializes blank cells for grid
     */
    private void initCells() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    /**
     * Gets the cell at the specified position.
     *
     * @param row the row index
     * @param col the column index
     * @return the cell at the specified position
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Gets the height (number of rows) of the grid.
     *
     * @return the height of the grid
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width (number of columns) of the grid.
     *
     * @return the width of the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * Checks if a position is within the grid boundaries.
     *
     * @param row the row index to check
     * @param col the column index to check
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    /**
     * Sets the state of a cell at the specified position.
     * Does nothing if the position is outside the grid.
     *
     * @param row the row index
     * @param col the column index
     * @param alive true to make the cell alive, false to make it dead
     */
    public void setCellState(int row, int col, boolean alive) {
        if (isValidLocation(row, col)) {
            cells[row][col].setAlive(alive);
        }
    }

    /**
     * Toggles the state of a cell at the specified position.
     * If alive, becomes dead. If dead, becomes alive.
     * Does nothing if the position is outside the grid.
     *
     * @param row the row index
     * @param col the column index
     */
    public void toggleCell(int row, int col) {
        if (isValidLocation(row, col)) {
            cells[row][col].toggle();
        }
    }

    /**
     * Counts the number of alive neighbors for a cell.
     * Checks all 8 surrounding cells (up, down, left, right, and diagonals).
     * Cells outside the grid boundaries are treated as dead.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the number of alive neighbors (0-8)
     */
    public int countAliveNeighbors(int row, int col) {
        int count = 0;

        // Check all 8 neighbors
        if (isValidLocation(row, col)) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    // Skip the cell itself
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    int neighborRow = row + i;
                    int neighborCol = col + j;

                    if (isValidLocation(neighborRow, neighborCol) && cells[neighborRow][neighborCol].isAlive()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /**
     * Advances the grid to the next generation using Conway's rules:
     * 1. Any live cell with 2 or 3 live neighbors survives
     * 2. Any dead cell with exactly 3 live neighbors becomes alive
     * 3. All other cells die or stay dead
     */
    public void nextGeneration() {
        Cell[][] nextGrd = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                boolean isAlive = cells[row][col].isAlive();
                boolean nextState;
                int aliveNeighbors = countAliveNeighbors(row, col);

                if (isAlive) {
                    // Living cell: survives with 2 or 3 neighbors
                    nextState = aliveNeighbors == 2 || aliveNeighbors == 3;
                } else {
                    // Dead cell: becomes alive with exactly 3 neighbors
                    nextState = aliveNeighbors == 3;
                }

                nextGrd[row][col] = new Cell(nextState);
            }
        }

        this.cells = nextGrd;
    }

    /**
     * Randomizes the grid with a specified probability.
     * Each cell has the given probability of being alive.
     *
     * @param prob the probability (0.0 to 1.0) that each cell will be alive
     */
    public void randomize(double prob) {
        Random rand = new Random();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col].setAlive(rand.nextDouble() <= prob);
            }
        }
    }

    /**
     * Randomizes the grid with 30% probability of cells being alive.
     */
    public void randomize() {
        randomize(0.3);
    }

    /**
     * Clears the grid by setting all cells to dead.
     */
    public void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col].setAlive(false);
            }
        }
    }

    /**
     * Prints the grid to the console.
     * Living cells are shown as ■, dead cells as □.
     * Useful for debugging and testing.
     */
    public void printGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(cells[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}




