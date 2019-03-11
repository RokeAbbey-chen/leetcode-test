package test;

/*
* There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*
* */
public class Test4 {
    public static void main(String[] args){
        Test4 test4 = new Test4();
        int[] nums1 = {1, 5, 10, 20, 30, 40, 50}; // 7
        int[] nums2 = {2, 3, 8, 15, 18, 31, 35, 39}; // 8

//        nums1 = new int[]{1, 5, 10, 20, 30, 40, 50, 51}; // 8
//        nums2 = new int[]{2, 3, 8, 15, 18, 31, 35, 39}; // 8

//        nums1 = new int[]{1, 3};
//        nums2 = new int[]{2};

//        nums1 = new int[0];
//        nums2 = new int[]{1,2};
        System.out.println(test4.findMedianSortedArrays(nums1, nums2));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int totalSize = nums1.length + nums2.length;
        int i = 0, i1 = 0, i2 = 0;
        int middleNum = 0;
        int middleNum2 = 0;
        while(true){
            middleNum2 = middleNum;
            if(i1 < nums1.length && ( i2 >= nums2.length || nums1[i1] <= nums2[i2])) {
                middleNum = nums1[i1++];
            }
            else if(i2 < nums2.length && (i1 >= nums1.length || nums2[i2] <= nums1[i1])) {
                middleNum = nums2[i2++];
            }
            i++;
            if(i > totalSize >> 1){
                if(i == (totalSize >> 1) + 1 && (totalSize & 1) == 1)
                    return middleNum;
                else if(i == (totalSize >> 1) + 1 && (totalSize & 1) == 1)
                    continue;
                else
                    return (middleNum + middleNum2) / 2.0;
            }
        }
    }
}
