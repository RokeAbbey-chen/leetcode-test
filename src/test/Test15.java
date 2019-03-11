package test;

import java.util.*;

/*
* Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
* Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
* */
public class Test15 {

    public static void main(String[] args){
        Test15 t = new Test15();
        int[] nums = {-1,0,1,2,-1,-4};
        // -4, -1, -1, 0, 1, 2
        nums = new int[]{0, 0, 0};
        nums = new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        System.out.println(t.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums){

        ArrayList<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length ;i ++){
            if(i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right ){
                int target = -nums[i];
                int realSum = nums[left] + nums[right];
                boolean lflag = realSum < target;
                boolean rflag = realSum > target;
                if(realSum == target){
                    lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    lflag = rflag = true;
                }

                if(lflag){
                    left ++;
                    while(left < right && nums[left] == nums[left - 1])
                        left ++;

                }
                else if(rflag){
                    right --;
                    while(left < right && nums[right] == nums[right + 1])
                        right --;
                }
            }
        }

        return  lists;
    }

}
