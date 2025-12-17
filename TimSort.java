/**
 * Tim Sort implementation (simplified version).
 * Combines Insertion Sort for small runs with Merge Sort for merging.
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class TimSort implements Sorter {
    private static final int MIN_RUN = 32;
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int n = array.length;
        
        // Sort individual runs using insertion sort
        for (int i = 0; i < n; i += MIN_RUN) {
            insertionSort(array, i, Math.min(i + MIN_RUN - 1, n - 1), v);
        }
        
        // Merge runs
        for (int size = MIN_RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                
                if (mid < right) {
                    merge(array, left, mid, right, v);
                }
            }
        }
        
        v.markAllSorted();
    }
    
    /**
     * Insertion sort for a portion of the array.
     */
    private void insertionSort(int[] array, int left, int right, Visualizer v) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            
            v.highlight(i, -1);
            v.sleep();
            
            while (j >= left && array[j] > key) {
                v.highlight(j, j + 1);
                array[j + 1] = array[j];
                v.updateValue(j + 1, array[j]);
                v.sleep();
                j--;
            }
            
            array[j + 1] = key;
            v.updateValue(j + 1, key);
        }
    }
    
    /**
     * Merges two sorted runs.
     */
    private void merge(int[] array, int left, int mid, int right, Visualizer v) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        
        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];
        
        for (int i = 0; i < len1; i++) {
            leftArr[i] = array[left + i];
        }
        for (int i = 0; i < len2; i++) {
            rightArr[i] = array[mid + 1 + i];
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < len1 && j < len2) {
            v.highlight(left + i, mid + 1 + j);
            v.sleep();
            
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                v.updateValue(k, leftArr[i]);
                i++;
            } else {
                array[k] = rightArr[j];
                v.updateValue(k, rightArr[j]);
                j++;
            }
            k++;
        }
        
        while (i < len1) {
            array[k] = leftArr[i];
            v.updateValue(k, leftArr[i]);
            v.highlight(k, -1);
            v.sleep();
            i++;
            k++;
        }
        
        while (j < len2) {
            array[k] = rightArr[j];
            v.updateValue(k, rightArr[j]);
            v.highlight(k, -1);
            v.sleep();
            j++;
            k++;
        }
        
        v.clearHighlight();
    }
    
    @Override
    public String getName() {
        return "Tim Sort";
    }
    
    @Override
    public String getTimeComplexity() {
        return "O(n log n)";
    }
    
    @Override
    public String getSpaceComplexity() {
        return "O(n)";
    }
}

