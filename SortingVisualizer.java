import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main application class for the Sorting Algorithm Visualizer.
 * Creates a GUI with visualization panel and control buttons.
 */
public class SortingVisualizer extends JFrame {
    private final Visualizer visualizer;
    private final List<JButton> sortButtons = new ArrayList<>();
    private final JButton resetBtn;
    private final JSlider speedSlider;
    private final JSlider sizeSlider;
    private final JLabel statusLabel;
    
    private int[] originalArray;
    private Thread sortingThread;
    private volatile boolean isSorting = false;
    
    private static final int DEFAULT_ARRAY_SIZE = 50;
    private static final int MIN_VALUE = 10;
    private static final int MAX_VALUE = 500;
    
    /**
     * Creates the main application window.
     */
    public SortingVisualizer() {
        setTitle("Sorting Algorithm Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Create visualizer panel
        visualizer = new Visualizer();
        visualizer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(visualizer, BorderLayout.CENTER);
        
        // Create control panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        // Define all sorting algorithms with their colors
        Object[][] algorithms = {
            {"Bubble Sort", new Color(231, 76, 60), new BubbleSort()},
            {"Selection Sort", new Color(230, 126, 34), new SelectionSort()},
            {"Insertion Sort", new Color(241, 196, 15), new InsertionSort()},
            {"Merge Sort", new Color(46, 204, 113), new MergeSort()},
            {"Quick Sort", new Color(26, 188, 156), new QuickSort()},
            {"Heap Sort", new Color(52, 152, 219), new HeapSort()},
            {"Tim Sort", new Color(155, 89, 182), new TimSort()},
            {"Bucket Sort", new Color(52, 73, 94), new BucketSort()},
            {"Bogo Sort", new Color(192, 57, 43), new BogoSort()}
        };
        
        // Create sort buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 8, 8));
        buttonPanel.setBackground(new Color(45, 45, 55));
        
        for (Object[] algo : algorithms) {
            String name = (String) algo[0];
            Color color = (Color) algo[1];
            Sorter sorter = (Sorter) algo[2];
            
            JButton btn = createStyledButton(name, color);
            btn.addActionListener(e -> startSort(sorter));
            sortButtons.add(btn);
            buttonPanel.add(btn);
        }
        
        // Reset button
        resetBtn = createStyledButton("Reset Array", new Color(127, 140, 141));
        resetBtn.addActionListener(e -> resetArray());
        buttonPanel.add(resetBtn);
        
        // Speed slider
        speedSlider = new JSlider(1, 200, 50);
        speedSlider.setMajorTickSpacing(50);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBackground(new Color(45, 45, 55));
        speedSlider.setForeground(Color.WHITE);
        speedSlider.addChangeListener(e -> {
            int value = speedSlider.getValue();
            visualizer.setDelay(201 - value); // Invert so higher = faster
        });
        
        // Size slider
        sizeSlider = new JSlider(10, 200, DEFAULT_ARRAY_SIZE);
        sizeSlider.setMajorTickSpacing(50);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setBackground(new Color(45, 45, 55));
        sizeSlider.setForeground(Color.WHITE);
        sizeSlider.addChangeListener(e -> {
            if (!isSorting) {
                generateRandomArray(sizeSlider.getValue());
            }
        });
        
        // Status label
        statusLabel = new JLabel("Ready - Click a sort button to begin");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Slider panel
        JPanel sliderPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        sliderPanel.setBackground(new Color(45, 45, 55));
        JPanel speedPanel = createLabeledSlider("Speed:", speedSlider);
        JPanel sizePanel = createLabeledSlider("Array Size:", sizeSlider);
        sliderPanel.add(speedPanel);
         sliderPanel.add(sizePanel);
        controlPanel.add(buttonPanel, BorderLayout.NORTH);
        controlPanel.add(sliderPanel, BorderLayout.CENTER);
        controlPanel.add(statusLabel, BorderLayout.SOUTH);
        


        // Generate initial array
        generateRandomArray(DEFAULT_ARRAY_SIZE);
        
        // Final setup
        pack();
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 40));
    }
    


    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(45, 45, 55));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));
        return panel;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    private JPanel createLabeledSlider(String labelText, JSlider slider) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(new Color(45, 45, 55));
        
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        panel.add(label, BorderLayout.WEST);
        panel.add(slider, BorderLayout.CENTER);
        
        return panel;
    }
    
    
     // Generates a random array with the specified size.
     
    private void generateRandomArray(int size) {
        Random random = new Random();
        originalArray = new int[size];
        
        for (int i = 0; i < size; i++) {
            originalArray[i] = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
        }
        
        visualizer.setArray(originalArray);
        statusLabel.setText("Ready - Array size: " + size);
    }
    
    
     // Resets the array 
    
    private void resetArray() {
        if (isSorting && sortingThread != null) {
            sortingThread.interrupt();
            isSorting = false;
        }
        
        visualizer.clearAlgorithmInfo();
        generateRandomArray(sizeSlider.getValue());
        setButtonsEnabled(true);
    }
    
    //start sorting
    private void startSort(Sorter sorter) {
        if (isSorting) {
            return;
        }
        
        isSorting = true;
        setButtonsEnabled(false);
        resetBtn.setEnabled(true); // Keep reset enabled
        
    
        int[] arrayToSort = originalArray.clone();
        visualizer.setArray(arrayToSort);
        
        // Set algorithm info for display
        visualizer.setAlgorithmInfo(
            sorter.getName(),
            sorter.getTimeComplexity(),
            sorter.getSpaceComplexity()
        );
        
        statusLabel.setText("Running: " + sorter.getName() + "...");
        
        
        sortingThread = new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                sorter.sort(arrayToSort, visualizer);
                long endTime = System.currentTimeMillis();
                
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText(sorter.getName() + " completed in " + 
                                        (endTime - startTime) + " ms");
                    isSorting = false;
                    setButtonsEnabled(true);
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Sorting interrupted");
                    isSorting = false;
                    setButtonsEnabled(true);
                });
            }
        });
        
        sortingThread.start();
    }
    
 
     
    private void setButtonsEnabled(boolean enabled) {
        for (JButton btn : sortButtons) {
            btn.setEnabled(enabled);
        }
        sizeSlider.setEnabled(enabled);
    }
    
   
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel
        }
        
        // Launch application on EDT
        SwingUtilities.invokeLater(() -> {
            SortingVisualizer app = new SortingVisualizer();
            app.setVisible(true);
        });
    }
}
