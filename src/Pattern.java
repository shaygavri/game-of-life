/**
 * Contains predefined patterns for Conway's Game of Life.
 */
public class Pattern {
    private boolean[][] pattern;
    private String name;


    public Pattern(String name, boolean[][] pattern) {
        this.name = name;
        this.pattern = pattern;
    }


    public String getName() {
        return name;
    }


    public boolean[][] getPattern() {
        return pattern;
    }


    public int getHeight() {
        return pattern.length;
    }


    public int getWidth() {
        return pattern[0].length;
    }


    public void placeOnGrid(Grid grid, int startRow, int startCol) {
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                int gridRow = startRow + row;
                int gridCol = startCol + col;

                if (gridRow >= 0 && gridRow < grid.getHeight() && gridCol >= 0 && gridCol < grid.getWidth()) {
                    grid.setCellState(gridRow, gridCol, pattern[row][col]);
                }
            }
        }
    }

    // ============================ Known Patterns ============================
    //
    // ============================ Still Lifes ============================

    /**
     * Creates a Block pattern.
     */
    public static Pattern block() {
        boolean[][] pattern = {
                {true, true},
                {true, true}
        };
        return new Pattern("Block", pattern);
    }

    /**
     * Creates a Bee-Hive pattern.
     */
    public static Pattern BeeHive() {
        boolean[][] pattern = {
                {false, true, true, false},
                {true, false, false, true},
                {false, true, true, false}
        };
        return new Pattern("Bee Hive", pattern);
    }

    /**
     * Creates a Loaf pattern.
     */
    public static Pattern loaf() {
        boolean[][] pattern = {
                {false, true, true, false},
                {true, false, false, true},
                {false, true, false, true},
                {false, false, true, false}
        };
        return new Pattern("Loaf", pattern);
    }

    /**
     * Creates a Boat pattern.
     */
    public static Pattern boat() {
        boolean[][] pattern = {
                {true, true, false},
                {true, false, true},
                {false, true, false}
        };
        return new Pattern("Boat", pattern);
    }

    /**
     * Creates a Tub pattern.
     */
    public static Pattern tub() {
        boolean[][] pattern = {
                {false, true, false},
                {true, false, true},
                {false, true, false}
        };
        return new Pattern("Tub", pattern);
    }

    // ============================ Oscillators ============================

    /**
     * Creates a Blinker pattern.
     * An oscillator with period 2.
     */
    public static Pattern blinker() {
        boolean[][] pattern = {
                {true, true, true}
        };
        return new Pattern("Blinker", pattern);
    }

    /**
     * Creates a Toad pattern.
     * An oscillator with period 2.
     */
    public static Pattern toad() {
        boolean[][] pattern = {
                {false, true, true, true},
                {true, true, true, false}
        };
        return new Pattern("Toad", pattern);
    }

    /**
     * Creates a Beacon pattern.
     * An oscillator with period 2.
     */
    public static Pattern beacon() {
        boolean[][] pattern = {
                {true, true, false, false},
                {true, true, false, false},
                {false, false, true, true},
                {false, false, true, true}
        };
        return new Pattern("Beacon", pattern);
    }

    /**
     * Creates a Pulsar pattern.
     * An oscillator with period 3.
     */
    public static Pattern pulsar() {
        boolean[][] pattern = {
                {false, false, true, true, true, false, false, false, true, true, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, false, false, false, false, true, false, true, false, false, false, false, true},
                {true, false, false, false, false, true, false, true, false, false, false, false, true},
                {true, false, false, false, false, true, false, true, false, false, false, false, true},
                {false, false, true, true, true, false, false, false, true, true, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, true, false, false, false, true, true, true, false, false},
                {true, false, false, false, false, true, false, true, false, false, false, false, true},
                {true, false, false, false, false, true, false, true, false, false, false, false, true},
                {true, false, false, false, false, true, false, true, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, true, false, false, false, true, true, true, false, false}
        };
        return new Pattern("Pulsar", pattern);
    }

    /**
     * Creates a Pentadecathlon pattern.
     * An oscillator with period 15.
     */
    public static Pattern pentadecathlon() {
        boolean[][] pattern = {
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        return new Pattern("Pentadecathlon", pattern);
    }

    // ============================ Spaceships ============================

    /**
     * Creates a Glider pattern.
     */
    public static Pattern glider() {
        boolean[][] pattern = {
                {false, true, false},
                {false, false, true},
                {true, true, true}
        };
        return new Pattern("Glider", pattern);
    }

    /**
     * Creates a Lightweight Spaceship (LWSS) pattern.
     * A small spaceship that moves horizontally.
     */
    public static Pattern lightweightSpaceship() {
        boolean[][] pattern = {
                {false, true, false, false, true},
                {true, false, false, false, false},
                {true, false, false, false, true},
                {true, true, true, true, false}
        };
        return new Pattern("Lightweight Spaceship", pattern);
    }

    /**
     * Creates a Middleweight Spaceship (MWSS) pattern.
     * A medium spaceship that moves horizontally.
     */
    public static Pattern middleweightSpaceship() {
        boolean[][] pattern = {
                {false, false, true, false, false, false},
                {false, true, false, false, true, false},
                {true, false, false, false, false, false},
                {true, false, false, false, false, true},
                {true, true, true, true, true, false}
        };
        return new Pattern("Middleweight Spaceship", pattern);
    }

    /**
     * Creates a Heavyweight Spaceship (HWSS) pattern.
     * A large spaceship that moves horizontally.
     */
    public static Pattern heavyweightSpaceship() {
        boolean[][] pattern = {
                {false, false, true, true, false, false, false},
                {false, true, false, false, false, true, false},
                {true, false, false, false, false, false, false},
                {true, false, false, false, false, false, true},
                {true, true, true, true, true, true, false}
        };
        return new Pattern("Heavyweight Spaceship", pattern);
    }

    // ============================ Glider Guns ============================

    /**
     * Creates a Glider Gun (Gosper Glider Gun) pattern.
     * A pattern that continuously creates gliders.
     */
    public static Pattern GosperGliderGun() {
        boolean[][] pattern = new boolean[9][36];

        pattern[4][0] = true;
        pattern[5][0] = true;
        pattern[4][1] = true;
        pattern[5][1] = true;

        pattern[4][10] = true;
        pattern[5][10] = true;
        pattern[6][10] = true;
        pattern[3][11] = true;
        pattern[7][11] = true;
        pattern[2][12] = true;
        pattern[8][12] = true;
        pattern[2][13] = true;
        pattern[8][13] = true;
        pattern[5][14] = true;
        pattern[3][15] = true;
        pattern[7][15] = true;
        pattern[4][16] = true;
        pattern[5][16] = true;
        pattern[6][16] = true;
        pattern[5][17] = true;

        pattern[2][20] = true;
        pattern[3][20] = true;
        pattern[4][20] = true;
        pattern[2][21] = true;
        pattern[3][21] = true;
        pattern[4][21] = true;
        pattern[1][22] = true;
        pattern[5][22] = true;
        pattern[0][24] = true;
        pattern[1][24] = true;
        pattern[5][24] = true;
        pattern[6][24] = true;

        pattern[2][34] = true;
        pattern[3][34] = true;
        pattern[2][35] = true;
        pattern[3][35] = true;

        return new Pattern("Gosper Glider Gun", pattern);
    }
}
