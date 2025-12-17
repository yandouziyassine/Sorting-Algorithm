/**
 * Quick Sort implementation.
 * Time Complexity: O(n log n) average, O(n²) worst case
 * Space Complexity: O(log n)
 */
public class QuickSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        quickSort(array, 0, array.length - 1, v);
        v.markAllSorted();
    }
    
    /**
     * Recursive quick sort implementation.
     */
    private void quickSort(int[] array, int low, int high, Visualizer v) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, v);
            quickSort(array, low, pivotIndex - 1, v);
            quickSort(array, pivotIndex + 1, high, v);
        }
    }
    
    /**
     * Partitions the array around a pivot element.
     * Uses the last element as pivot.
     */
    private int partition(int[] array, int low, int high, Visualizer v) {
        int pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // Highlight current element and pivot
            v.highlight(j, high);
            v.sleep();
            
            if (array[j] <= pivot) {
                i++;
                if (i != j) {
                    // Swap elements
                    v.swap(i, j);
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    v.sleep();
                }
            }
        }
        
        // Place pivot in correct position
        if (i + 1 != high) {
            v.highlight(i + 1, high);
            v.swap(i + 1, high);
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            v.sleep();
        }
        
        return i + 1;
    }
    
    @Override
    public String getName() {
        return "Quick Sort";
    }
    
    @Override
    public String getTimeComplexity() {
        return "O(n log n)";
    }
    
    @Override
    public String getSpaceComplexity() {
        return "O(log n)";
    }
}

