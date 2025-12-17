import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Bucket Sort implementation.
 * Time Complexity: O(n + k) average, O(n²) worst case
 * Space Complexity: O(n + k)
 */
public class BucketSort implements Sorter {
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int n = array.length;
        if (n <= 0) return;
        
        // Find min and max values
        int min = array[0], max = array[0];
        for (int i = 1; i < n; i++) {
            v.highlight(i, -1);
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }
        v.sleep();
        
        // Create buckets
        int bucketCount = (int) Math.sqrt(n);
        if (bucketCount < 1) bucketCount = 1;
        
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        
        // Distribute elements into buckets
        int range = max - min + 1;
        for (int i = 0; i < n; i++) {
            v.highlight(i, -1);
            v.sleep();
            
            int bucketIndex = (int) ((long)(array[i] - min) * (bucketCount - 1) / range);
            buckets.get(bucketIndex).add(array[i]);
        }
        
        // Sort individual buckets and place back
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            List<Integer> bucket = buckets.get(i);
            Collections.sort(bucket);
            
            for (int value : bucket) {
                array[index] = value;
                v.updateValue(index, value);
                v.highlight(index, -1);
                v.sleep();
                index++;
            }
        }
        
        v.markAllSorted();
    }
    
    @Override
    public String getName() {
        return "Bucket Sort";
    }
    
    @Override
    public String getTimeComplexity() {
        return "O(n + k)";
    }
    
    @Override
    public String getSpaceComplexity() {
        return "O(n + k)";
    }
}

