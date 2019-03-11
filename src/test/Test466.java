package test;

import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
*
*
Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".

On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.

You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.

Example:

Input:
s1="acb", n1=4
s2="ab", n2=2

Return:
2


超时*
* */
public class Test466 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len1 = ch1.length * n1;
        int len2 = ch2.length ;
//        Map<Integer, Integer> lastIndex = new HashMap<>();
        int[][] lastIndex = new int[ch1.length][];
        for (int i = 0; i < lastIndex.length; i ++){
            lastIndex[i] = new int[]{-1, -1};
        }
        int T = 0; //变化周期
        int Tsize = 0; //进入变化周期前的多余数据数量
        int result = 0;
        int i , j;
        for (i = 0, j = 0; i < len1; i ++){
            if (ch1[i % ch1.length] == ch2[j % ch2.length]){
                if(j % len2 == len2 - 1){
                    int v = lastIndex[i % ch1.length][0];
                    if (v != -1){
                        T = i - v;
                        Tsize = result - lastIndex[i % ch1.length][1];
                        while (i + T < len1){
                            result += Tsize;
                            i += T;
                        }
                        break;
                    }
                    else {
                        lastIndex[i % ch1.length][0] = i;
                        lastIndex[i % ch1.length][1] = result;
                        result ++;
                    }
                }
                j ++;
            }
        }
        if (j < len2 - 1){ return result; }

        for (;i < len1; i ++){
            if (ch1[i % ch1.length] == ch2[j % ch2.length]){
                if (j % len2 == len2 - 1){
                    result ++;
                }
                j ++;
            }
        }
        return result / n2;
    }

    public static void main(String[] args) {
        Test466 t = new Test466();
//        System.out.println(t.getMaxRepetitions("acb", 4, "ab", 2));
        System.out.println(t.getMaxRepetitions("lovelive", 1, "lovelive", 10));
//        System.out.println(t.getMaxRepetitions("acb", 1, "acb", 1));
//        SocketChannel sc = null;
//        sc.bind(null);
//        Socket s = new Socket();
//        s.bind();
    }
}
