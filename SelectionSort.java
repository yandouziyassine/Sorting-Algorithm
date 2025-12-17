/**
 * Selection Sort implementation.
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
public class SelectionSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int n = array.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            // Find minimum element in unsorted portion
            for (int j = i + 1; j < n; j++) {
                v.highlight(minIndex, j);
                v.sleep();
                
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap minimum with first unsorted element
            if (minIndex != i) {
                v.highlight(i, minIndex);
                v.swap(i, minIndex);
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                v.sleep();
            }
            
            // Mark element as sorted
            v.markSorted(i + 1);
        }
        
        v.markAllSorted();
    }
    
    @Override
    public String getName() {
        return "Selection Sort";
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

