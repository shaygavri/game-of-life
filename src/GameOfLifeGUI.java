import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI application for Conway's Game of Life.
 * Features:
 * - Visual grid display with clickable cells
 * - Start/Stop/Step/Clear controls
 * - Random generation
 * - Predefined pattern insertion
 * - Configurable grid size
 */
public class GameOfLifeGUI extends JFrame {

    private Grid grid;
    private GridPanel gridPanel;
    private Timer timer;
    private boolean running;

    // UI Components
    private JButton startButton;
    private JButton stepButton;
    private JButton clearButton;
    private JButton randomButton;
    private JComboBox<String> patternSelector;
    private JLabel generationLabel;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;

    private int generation;
    private static final int DEFAULT_CELL_SIZE = 10;
    private static final int DEFAULT_DELAY = 200; // milliseconds between generations

    /**
     * Creates the Game of Life GUI with default 50x50 grid.
     */
    public GameOfLifeGUI() {
        this(50, 50);
    }

    /**
     * Creates the Game of Life GUI with specified dimensions.
     * @param width the grid width
     * @param height the grid height
     */
    public GameOfLifeGUI(int width, int height) {
        grid = new Grid(width, height);
        generation = 0;
        running = false;

        initializeUI();
        setupTimer();
    }

    /**
     * Initializes all UI components.
     */
    private void initializeUI() {
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create grid panel
        gridPanel = new GridPanel();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        add(scrollPane, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Create status panel
        JPanel statusPanel = createStatusPanel();
        add(statusPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Creates the control panel with buttons and options.
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Start/Stop button
        startButton = new JButton("Start");
        startButton.addActionListener(e -> toggleSimulation());
        panel.add(startButton);

        // Step button
        stepButton = new JButton("Step");
        stepButton.addActionListener(e -> step());
        panel.add(stepButton);

        // Clear button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clear());
        panel.add(clearButton);

        // Random button
        randomButton = new JButton("Random");
        randomButton.addActionListener(e -> randomize());
        panel.add(randomButton);

        panel.add(new JSeparator(SwingConstants.VERTICAL));

        // Pattern selector
        panel.add(new JLabel("Pattern:"));
        String[] patterns = {
                "Select Pattern",
                "-- Still Lifes --",
                "Block",
                "Beehive",
                "Loaf",
                "Boat",
                "Tub",
                "-- Oscillators --",
                "Blinker",
                "Toad",
                "Beacon",
                "Pulsar",
                "Pentadecathlon",
                "-- Spaceships --",
                "Glider",
                "LWSS",
                "MWSS",
                "HWSS",
                "-- Generators --",
                "Gosper Glider Gun"
        };
        patternSelector = new JComboBox<>(patterns);
        patternSelector.addActionListener(e -> insertPattern());
        panel.add(patternSelector);

        panel.add(new JSeparator(SwingConstants.VERTICAL));

        // Grid size configuration
        panel.add(new JLabel("Width:"));
        widthSpinner = new JSpinner(new SpinnerNumberModel(50, 10, 200, 10));
        widthSpinner.setPreferredSize(new Dimension(60, 25));
        panel.add(widthSpinner);

        panel.add(new JLabel("Height:"));
        heightSpinner = new JSpinner(new SpinnerNumberModel(50, 10, 200, 10));
        heightSpinner.setPreferredSize(new Dimension(60, 25));
        panel.add(heightSpinner);

        JButton resizeButton = new JButton("Resize Grid");
        resizeButton.addActionListener(e -> resizeGrid());
        panel.add(resizeButton);

        return panel;
    }

    /**
     * Creates the status panel showing generation count.
     */
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        generationLabel = new JLabel("Generation: 0");
        panel.add(generationLabel);

        return panel;
    }

    /**
     * Sets up the timer for automatic generation advancement.
     */
    private void setupTimer() {
        timer = new Timer(DEFAULT_DELAY, e -> {
            step();
        });
    }

    /**
     * Toggles the simulation between running and stopped.
     */
    private void toggleSimulation() {
        if (running) {
            stop();
        } else {
            start();
        }
    }

    /**
     * Starts the simulation.
     */
    private void start() {
        running = true;
        startButton.setText("Stop");
        stepButton.setEnabled(false);
        timer.start();
    }

    /**
     * Stops the simulation.
     */
    private void stop() {
        running = false;
        startButton.setText("Start");
        stepButton.setEnabled(true);
        timer.stop();
    }

    /**
     * Advances one generation.
     */
    private void step() {
        grid.nextGeneration();
        generation++;
        generationLabel.setText("Generation: " + generation);
        gridPanel.repaint();
    }

    /**
     * Clears the grid and resets generation counter.
     */
    private void clear() {
        stop();
        grid.clear();
        generation = 0;
        generationLabel.setText("Generation: " + generation);
        gridPanel.repaint();
    }

    /**
     * Randomizes the grid.
     */
    private void randomize() {
        stop();
        grid.randomize();
        generation = 0;
        generationLabel.setText("Generation: " + generation);
        gridPanel.repaint();
    }

    /**
     * Inserts the selected pattern at the center of the grid.
     */
    private void insertPattern() {
        String selected = (String) patternSelector.getSelectedItem();
        if (selected == null || selected.equals("Select Pattern") || selected.startsWith("--")) {
            // Reset selector if it's a header or default option
            patternSelector.setSelectedIndex(0);
            return;
        }

        Pattern pattern = null;
        switch (selected) {
            // Still Lifes
            case "Block":
                pattern = Pattern.block();
                break;
            case "Beehive":
                pattern = Pattern.BeeHive();
                break;
            case "Loaf":
                pattern = Pattern.loaf();
                break;
            case "Boat":
                pattern = Pattern.boat();
                break;
            case "Tub":
                pattern = Pattern.tub();
                break;

            // Oscillators
            case "Blinker":
                pattern = Pattern.blinker();
                break;
            case "Toad":
                pattern = Pattern.toad();
                break;
            case "Beacon":
                pattern = Pattern.beacon();
                break;
            case "Pulsar":
                pattern = Pattern.pulsar();
                break;
            case "Pentadecathlon":
                pattern = Pattern.pentadecathlon();
                break;

            // Spaceships
            case "Glider":
                pattern = Pattern.glider();
                break;
            case "LWSS":
                pattern = Pattern.lightweightSpaceship();
                break;
            case "MWSS":
                pattern = Pattern.middleweightSpaceship();
                break;
            case "HWSS":
                pattern = Pattern.heavyweightSpaceship();
                break;

            // Generators
            case "Gosper Glider Gun":
                pattern = Pattern.GosperGliderGun();
                break;
        }

        if (pattern != null) {
            // Place pattern at center of grid
            int startRow = (grid.getHeight() - pattern.getHeight()) / 2;
            int startCol = (grid.getWidth() - pattern.getWidth()) / 2;
            pattern.placeOnGrid(grid, startRow, startCol);
            gridPanel.repaint();
        }

        // Reset selector
        patternSelector.setSelectedIndex(0);
    }

    /**
     * Resizes the grid to the specified dimensions.
     */
    private void resizeGrid() {
        stop();
        int newWidth = (Integer) widthSpinner.getValue();
        int newHeight = (Integer) heightSpinner.getValue();

        grid = new Grid(newWidth, newHeight);
        generation = 0;
        generationLabel.setText("Generation: " + generation);
        gridPanel.updateGrid();
        gridPanel.repaint();
        pack();
    }

    /**
     * Panel that displays the grid of cells.
     */
    private class GridPanel extends JPanel {
        private int cellSize = DEFAULT_CELL_SIZE;

        public GridPanel() {
            updateGrid();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    handleCellClick(e);
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    handleCellClick(e);
                }
            });
        }

        /**
         * Updates the preferred size based on grid dimensions.
         */
        public void updateGrid() {
            int width = grid.getWidth() * cellSize + 1;
            int height = grid.getHeight() * cellSize + 1;
            setPreferredSize(new Dimension(width, height));
            revalidate();
        }

        /**
         * Handles mouse clicks to toggle cells.
         */
        private void handleCellClick(MouseEvent e) {
            if (running) return; // Don't allow editing while running

            int col = e.getX() / cellSize;
            int row = e.getY() / cellSize;

            if (row >= 0 && row < grid.getHeight() && col >= 0 && col < grid.getWidth()) {
                grid.toggleCell(row, col);
                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

            // Draw cells
            for (int row = 0; row < grid.getHeight(); row++) {
                for (int col = 0; col < grid.getWidth(); col++) {
                    Cell cell = grid.getCell(row, col);

                    if (cell.isAlive()) {
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                    }
                }
            }

            // Draw grid lines
            g2d.setColor(Color.LIGHT_GRAY);
            for (int row = 0; row <= grid.getHeight(); row++) {
                g2d.drawLine(0, row * cellSize, grid.getWidth() * cellSize, row * cellSize);
            }
            for (int col = 0; col <= grid.getWidth(); col++) {
                g2d.drawLine(col * cellSize, 0, col * cellSize, grid.getHeight() * cellSize);
            }
        }
    }

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameOfLifeGUI gui = new GameOfLifeGUI();
            gui.setVisible(true);
        });
    }
}