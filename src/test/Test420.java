package test;

public class Test420 {
    public int strongPasswordChecker(String password) {
        char[] pwd = password.toCharArray();
        boolean[] conditions = new boolean[3];
        int[] repWhere = new int[pwd.length];
        int repLen = 0;
        int num0 = 3;
        for (int i = 0; i < pwd.length; i ++) {
            if (!conditions[0] && pwd[i] >= 'a' && pwd[i] <= 'z') {
                conditions[0] = true; num0 --;
            }
            if (!conditions[1] && pwd[i] >= 'A' && pwd[i] <= 'Z') {
                conditions[1] = true; num0 --;
            }
            if (!conditions[2] && pwd[i] >= '0' && pwd[i] <= '9') {
                conditions[2] = true; num0 --;
            }
        }

        char c0 = 0, c1 = 0, c2;
        for (int i = 0; i < pwd.length; i ++) {
            c2 = pwd[i];
            if (c2 == c1 && c1 == c0) {
                repWhere[repLen ++] = i;
                c2 = 0;
            }
            c0 = c1; c1 = c2;
        }

        if (pwd.length <= 5){
            return Math.max(6 - pwd.length, num0);
        }

        if (pwd.length <= 20) {
            return Math.max(repLen, num0);
        }

        int dTimes = delete(pwd, repWhere, 0, repLen, pwd.length - 20, num0);
        return dTimes + Math.max(repLen, num0);
    }

    public int delete(char[] pwd, int[] repWhere, int start, int repLen, int restTimes, int num0) {
        int minTimes = restTimes;
        for (int i = start; i < repLen; i ++) {
            int j, j2 = i, repTimes = 0;
            for (j = repWhere[i]; j < pwd.length && pwd[j] == pwd[repWhere[i]]; j ++) {
                if (j2 < repLen && repWhere[j2] <= j) {
                    repTimes++;j2 ++;
                }
            }

            int useTimes = j - repWhere[i];
            if (useTimes <= restTimes) {
                int restNum0 = Math.max(num0 - (i - start), 0);
                if (restNum0 <= repLen - 1 - i)
                    minTimes = Math.min(minTimes, useTimes - repTimes + delete(pwd, repWhere, i + repTimes,
                            repLen, restTimes - useTimes, restNum0));
            }
        }
        return minTimes;
    }

    public static void main(String[] args) {
        Test420 t = new Test420();
        String pwd;
        pwd = "..A..a..1..1..1..1..D";
        pwd = "...!!!...!!!...!!!..!";
        pwd = "...";
        pwd = "aaAabb1bccccddeeddeeddeedd";
        pwd = "aaaabbbbccccddeeddeeddeedd";
        pwd = "aaAabb1bccccddeeddeeddeedd";
//        pwd = "aaaabbbbccccddeedd";
        pwd = "ABABABABABABABABABAB1";
//        pwd = "abababababababababaaa";
        System.out.println("len:" + pwd.length());
        int result = t.strongPasswordChecker(pwd);
        System.out.println(result);
    }

}

