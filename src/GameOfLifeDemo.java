/**
 * Simple demo to test Conway's Game of Life patterns.
 * Run this to verify everything works before using the GUI.
 */
public class GameOfLifeDemo {

    public static void main(String[] args) {
        System.out.println("Conway's Game of Life - Demo\n");

        // Test a still life
        testBlock();

        // Test an oscillator
        testBlinker();

        // Test a spaceship
        testGlider();

        // Test the glider gun
        testGliderGun();

        // Test random grid
        testRandom();

        System.out.println("\nAll tests complete!");
    }

    /**
     * Test the Block pattern (should stay the same).
     */
    private static void testBlock() {
        System.out.println("Testing Block (still life):");
        Grid grid = new Grid(10, 10);
        Pattern.block().placeOnGrid(grid, 4, 4);

        System.out.println("Generation 0:");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 1 (should look the same):");
        grid.printGrid();
    }

    /**
     * Test the Blinker pattern (should flip back and forth).
     */
    private static void testBlinker() {
        System.out.println("Testing Blinker (oscillator):");
        Grid grid = new Grid(10, 10);
        Pattern.blinker().placeOnGrid(grid, 4, 4);

        System.out.println("Generation 0:");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 1 (rotated):");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 2 (back to original):");
        grid.printGrid();
    }

    /**
     * Test the Glider pattern (should move diagonally).
     */
    private static void testGlider() {
        System.out.println("Testing Glider (spaceship):");
        Grid grid = new Grid(15, 15);
        Pattern.glider().placeOnGrid(grid, 2, 2);

        System.out.println("Generation 0:");
        grid.printGrid();

        for (int i = 0; i < 4; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 4 (moved down and right):");
        grid.printGrid();
    }

    /**
     * Test the Gosper Glider Gun (creates gliders).
     */
    private static void testGliderGun() {
        System.out.println("Testing Gosper Glider Gun:");
        Grid grid = new Grid(50, 50);
        Pattern.GosperGliderGun().placeOnGrid(grid, 20, 5);

        System.out.println("Generation 0:");
        grid.printGrid();

        System.out.println("Running 30 generations...");
        for (int i = 0; i < 30; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 30 (a glider should appear):");
        grid.printGrid();
    }

    /**
     * Test random grid evolution.
     */
    private static void testRandom() {
        System.out.println("Testing Random Grid:");
        Grid grid = new Grid(15, 15);
        grid.randomize();

        System.out.println("Generation 0 (random):");
        grid.printGrid();

        for (int i = 1; i <= 3; i++) {
            grid.nextGeneration();
            System.out.println("Generation " + i + ":");
            grid.printGrid();
        }
    }
}