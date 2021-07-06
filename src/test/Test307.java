package test;

public class Test307 {
    public static class NumArray {
        public SegTree tree = null;

        static class SegTree {
            private int[] nodes;
            private int[] oldNums;

            SegTree(int[] nums) {
                nodes = new int[getSuitableLength(nums.length)];
                oldNums = nums;
                buildTree(nums, 0, nums.length - 1, 0);
            }

            private int getSuitableLength(int numsLength) {
                for (; ((numsLength - 1) & numsLength) > 0; numsLength = (numsLength - 1) & numsLength) ;
                return (numsLength << 2) + 5;
            }

            private void buildTree(int[] nums, int start, int end, int root) {
                if (start == end) {
                    nodes[root] = nums[start];
                    return;
                }
                int mid = (start + end) >> 1;
                int leftChild = (root << 1) + 1;
                int rightChild = (root << 1) + 2;
                buildTree(nums, start, mid, leftChild);
                buildTree(nums, mid + 1, end, rightChild);
                nodes[root] = nodes[leftChild] + nodes[rightChild];
            }

            void update(int index, int val) {
                int oldValue = oldNums[index];
                oldNums[index] = val;
                int diff = val - oldValue;
                int nodeIndex = 0;
                nodes[nodeIndex] += diff;
                for (int left = 0, right = oldNums.length - 1, mid = (left + right) >> 1;
                     left < right; ) {
                    nodeIndex = (nodeIndex << 1) + 1;
                    if (index > mid) {
                        nodeIndex++;
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                    nodes[nodeIndex] += diff;
                    mid = (left + right) >> 1;
                }
                print();
            }

            /***
             * 求取(-1, index]范围内的值的和, leftest是最左边的意思
             * @param index
             * @return
             */
            public int sumFromLeftest(int index) {
                if (index <= -1) { return 0; }
                int sum = 0;
                int nodeIndex = 0;
                for (int left = 0, right = oldNums.length - 1, mid = (left + right) >> 1;
                        ; mid = (left + right) >> 1) {
                    if (left == right) { sum += nodes[nodeIndex]; break; }
                    if (index > mid) {
                        nodeIndex = (nodeIndex << 1) + 2;
                        sum += nodes[nodeIndex - 1];
                        left = mid + 1;
                    } else {
                        nodeIndex = (nodeIndex << 1) + 1;
                        right = mid;
                    }
                }
                return sum;
            }

            public int sumRange(int left, int right) {
                int rightSum = sumFromLeftest(right);
                int leftSum = sumFromLeftest(left - 1);
                return rightSum - leftSum;
            }

            void print() {
                for (int i = 0; i < nodes.length; i++) {
                    System.out.println("i: " + i + ", nodes[i] = " + nodes[i]);
                }
            }
        }

        public NumArray(int[] nums) {
            tree = new SegTree(nums);
        }

        public void update(int index, int val) {
            tree.update(index, val);
        }

        public int sumRange(int left, int right) {
            return tree.sumRange(left, right);
        }
    }



    public static void main(String[] args) {
        int[] nums = {7, 2, 7, 2, 0};
        NumArray arr = new NumArray(nums);
        arr.tree.print();
        System.out.println("----------------------");
        arr.update(4, 6);
        int result = arr.sumRange(3, 9);
        System.out.println(result);
    }
}
