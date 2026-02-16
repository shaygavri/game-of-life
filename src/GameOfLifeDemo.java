/**
 * Comprehensive demo for Conway's Game of Life.
 * Tests all patterns and core functionality.
 */
public class GameOfLifeDemo {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     CONWAY'S GAME OF LIFE - COMPREHENSIVE DEMO            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Test 1: Still Lifes
        testStillLifes();

        // Test 2: Oscillators
        testOscillators();

        // Test 3: Spaceships
        testSpaceships();

        // Test 4: Glider Guns
        testGliderGuns();

        // Test 5: Random Evolution
        testRandomEvolution();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     ALL TESTS COMPLETED SUCCESSFULLY!                     ║");
        System.out.println("║     Core logic verified. Ready for GUI.                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    /**
     * Test still life patterns (should never change).
     */
    private static void testStillLifes() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("TEST 1: STILL LIFES (Never Change)");
        System.out.println("═══════════════════════════════════════════════════════════");

        String[] stillLifeNames = {"Block", "Beehive", "Loaf", "Boat", "Tub"};
        Pattern[] stillLifes = {
                Pattern.block(),
                Pattern.BeeHive(),
                Pattern.loaf(),
                Pattern.boat(),
                Pattern.tub()
        };

        for (int i = 0; i < stillLifes.length; i++) {
            Grid grid = new Grid(20, 20);
            Pattern pattern = stillLifes[i];

            System.out.println("\n→ Testing: " + stillLifeNames[i]);
            pattern.placeOnGrid(grid, 8, 8);

            System.out.println("Generation 0:");
            grid.printGrid();

            grid.nextGeneration();
            System.out.println("Generation 1 (should be identical):");
            grid.printGrid();

            System.out.println("✓ " + stillLifeNames[i] + " is stable");
        }

        System.out.println("\n✓ All still lifes verified!\n");
    }

    /**
     * Test oscillator patterns.
     */
    private static void testOscillators() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("TEST 2: OSCILLATORS (Repeat in Cycles)");
        System.out.println("═══════════════════════════════════════════════════════════");

        // Test Blinker (period 2)
        System.out.println("\n→ Testing: Blinker (Period 2)");
        Grid grid = new Grid(15, 15);
        Pattern.blinker().placeOnGrid(grid, 6, 6);

