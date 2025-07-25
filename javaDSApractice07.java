import java.util.*;

public class Main {

    static void PrintArr(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    static void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    static void RevArr(int[] arr) {
        System.out.print("Original Array: ");
        PrintArr(arr);
        int n = arr.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            swap(arr, i, j);
        }
        System.out.print("Reversed Array: ");
        PrintArr(arr);
    }

    static void Threewaypartitioning(int[] arr, int low, int high) {
        System.out.print("Original Array: ");
        PrintArr(arr);
        int start = 0, mid = 0, end = arr.length - 1;
        while (mid <= end) {
            if (arr[mid] < low) {
                swap(arr, start++, mid++);
            } else if (arr[mid] > high) {
                swap(arr, mid, end--);
            } else {
                mid++;
            }
        }
        System.out.print("3-Way Partitioned Array: ");
        PrintArr(arr);
    }

    static void SmallestSubarraySumGreaterThanK(int[] arr, int k) {
        System.out.println("Smallest Subarray With Sum ≥ " + k);
        int n = arr.length, minLen = n + 1;
        for (int start = 0; start < n; start++) {
            int sum = arr[start];
            if (sum >= k) {
                System.out.println("Length = 1");
                return;
            }
            for (int end = start + 1; end < n; end++) {
                sum += arr[end];
                if (sum >= k && (end - start + 1) < minLen) {
                    minLen = end - start + 1;
                }
            }
        }
        System.out.println(minLen <= n ? "Length = " + minLen : "No subarray found");
    }

    static void inversion(int[] arr) {
        int count = 0, n = arr.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] > arr[j]) count++;
        System.out.println("Inversion Count: " + count);
    }

    static void maximumproductsubarray(int[] arr) {
        int maxProd = arr[0], currMax = arr[0], currMin = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) {
                int temp = currMax;
                currMax = currMin;
                currMin = temp;
            }
            currMax = Math.max(arr[i], currMax * arr[i]);
            currMin = Math.min(arr[i], currMin * arr[i]);
            maxProd = Math.max(maxProd, currMax);
        }
        System.out.println("Max Product Subarray: " + maxProd);
    }

    static void findElementsMoreThanNbyK(int[] arr, int k) {
        int n = arr.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) freq.put(num, freq.getOrDefault(num, 0) + 1);
        int threshold = n / k;
        System.out.println("Elements appearing more than n/k:");
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > threshold) {
                System.out.println(entry.getKey());
            }
        }
    }

    static void minimumSwaps(int[] arr, int k) {
        int count = 0;
        for (int num : arr) if (num <= k) count++;

        int bad = 0;
        for (int i = 0; i < count; i++)
            if (arr[i] > k) bad++;

        int minSwaps = bad;
        for (int i = 0, j = count; j < arr.length; i++, j++) {
            if (arr[i] > k) bad--;
            if (arr[j] > k) bad++;
            minSwaps = Math.min(minSwaps, bad);
        }

        System.out.println("Minimum Swaps to bring <= k together: " + minSwaps);
    }

    static void minOpsToMakePalindrome(int[] arr) {
        int left = 0, right = arr.length - 1, count = 0;
        while (left < right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else if (arr[left] < arr[right]) {
                arr[left + 1] += arr[left];
                left++;
                count++;
            } else {
                arr[right - 1] += arr[right];
                right--;
                count++;
            }
        }
        System.out.println("Minimum Operations to Make Palindrome: " + count);
    }

    static void findLongestConsecutiveSorted(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Longest Consecutive Subsequence Length: 0");
            return;
        }

        Arrays.sort(arr);
        int maxLen = 1, currLen = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) continue;
            else if (arr[i] == arr[i - 1] + 1) currLen++;
            else {
                maxLen = Math.max(maxLen, currLen);
                currLen = 1;
            }
        }
        maxLen = Math.max(maxLen, currLen);
        System.out.println("Longest Consecutive Subsequence Length: " + maxLen);
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 9, 5, 7, 6};
        int[] arr2 = {2, 4, 1, 3, 5};
        int[] arr3 = {2, 3, -2, 4};
        int[] arr4 = {3, 1, 2, 2, 1, 2, 3, 3};
        int[] arr5 = {2, 1, 5, 6, 3};
        int[] arr6 = {5, 3, 2, 1, 4, 2, 3, 5};

        SmallestSubarraySumGreaterThanK(arr, 9);
        Threewaypartitioning(arr, 3, 6);
        inversion(arr2);
        maximumproductsubarray(arr3);
        findElementsMoreThanNbyK(arr4, 4);
        minimumSwaps(arr5, 3);
        minOpsToMakePalindrome(arr6);
        findLongestConsecutiveSorted(arr);
    }
}
