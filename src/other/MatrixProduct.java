package other;

import java.util.Stack;

public class MatrixProduct {
    public int matrixProduct(int[] matrix){//代表矩阵的行数, 最后一个位置是最后一个矩阵的列数
        if (matrix == null || matrix.length <= 3){ return 0; }
        Stack<Integer> stack = new Stack<>();
        stack.push(matrix[0]);
        stack.push(matrix[1]);
        for (int i = 2; i < matrix.length; i ++){
            int mid = stack.pop();
            int left = stack.peek();
//            if ()
        }
        return 0;
    }
}
