import java.util.*;
import java.util.stream.Stream;

/*
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

    The overall run time complexity should be O(log (m+n)).

    

    Example 1:

    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
    Example 2:

    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
    

    Constraints:

    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106

*/
class Solution {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Solution().new RunnableImpl());
        t1.start();
    }

    private class RunnableImpl implements Runnable {

        public void run() {
            int[] arrr = new int[]{1,3};
            int[] arrr2 = new int[]{2};

            findMedianSortedArrays(arrr, arrr2);
            System.out.println(findMedianSortedArrays(arrr, arrr2));
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> mergedList = getMergedList(nums1, nums2);
        Collections.sort(mergedList);

        int length = mergedList.size();
        if (length == 1) return mergedList.get(0);
        if (length == 0) return 0;

        boolean evenLength = length % 2 == 0;
        if (evenLength) {
            return getMedianValueOfEvenLengthList(mergedList, length);
        } else {
            return getMedianValueOfOddLengthList(mergedList, length);
        }
    }

    private double getMedianValueOfOddLengthList(List<Integer> mergedList, int length) {
        int medianIndexValue = Math.round((float) length / 2);
        return Double.valueOf(mergedList.get(medianIndexValue - 1));
    }

    private double getMedianValueOfEvenLengthList(List<Integer> mergedList, int length) {
        Double value = Double.valueOf(mergedList.get((length - 1) / 2));
        Double value2 = Double.valueOf(mergedList.get(((length - 1) / 2) + 1));
        return (value + value2) / 2;
    }

    private List<Integer> getMergedList(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream(nums1).boxed().toList();
        List<Integer> list2 = Arrays.stream(nums2).boxed().toList();
        return new LinkedList<>(Stream.concat(list1.stream(), list2.stream()).toList());
    }
}