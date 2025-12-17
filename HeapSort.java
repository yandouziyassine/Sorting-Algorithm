/**
 * Heap Sort implementation.
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */
public class HeapSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int n = array.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, v);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            v.highlight(0, i);
            v.swap(0, i);
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            v.sleep();
            
            // Mark as sorted
            v.markSorted(n - i);
            
            // Heapify reduced heap
            heapify(array, i, 0, v);
        }
        
        v.markAllSorted();
    }
    
    /**
     * Heapifies a subtree rooted at index i.
     */
    private void heapify(int[] array, int n, int i, Visualizer v) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        // Check if left child is larger than root
        if (left < n) {
            v.highlight(largest, left);
            v.sleep();
            if (array[left] > array[largest]) {
                largest = left;
            }
        }
        
        // Check if right child is larger than current largest
        if (right < n) {
            v.highlight(largest, right);
            v.sleep();
            if (array[right] > array[largest]) {
                largest = right;
            }
        }
        
        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            v.highlight(i, largest);
            v.swap(i, largest);
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            v.sleep();
            
            heapify(array, n, largest, v);
        }
    }
    
    @Override
    public String getName() {
        return "Heap Sort";
    }
    
    @Override
    public String getTimeComplexity() {
        return "O(n log n)";
    }
    
    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}

