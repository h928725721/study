/**
 * 希尔排序：
 *  将数组按照增量序列分解成若干的小数组，例如增量序列为5
 *  12 23 2 35 22
 *  13 35 90 87 11
 *  19 35
 *  分别排序 12 13 19/23 35 35/2 90/35 87/22 11
 *  序列最终减为1，变成插入排序
 */
public class Shellsort {




    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }
}
