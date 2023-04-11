import java.util.Arrays;

public class MainTest {

    public static void main(String[] args) {
        int[] arr = new int[]{52, 36, 1, 25, 37, 42, 98, 64, 33, 15, 18, 19, 76, 99, 92, 54, 66};
        //Shellsort.shellSort(arr);
        //InsertionSort.insertionSort(arr);
        //BucketSort.bucketSort(arr);
        //CountingSort.countingSort(arr);
        new HeapSort(arr).sort();

        System.out.println(Arrays.toString(arr));

    }
}
