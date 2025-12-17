# 📊 Sorting Algorithm Visualizer

A modular Java Swing application that visualizes various sorting algorithms with real-time animations and performance statistics.


## Features

- **9 Sorting Algorithms** — Bubble, Selection, Insertion, Merge, Quick, Heap, Tim, Bucket, and Bogo Sort
- **Real-time Visualization** — Watch algorithms sort with color-coded bar animations
- **Live Statistics** — View time/space complexity, operation counts, comparisons, and swaps
- **Adjustable Speed** — Control animation speed with a slider
- **Dynamic Array Size** — Generate arrays from 10 to 200 elements
- **Threaded Execution** — GUI remains responsive during sorting


### Prerequisites

- Java JDK 8 or higher

### Compile & Run

bash:
cd Sorting
javac *.java
java SortingVisualizer


## 📁 Project Structure


Sorting
├── Sorter.java            # Interface for all sorting algorithms
├── Visualizer.java       # JPanel with histogram rendering & stats
├── SortingVisualizer.java # Main JFrame application
├── BubbleSort.java         # O(n²) - Simple comparison sort
├── SelectionSort.java    # O(n²) - Finds minimum each pass
├── InsertionSort.java    # O(n²) - Builds sorted portion
├── MergeSort.java        # O(n log n) - Divide and conquer
├── QuickSort.java        # O(n log n) - Partition-based
├── HeapSort.java         # O(n log n) - Heap data structure
├── TimSort.java          # O(n log n) - Hybrid (Insertion + Merge)
├── BucketSort.java       # O(n + k) - Distribution sort
└── BogoSort.java         # O((n+1)!) - Random shuffle (for fun!)


## 🎨 Color Legend

| Color | Meaning |
|🔵 Blue | Unsorted element |
|🔴 Red  | Currently selected/comparing (primary) |
|🟡 Gold | Currently comparing (secondary) |
|🟢 Green| Sorted element |

## 📈 Algorithm Complexities

| Algorithm | Time (Best) | Time (Worst) | Space |<br>
| Bubble Sort | O(n) | O(n²) | O(1) |<br>
| Selection Sort | O(n²) | O(n²) | O(1) |<br>
| Insertion Sort | O(n) | O(n²) | O(1) |<br>
| Merge Sort | O(n log n) | O(n log n) | O(n) |<br>
| Quick Sort | O(n log n) | O(n²) | O(log n) |<br>
| Heap Sort | O(n log n) | O(n log n) | O(1) |<br>
| Tim Sort | O(n) | O(n log n) | O(n) |<br>
| Bucket Sort | O(n + k) | O(n²) | O(n + k) |<br>
| Bogo Sort | O(n) | O(∞) | O(1) |<br>


## 📝 License
This project is open source and available under the MIT License.



Feel free to fork this project and submit pull requests with new sorting algorithms or UI improvements!