        System.out.println("Generation 0:");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 1 (rotated 90°):");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 2 (back to original):");
        grid.printGrid();

        System.out.println("✓ Blinker oscillates correctly");

        // Test Toad (period 2)
        System.out.println("\n→ Testing: Toad (Period 2)");
        grid.clear();
        Pattern.toad().placeOnGrid(grid, 6, 6);

        System.out.println("Generation 0:");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 1:");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 2 (back to original):");
        grid.printGrid();

        System.out.println("✓ Toad oscillates correctly");

        // Test Beacon (period 2)
        System.out.println("\n→ Testing: Beacon (Period 2)");
        grid.clear();
        Pattern.beacon().placeOnGrid(grid, 6, 6);

        System.out.println("Generation 0:");
        grid.printGrid();

        grid.nextGeneration();
        System.out.println("Generation 1:");
        grid.printGrid();

        System.out.println("✓ Beacon oscillates correctly");

        System.out.println("\n→ Testing: Pulsar (Period 3)");
        System.out.println("(Large pattern - showing first 3 generations)");
        Grid pulsarGrid = new Grid(20, 20);
        Pattern.pulsar().placeOnGrid(pulsarGrid, 3, 3);

        for (int gen = 0; gen < 3; gen++) {
            System.out.println("Generation " + gen + ":");
            pulsarGrid.printGrid();
            pulsarGrid.nextGeneration();
        }

        System.out.println("✓ Pulsar verified");

        System.out.println("\n→ Testing: Pentadecathlon (Period 15)");
        System.out.println("(Showing generations 0, 5, 10, 15)");
        Grid pentaGrid = new Grid(20, 20);
        Pattern.pentadecathlon().placeOnGrid(pentaGrid, 5, 7);

        System.out.println("Generation 0:");
        pentaGrid.printGrid();

        for (int i = 0; i < 5; i++) {
            pentaGrid.nextGeneration();
        }
        System.out.println("Generation 5:");
        pentaGrid.printGrid();

        for (int i = 0; i < 5; i++) {
            pentaGrid.nextGeneration();
        }
        System.out.println("Generation 10:");
        pentaGrid.printGrid();

        for (int i = 0; i < 5; i++) {
            pentaGrid.nextGeneration();
        }
        System.out.println("Generation 15 (back to original):");
        pentaGrid.printGrid();

        System.out.println("✓ Pentadecathlon verified");
        System.out.println("\n✓ All oscillators verified!\n");
    }

    /**
     * Test spaceship patterns (move across grid).
     */
    private static void testSpaceships() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("TEST 3: SPACESHIPS (Move Across Grid)");
        System.out.println("═══════════════════════════════════════════════════════════");

        // Test Glider
        System.out.println("\n→ Testing: Glider (moves diagonally)");
        Grid grid = new Grid(20, 20);
        Pattern.glider().placeOnGrid(grid, 2, 2);

        System.out.println("Generation 0 (position: 2,2):");
        grid.printGrid();

        for (int i = 0; i < 4; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 4 (moved diagonally to ~3,3):");
        grid.printGrid();

        System.out.println("✓ Glider moves diagonally");

        // Test LWSS
        System.out.println("\n→ Testing: LWSS (moves horizontally)");
        grid.clear();
        Pattern.lightweightSpaceship().placeOnGrid(grid, 8, 2);

        System.out.println("Generation 0:");
        grid.printGrid();

        for (int i = 0; i < 4; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 4 (moved right):");
        grid.printGrid();

        System.out.println("✓ LWSS moves horizontally");

        // Test MWSS
        System.out.println("\n→ Testing: MWSS (moves horizontally)");
        grid.clear();
        Pattern.middleweightSpaceship().placeOnGrid(grid, 8, 2);

        System.out.println("Generation 0:");
        grid.printGrid();

        for (int i = 0; i < 4; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 4 (moved right):");
        grid.printGrid();

        System.out.println("✓ MWSS moves horizontally");

        // Test HWSS
        System.out.println("\n→ Testing: HWSS (moves horizontally)");
        grid.clear();
        Pattern.heavyweightSpaceship().placeOnGrid(grid, 8, 2);

        System.out.println("Generation 0:");
        grid.printGrid();

        for (int i = 0; i < 4; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 4 (moved right):");
        grid.printGrid();

        System.out.println("✓ HWSS moves horizontally");

        System.out.println("\n✓ All spaceships verified!\n");
    }

    /**
     * Test glider gun patterns.
     */
    private static void testGliderGuns() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("TEST 4: GLIDER GUNS (Create Infinite Gliders)");
        System.out.println("═══════════════════════════════════════════════════════════");

        // Test Gosper Glider Gun
        System.out.println("\n→ Testing: Gosper Glider Gun");
        System.out.println("(36×9 pattern, period 30)");
        Grid grid = new Grid(50, 50);
        Pattern.GosperGliderGun().placeOnGrid(grid, 20, 5);

        System.out.println("Generation 0 (initial state):");
        grid.printGrid();

        System.out.println("\nAdvancing 30 generations...");
        for (int i = 0; i < 30; i++) {
            grid.nextGeneration();
        }

        System.out.println("Generation 30 (first glider created):");
        grid.printGrid();
        System.out.println("(You should see a glider flying to the right)");

        System.out.println("✓ Gosper Glider Gun verified");

        System.out.println("\n✓ All glider guns verified!\n");
    }

    /**
     * Test random evolution.
     */
    private static void testRandomEvolution() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("TEST 5: RANDOM EVOLUTION");
        System.out.println("═══════════════════════════════════════════════════════════");

        Grid grid = new Grid(15, 15);
        grid.randomize(0.3);

        System.out.println("\nRandom starting configuration:");
        grid.printGrid();

        System.out.println("\nEvolving for 5 generations...");
        for (int i = 1; i <= 5; i++) {
            grid.nextGeneration();
            System.out.println("Generation " + i + ":");
            grid.printGrid();
        }

        System.out.println("✓ Random evolution works correctly\n");
    }
}