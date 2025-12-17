/**
 * Bubble Sort implementation.
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
public class BubbleSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int n = array.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                // Highlight the elements being compared
                v.highlight(j, j + 1);
                v.sleep();
                
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    v.swap(j, j + 1);
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    v.sleep();
                }
            }
            
            // Mark the last element of this pass as sorted
            v.markSorted(n - i);
            
            // If no swapping occurred, array is already sorted
            if (!swapped) {
                break;
            }
        }
        
        // Mark all as sorted
        v.markAllSorted();
    }
    
    @Override
    public String getName() {
        return "Bubble Sort";
    }
    
    @Override
    public String getTimeComplexity() {
        return "O(n²)";
    }
    
    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}

