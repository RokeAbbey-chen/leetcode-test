package test;

import java.util.*;

/*
* Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*
*
* */
public class Test18 {
    public static void main(String[] args){
        Test18 t = new Test18();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 20;

        nums = new int[]{1,0,-1,0,-2,2};
        //-2 -1 0 0 1 2
        //-2 -1 1 2
        target = 0;

        System.out.println(t.fourSum(nums, target));
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 3, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }


    //nums need sorted
    public List<List<Integer>> kSum(int[] nums, int target, int k , int start){
        List<List<Integer>> result = new ArrayList<>();
        if(k == 1) {
//            System.out.println("k = " + k);
            int index = biSearch(nums, target, start);
            if(index != -1 && (index >= start || nums[index] == nums[start]))
                result.add(Arrays.asList(nums[index]));
            return result;
        }
        else if(k == 2)
            return twoSum(nums, target, start);

        List<List<Integer>> temp = null;//new ArrayList<>();
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i - 1]) continue;
            temp = kSum(nums, target - nums[i], k - 1, i + 1);
//            System.out.println("nums[" + i + "] = " + nums[i] + ", temp = " + temp + ", k = " + k);
            int size = temp.size();
            if(size > 0){
                for(int j = 0; j < size; j++) {
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    result.add(l);
                    result.get(result.size() - 1).addAll(temp.get(j));
                }
            }
        }
        return  result;

    }

    public int biSearch(int[] nums, int value, int left){
        int right = nums.length - 1;
        int mid;
        while(left <= right){
            mid = left + right >> 1;
            if(value < nums[mid]) right = mid - 1;
            else if(value > nums[mid]) left = mid + 1;
            else return mid;
        }
        return -1;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int left){
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        while(left < right){
            int realValue = nums[left] + nums[right];
            boolean lflag = true;
            boolean rflag = true;
            if(realValue == target)
                result.add(Arrays.asList(nums[left], nums[right]));
            if(realValue > target){
                rflag = true;
                lflag = false;
            }else if(realValue < target){
                rflag = false;
                lflag = true;
            }

            if(lflag){
                left ++;
                while(left < right && nums[left - 1] == nums[left]) left++;
            }

            if(rflag){
                right --;
                while(left < right && nums[right + 1] == nums[right]) right--;
            }
        }
        return result;
    }
}
