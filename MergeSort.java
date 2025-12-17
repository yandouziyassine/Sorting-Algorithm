/**
 * Merge Sort implementation.
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class MergeSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        mergeSort(array, 0, array.length - 1, v);
        v.markAllSorted();
    }
    
    /**
     * Recursive merge sort implementation.
     */
    private void mergeSort(int[] array, int left, int right, Visualizer v) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSort(array, left, mid, v);
            mergeSort(array, mid + 1, right, v);
            
            // Merge the sorted halves
            merge(array, left, mid, right, v);
        }
    }
    
    /**
     * Merges two sorted subarrays.
     */
    private void merge(int[] array, int left, int mid, int right, Visualizer v) {
        // Calculate sizes of subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temporary arrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }
        
        // Merge the temporary arrays back
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            // Highlight the elements being compared
            v.highlight(left + i, mid + 1 + j);
            v.sleep();
            
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                v.updateValue(k, leftArray[i]);
                i++;
            } else {
                array[k] = rightArray[j];
                v.updateValue(k, rightArray[j]);
                j++;
            }
            k++;
            v.sleep();
        }
        
        // Copy remaining elements of leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            v.updateValue(k, leftArray[i]);
            v.highlight(k, -1);
            v.sleep();
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            v.updateValue(k, rightArray[j]);
            v.highlight(k, -1);
            v.sleep();
            j++;
            k++;
        }
        
        v.clearHighlight();
    }
    
    @Override
    public String getName() {
        return "Merge Sort";
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

