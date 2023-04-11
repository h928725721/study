import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序
 * 将数组中最大值-最小值+1 算出桶的个数
 * 将数据计算出索引放到对应的桶中
 * 每个桶都是一个list，对每个桶内的数据采用排序算法进行排序
 * 最后再打平
 */
public class BucketSort {
    private static int indexFor(int a, int min, int step) {
        return (a - min) / step;
    }

    public static void bucketSort(int[] arr) {

        int max = arr[0], min = arr[0];
        for (int a : arr) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }
        // 該值也可根據實際情況選擇
        int bucketNum = max / 10 - min / 10 + 1;
        List<List<Integer>> buckList = new ArrayList<>();
        // create bucket
        for (int i = 1; i <= bucketNum; i++) {
            buckList.add(new ArrayList<>());
        }
        // push into the bucket
        for (int j : arr) {
            int index = indexFor(j, min, 10);
            buckList.get(index).add(j);
        }
        ArrayList<Integer> bucket;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = (ArrayList<Integer>) buckList.get(i);
            insertSort(bucket);
            for (int k : bucket) {
                arr[index++] = k;
            }
        }

    }

    // 把桶內元素插入排序
    private static void insertSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }
}
