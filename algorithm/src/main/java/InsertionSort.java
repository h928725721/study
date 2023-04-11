/**
 * 插入排序
 * 假设所取的第一个数已经在排序中，分别用它的后一个数对别前面已经排序好的数据，并将其插入到合适的位置
 */
public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int j;
        for (int p = 1; p < arr.length; p++) {
            Integer tmp = arr[p];
            for (j = p; j > 0 && tmp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }
}
