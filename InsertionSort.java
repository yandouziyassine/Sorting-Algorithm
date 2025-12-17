/**
 * Insertion Sort implementation.
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
public class InsertionSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int n = array.length;
        
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            
            v.highlight(i, -1);
            v.sleep();
            
            // Move elements greater than key one position ahead
            while (j >= 0 && array[j] > key) {
                v.highlight(j, j + 1);
                
                array[j + 1] = array[j];
                v.updateValue(j + 1, array[j]);
                v.sleep();
                
                j--;
            }
            
            // Place key at correct position
            array[j + 1] = key;
            v.updateValue(j + 1, key);
            v.highlight(j + 1, -1);
            v.sleep();
            
            v.markSorted(i + 1);
        }
        
        v.markAllSorted();
    }
    
    @Override
    public String getName() {
        return "Insertion Sort";
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

