import java.util.Random;

//WARNING: Extremely inefficient! For educational/entertainment purposes only.
public class BogoSort implements Sorter {
    private static final int MAX_ITERATIONS = 100000;
    private final Random random = new Random();
    
    @Override
    public void sort(int[] array, Visualizer v) {
        int iterations = 0;
        
        while (!isSorted(array, v) && iterations < MAX_ITERATIONS) {
            shuffle(array, v);
            iterations++;
        }
        
        if (iterations >= MAX_ITERATIONS) {
            java.util.Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                v.updateValue(i, array[i]);
            }
        }
        
        v.markAllSorted();
    }
   
    private boolean isSorted(int[] array, Visualizer v) {
        for (int i = 0; i < array.length - 1; i++) {
            v.highlight(i, i + 1);
            v.sleep();
            
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    
    private void shuffle(int[] array, Visualizer v) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            
            v.highlight(i, j);
            v.swap(i, j);
            
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        v.sleep();
    }
    
    @Override
    public String getName() {
        return "Bogo Sort";
    }
    
    @Override
    public String getTimeComplexity() {
        return "O((n+1)!)";
    }
    
    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}

