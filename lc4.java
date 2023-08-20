public class lc4 {
    public static void main(String[] args) {

    }
}

class Solution {
    // l1, r1    l2,r2
    // how to explain the devide phase?
    // how to explain how we initiate l1, l2?
    /*
    The goal of this issue is to find the median of the two sorted arrays. Basically we are performing a binary search and view the two
    sorted array as a single sorted array. We'll try to find a dividing point in both array such that every elements on left side are
    less than or equal to the element on the right side, and the total elements on the left equals to the total number of elements on
    the right. And if the total length is odd, there should be one more element on the right.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
         /*
         We calculate the index cnt which will be the theoretical mid-point
         if we consider both arrays as a single combined array.
         We also check if the total length of the arrays is odd,
         if it is odd then a single mid-point exists, else we will have two mid-points.
         */
        int cnt = (nums1.length + nums2.length) / 2;
        boolean isOdd = ((nums1.length + nums2.length) % 2 == 1);
        if(nums1.length < nums2.length){
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        /*
        the right boundary of our search space is cnt - 1
         */
        int right = cnt - 1;
        /*
         Now comes to left boundary, the initial thought might be simple set the left boundary to 0,
         because that's how binary search usually works, right?
         However, the trick here is to realize that there're at most nums2.length numbers in nums2 array,
         so nums1 array should have at least cnt - nums2.length elements.
         */

        int left = cnt - nums2.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            int mid2 = cnt - (mid + 1) - 1;
            //for each iteration, we calculate the max elements from left partition and min element from right partition.
            int l1max = mid == -1 ? Integer.MIN_VALUE : nums1[mid];
            int l2max = mid2 == -1 ? Integer.MIN_VALUE : nums2[mid2];
            int r1min = mid + 1 == nums1.length ? Integer.MAX_VALUE : nums1[mid + 1];
            int r2min = mid2 + 1 == nums2.length ? Integer.MAX_VALUE : nums2[mid2 + 1];
            // if the partition is correct, we get our median already
            if (l1max <= r2min && l2max <= r1min){
                if (isOdd){
                    return (double) Math.min(r1min, r2min);
                }else{
                    return (double) (Math.max(l1max, l2max) + Math.min(r1min, r2min)) / 2;
                }
            }else if(l1max > r2min){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return 0;
    }
}
