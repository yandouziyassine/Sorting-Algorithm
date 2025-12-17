/**
 * Interface for sorting algorithms.
 * All sorting implementations must implement this interface.
 */
public interface Sorter {
    /**
     * Sorts the given array and visualizes the process.
     * @param array The array to sort
     * @param v The visualizer to display the sorting progress
     */
    void sort(int[] array, Visualizer v);
    
    /**
     * Returns the name of the sorting algorithm.
     * @return The algorithm name
     */
    String getName();
    
    /**
     * Returns the time complexity of the algorithm.
     * @return Time complexity string (e.g., "O(n²)")
     */
    String getTimeComplexity();
    
    /**
     * Returns the space complexity of the algorithm.
     * @return Space complexity string (e.g., "O(1)")
     */
    String getSpaceComplexity();
}

