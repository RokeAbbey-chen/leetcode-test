package test;

public class Test517_4 {
        public int findMinMoves(int[] machines) {
            int sum=0;
            for(int i=0;i<machines.length;i++)
                sum+=machines[i];

            if(sum%machines.length!=0)
                return -1;

            int avg=sum/machines.length;
            int answer=0;
            int rollingSum=0;
            for(int i=0;i<machines.length;i++){
                int x= machines[i]-avg;
                answer= Math.max(answer,x);
                rollingSum+=x;
                answer= Math.max(answer,Math.abs(rollingSum));
            }

            return answer;
        }
}
