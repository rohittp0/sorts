import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running with 1000 elements\n");
        int[] unsortedArray = createArrayWithRandomInts(1000);

        bubbleSort(unsortedArray);
        selectionSort(unsortedArray);
        insertionSort(unsortedArray);
        benchmarkQuickSort(unsortedArray);
        benchmarkMergeSort(unsortedArray);
        benchmarkFlashSort(unsortedArray);

        System.out.println("\n\nRunning with 100,000 elements\n");
        unsortedArray = createArrayWithRandomInts(100000);

        bubbleSort(unsortedArray);
        selectionSort(unsortedArray);
        insertionSort(unsortedArray);
        benchmarkQuickSort(unsortedArray);
        benchmarkMergeSort(unsortedArray);
        benchmarkFlashSort(unsortedArray);
    }

    /**
     * Sorting an array of ints in ascending order using bubbleSort
     * Best-Case Complexity: O(n), Average Complexity: O(n^2), Worst-Case Complexity: O(n^2)
     * O(n) is achieved in Best-Case (already sorted array) using the alreadySorted flag
     */
    static void bubbleSort(int @NotNull [] array) {
        int temp;
        boolean alreadySorted = true;
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {


            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    alreadySorted = false;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            if (alreadySorted) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Array sorted with bubble sort in :" + (end - start) + "ms");
    }

    /**
     * Sorting an array of ints in ascending order using selectionSort
     * Best-Case Complexity: O(n^2), Average Complexity: O(n^2), Worst-Case Complexity: O(n^2)
     */
    static void selectionSort(int @NotNull [] array) {
        int min;
        int pos = 0;
        long start = System.currentTimeMillis();

        for (int i = 0; i < array.length - 1; i++) {


            min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    pos = j;
                }
            }
            array[pos] = array[i];
            array[i] = min;
        }

        long end = System.currentTimeMillis();
        System.out.println("Array sorted with selection sort in :" + (end - start) + "ms");
    }

    /**
     * Sorting an array of ints in ascending order using insertionSort
     * Best-Case Complexity: O(n), Average Complexity: O(n^2), Worst-Case Complexity: O(n^2)
     */
    static void insertionSort(int @NotNull [] array) {
        long start = System.currentTimeMillis();
        int j;

        for (int i = 1; i < array.length; i++) {


            int key = array[i];

            for (j = i - 1; (j >= 0) && (key < array[j]); j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }

        long end = System.currentTimeMillis();
        System.out.println("Array sorted with insertion sort in :" + (end - start) + "ms");
    }

    /**
     * Sorting an array of ints in ascending order using quickSort
     * Best-Case Complexity: O(n log(n)), Average Complexity: O(n log(n)), Worst-Case Complexity: O(n^2))
     */
    static void quickSort(int @NotNull [] array, int low, int high) {
        int pivot = array[low + ((high - low) / 2)];
        int i = low;
        int j = high;


        while (i <= j) {

            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

            i++;
            j--;
        }

        if (low < j) {
            quickSort(array, low, j);
        }

        if (i < high) {
            quickSort(array, i, high);
        }
    }

    /**
     * Helping method to benchmark quick sort's execution time
     */
    static void benchmarkQuickSort(int[] array) {
        long start = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("Array sorted with quick sort in :" + (end - start) + "ms");
    }

    /**
     * Sorting an array of ints in ascending order using mergeSort
     * Best-Case Complexity: O(n log(n)), Average Complexity: O(n log(n)), Worst-Case Complexity: O(n log(n)))
     */
    public static void mergeSort(int @NotNull [] array) {
        if (array.length == 1) {
            return;
        }

        int[] array1 = new int[(array.length / 2)];
        int[] array2 = new int[(array.length - array1.length)];

        System.arraycopy(array, 0, array1, 0, array1.length);
        System.arraycopy(array, array1.length, array2, 0, array2.length);

        mergeSort(array1);
        mergeSort(array2);

        merge(array1, array2, array);
    }

    /**
     * Merges 2 sorted arrays of ints
     */
    static void merge(int @NotNull [] array1, int[] array2, int[] mergedArray) {
        int array1Index = 0;
        int array2Index = 0;
        int pos = 0;
        while ((array1Index < array1.length) && (array2Index < array2.length)) {
            if (array1[array1Index] < array2[array2Index]) {
                mergedArray[pos] = array1[array1Index];
                array1Index++;
            } else {
                mergedArray[pos] = array2[array2Index];
                array2Index++;
            }
            pos++;
        }

        if (array1Index < array2Index) {
            System.arraycopy(array1, array1Index, mergedArray, pos, array1.length - array1Index);
        }

        System.arraycopy(array2, array2Index, mergedArray, pos, array2.length - array2Index);

    }

    /**
     * Helping method to benchmark merge sort's execution time
     */
    static void benchmarkMergeSort(int[] array) {
        long start = System.currentTimeMillis();
        mergeSort(array);
        long end = System.currentTimeMillis();
        System.out.println("Array sorted with merge sort in :" + (end - start) + "ms");
    }

    static void flashSort(int @NotNull [] a)
    {
        int[] l = new int[Math.min(a.length / 20, 30)];
        int i, j = 0, k;
        int anmin = a[0];
        int nmax  = 0;


        for (i=1; i < a.length; i++)


        {
            if (a[i] < anmin) anmin=a[i];
            if (a[i] > a[nmax]) nmax=i;
        }


        if (anmin == a[nmax]) return;


        double c1 = ((double)l.length - 1)/(a[nmax] - anmin);


        for (i=0; i < a.length; i++)
        {
            k=(int)(c1*(a[i] - anmin));
            l[k]++;
        }


        for (k=1; k < l.length; k++)
        {
            l[k] += l[k-1];
        }


        int hold = a[nmax];
        a[nmax]=a[0];
        a[0]=hold;


        int nmove = 0;
        int flash;
        k=l.length-1;


        while (nmove < a.length-1)


        {
            while (j > (l[k]-1))


            {
                j++;
                k = (int)(c1 * (a[j] - anmin));
            }


            flash = a[j];


            while (!(j == l[k]))


            {
                k = (int) (c1 * (flash - anmin));


                hold = a[l[k]-1];
                a[l[k]-1]=flash;
                flash = hold;


                l[k]--;
                nmove++;
            }
        }
    }


    static void benchmarkFlashSort(int[] array) {
        long start = System.currentTimeMillis();
        flashSort(array);
        long end = System.currentTimeMillis();
        System.out.println("Array sorted with flash sort in :" + (end - start) + "ms");
    }



    /**
     * Creates and returns an array with random ints
     *
     * @param size the size of the array to be created
     */
    static int @NotNull [] createArrayWithRandomInts(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Math.random() * 100000);
        }
        return array;
    }

}