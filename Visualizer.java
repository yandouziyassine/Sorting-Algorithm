import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that visualizes an array of integers as histogram bars.
 * Provides animation capabilities for sorting algorithm visualization.
 */
public class Visualizer extends JPanel {
    private int[] array;
    private int highlightIndex1 = -1;
    private int highlightIndex2 = -1;
    private int sortedUpTo = -1;
    private int delay = 50; // Default delay in milliseconds
    
    // Statistics tracking
    private String algorithmName = "";
    private String timeComplexity = "";
    private String spaceComplexity = "";
    private int operationCount = 0;
    private int comparisonCount = 0;
    private int swapCount = 0;
    
    // Colors for visualization
    private static final Color BAR_COLOR = new Color(65, 105, 225);      // Royal Blue
    private static final Color HIGHLIGHT_COLOR = new Color(255, 99, 71); // Tomato Red
    private static final Color COMPARE_COLOR = new Color(255, 215, 0);   // Gold
    private static final Color SORTED_COLOR = new Color(50, 205, 50);    // Lime Green
    private static final Color BACKGROUND_COLOR = new Color(30, 30, 40); // Dark background
    private static final Color TEXT_COLOR = new Color(255, 255, 255);    // White text
    private static final Color STATS_BG_COLOR = new Color(0, 0, 0, 150); // Semi-transparent black
    
