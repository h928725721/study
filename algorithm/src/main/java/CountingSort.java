public class CountingSort {
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        int minValue = arr[0];
        int maxValue = arr[0];

        // 找到最大值和最小值
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            }
            if (value > maxValue) {
                maxValue = value;
            }
        }

        // 创建计数数组
        int[] count = new int[maxValue - minValue + 1];

        // 统计每个元素出现的次数
        for (int value : arr) {
            count[value - minValue]++;
        }

        // 将计数数组变成前缀和数组
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // 创建临时数组
        int[] output = new int[n];

        // 将元素放入临时数组中
        for (int i = n - 1; i >= 0; i--) {
            int index = count[arr[i] - minValue] - 1;
            output[index] = arr[i];
            count[arr[i] - minValue]--;
        }

        // 将临时数组拷贝回原数组中
        System.arraycopy(output, 0, arr, 0, n);
    }


}