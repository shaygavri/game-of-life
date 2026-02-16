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
     *
     * @param row
     * @param col
     * @return
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }


    public void setCellState(int row, int col, boolean alive) {
        if (isValidLocation(row, col)) {
            cells[row][col].setAlive(alive);
        }
    }

    public void toggleCell(int row, int col) {
        if (isValidLocation(row, col)) {
            cells[row][col].toggle();
        }
    }

    /**
     * Counts the living neighbors of a given cell
     * @param row row index
     * @param col col index
     * @return living neighbors count
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
     * promotes the grid to the next step according to the neighbors of each cell
     */
    public void nextGeneration() {
        Cell[][] nextGrd = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                boolean isAlive = cells[row][col].isAlive();
                boolean nextState;
                int aliveNeighbors = countAliveNeighbors(row, col);

                if (isAlive) {
                    nextState = aliveNeighbors == 2 || aliveNeighbors == 3;
                } else {
                    nextState = aliveNeighbors == 3;
                }

                nextGrd[row][col] = new Cell(nextState);
            }
        }

        this.cells = nextGrd;
    }


    public void randomize(double prob) {
        Random rand = new Random();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col].setAlive(rand.nextDouble() <= prob);
            }
        }
    }


    public void randomize() {
        randomize(0.3);
    }


    public void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col].setAlive(false);
            }
        }
    }


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