    /**
     * Creates a new Visualizer with an empty array.
     */
    public Visualizer() {
        this.array = new int[0];
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(800, 500));
    }
    
    /**
     * Sets the current sorting algorithm info for display.
     */
    public void setAlgorithmInfo(String name, String timeComplexity, String spaceComplexity) {
        this.algorithmName = name;
        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
        this.operationCount = 0;
        this.comparisonCount = 0;
        this.swapCount = 0;
        repaint();
    }
    
    /**
     * Clears the algorithm info display.
     */
    public void clearAlgorithmInfo() {
        this.algorithmName = "";
        this.timeComplexity = "";
        this.spaceComplexity = "";
        this.operationCount = 0;
        this.comparisonCount = 0;
        this.swapCount = 0;
        repaint();
    }
    
    /**
     * Increments the operation counter.
     */
    public void incrementOperations() {
        this.operationCount++;
        repaint();
    }
    
    /**
     * Increments the comparison counter.
     */
    public void incrementComparisons() {
        this.comparisonCount++;
        this.operationCount++;
        repaint();
    }
    
    /**
     * Increments the swap counter.
     */
    public void incrementSwaps() {
        this.swapCount++;
        this.operationCount++;
        repaint();
    }
    
    /**
     * Gets the current operation count.
     */
    public int getOperationCount() {
        return operationCount;
    }
    
    /**
     * Sets the array to visualize.
     * @param array The array of integers
     */
    public void setArray(int[] array) {
        this.array = array.clone();
        this.sortedUpTo = -1;
        this.highlightIndex1 = -1;
        this.highlightIndex2 = -1;
        repaint();
    }
    
    /**
     * Gets the current array.
     * @return The array being visualized
     */
    public int[] getArray() {
        return array;
    }
    
    /**
     * Updates a value at a specific index.
     * @param index The index to update
     * @param value The new value
     */
    public void updateValue(int index, int value) {
        if (index >= 0 && index < array.length) {
            array[index] = value;
            incrementOperations();
            repaint();
        }
    }
    
    /**
     * Swaps two elements in the array.
     * @param i First index
     * @param j Second index
     */
    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        incrementSwaps();
        repaint();
    }
    
    /**
     * Sets the delay between visualization updates.
     * @param delay Delay in milliseconds
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    /**
     * Gets the current delay.
     * @return Delay in milliseconds
     */
    public int getDelay() {
        return delay;
    }
    
    /**
     * Highlights two indices for comparison visualization.
     * @param i First index
     * @param j Second index
     */
    public void highlight(int i, int j) {
        this.highlightIndex1 = i;
        this.highlightIndex2 = j;
        incrementComparisons();
        repaint();
    }
    
    /**
     * Clears all highlights.
     */
    public void clearHighlight() {
        this.highlightIndex1 = -1;
        this.highlightIndex2 = -1;
        repaint();
    }
    
    /**
     * Marks elements as sorted up to the given index.
     * @param index Index up to which elements are sorted
     */
    public void markSorted(int index) {
        this.sortedUpTo = index;
        repaint();
    }
    
    /**
     * Marks all elements as sorted.
     */
    public void markAllSorted() {
        this.sortedUpTo = array.length;
        this.highlightIndex1 = -1;
        this.highlightIndex2 = -1;
        repaint();
    }
    
    /**
     * Sleeps for the visualization delay.
     * Call this in sorting algorithms to animate the process.
     */
    public void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Sleeps for a custom duration.
     * @param ms Duration in milliseconds
     */
    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        int padding = 20;
        int topPadding = 70; // Extra space for title
        
        // Draw algorithm title (top center)
        if (!algorithmName.isEmpty()) {
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 28));
            FontMetrics fm = g2d.getFontMetrics();
            int titleWidth = fm.stringWidth(algorithmName);
            
            // Draw title with shadow
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.drawString(algorithmName, (width - titleWidth) / 2 + 2, 42);
            g2d.setColor(TEXT_COLOR);
            g2d.drawString(algorithmName, (width - titleWidth) / 2, 40);
        }
        
        // Draw statistics box (top right)
        if (!algorithmName.isEmpty()) {
            g2d.setFont(new Font("Consolas", Font.PLAIN, 14));
            FontMetrics fm = g2d.getFontMetrics();
            
            String[] stats = {
                "Time: " + timeComplexity,
                "Space: " + spaceComplexity,
                "Operations: " + operationCount,
                "Comparisons: " + comparisonCount,
                "Swaps: " + swapCount
            };
            
            int boxWidth = 180;
            int lineHeight = 20;
            int boxHeight = stats.length * lineHeight + 16;
            int boxX = width - boxWidth - 15;
            int boxY = 10;
            
            // Draw semi-transparent background
            g2d.setColor(STATS_BG_COLOR);
            g2d.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);
            
            // Draw border
            g2d.setColor(new Color(100, 100, 120));
            g2d.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);
            
            // Draw stats text
            g2d.setColor(TEXT_COLOR);
            int textY = boxY + 20;
            for (String stat : stats) {
                g2d.drawString(stat, boxX + 12, textY);
                textY += lineHeight;
            }
        }
        
        // Draw bars
        if (array == null || array.length == 0) {
            return;
        }
        
        int availableWidth = width - 2 * padding;
        int availableHeight = height - topPadding - padding;
        
        // Calculate bar width and spacing
        int totalBars = array.length;
        int barWidth = Math.max(2, (availableWidth - totalBars) / totalBars);
        int spacing = Math.max(1, barWidth / 10);
        barWidth = (availableWidth - (totalBars - 1) * spacing) / totalBars;
        
        // Find max value for scaling
        int maxValue = 1;
        for (int value : array) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        
        // Draw bars
        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) ((double) array[i] / maxValue * availableHeight);
            int x = padding + i * (barWidth + spacing);
            int y = height - padding - barHeight;
            
            // Choose color based on state
            Color barColor;
            if (sortedUpTo >= 0 && i < sortedUpTo) {
                barColor = SORTED_COLOR;
            } else if (i == highlightIndex1) {
                barColor = HIGHLIGHT_COLOR;
            } else if (i == highlightIndex2) {
                barColor = COMPARE_COLOR;
            } else if (sortedUpTo >= array.length) {
                barColor = SORTED_COLOR;
            } else {
                barColor = BAR_COLOR;
            }
            
            // Draw bar with gradient effect
            GradientPaint gradient = new GradientPaint(
                x, y, barColor.brighter(),
                x + barWidth, y + barHeight, barColor.darker()
            );
            g2d.setPaint(gradient);
            g2d.fillRoundRect(x, y, barWidth, barHeight, 4, 4);
            
            // Draw border
            g2d.setColor(barColor.darker().darker());
            g2d.drawRoundRect(x, y, barWidth, barHeight, 4, 4);
        }
    }
}

