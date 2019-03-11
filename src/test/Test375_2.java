package test;

public class Test375_2 extends Test375{
    @Override
    public int getMoneyAmount(int n) {
        int[][] money = new int[n+1][n+1];//全部为0
        for(int i = n - 1;i>=1;i--){//为什么是从n-1开始呢？因为在递推关系式中用到了公式下面一行的数据，所以只能从n-1开始
            for(int j = i + 1;j<=n;j++){//为什么从左开始呢？同样的道理
                int min = Integer.MAX_VALUE;
                for(int k = i;k<=j;k++){
                    int tmp = k;
                    if(i < k - 1 && k + 1 < j) {
                        tmp = k + Math.max(money[i][k - 1], money[k + 1][j]);
                    }
                    else if(i < k - 1) {
                        tmp = k + money[i][k - 1];
                    }
                    else if(k + 1 <j) {
                        tmp = k + money[k + 1][j];
                    }
                    if (min >= tmp){
                        System.out.println("k = " + k);
                    }
                    min = Math.min(tmp,min);
                }
                money[i][j] = min;
            }
        }
        return money[1][n];
    }

}
