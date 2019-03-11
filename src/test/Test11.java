package test;

/*
* Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
* n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
* Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.


*
* */
public class Test11 {
    public static void main(String[] args){
        Test11 t = new Test11();
        int[] height = {10, 9, 20, 0, 5};
        System.out.println(t.maxArea(height));
    }
    public int maxArea(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while(l <= r){
//            System.out.println("l = " + l + ", r = " + r);
            int min = Math.min(height[l], height[r]);
            int v = r - l;
            v *= min;
            if(v > max)
                max = v;
            if(height[l] == min) {
                while (l <= r && l + 1 < height.length && height[l + 1] <= height[l])
                    l++;
                if(l <= r && l + 1 < height.length)
                    l++;
            }
            if(height[r] == min) {
                while (l <= r && r - 1 >= 0 && height[r - 1] <= height[r])
                    r--;
                if(l <= r && r - 1 >= 0)
                    r--;
            }
        }
        return max;
    }
}
