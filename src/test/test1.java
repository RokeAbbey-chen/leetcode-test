package test;

import java.util.TreeMap;
import java.util.TreeSet;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 */
public class test1 {
    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < nums.length ; i++)
            map.put(nums[i], i);
        for(int i = 0; i < nums.length; i++){
            result[0] = i;
            if(target - nums[i] != nums[i] || map.get(nums[i]) != i)
                for(int j = i + 1; j < nums.length; j++ )
                    if(nums[i] + nums[j] == target){
                        result[1] = j;
                        return result;
                    }
        }
        return result;
    }
}
