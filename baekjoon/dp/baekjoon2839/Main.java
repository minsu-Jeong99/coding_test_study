package baekjoon.dp.baekjoon2839;
import java.util.*;
import java.io.*;

public class Main {
    //1. 3키로 5키로 봉지가 있음
    //2. n 킬로그램 배달시 봉지를 최소한으로 가져가는 방법
    //2-1. 18키로 경우 = 3키로 6봉지, 3키로 1봉지 5키로 3봉지 총 4봉지 -> 4봉지 출력
    //3. 3kg, 5kg 봉지로 n kg을 담을 때 최소 봉지 수 구하기
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        // 불가능한 경우를 5001로 초기화
        for(int i=0;i<=n;i++){
            dp[i]=5001;
        }
        dp[0]=0;

        for(int i=1;i<=n;i++){
            // 3kg 봉지 선택 가능하고, i-3 상태가 가능할 때
            if(i>=3 && dp[i-3]!=5001)
                dp[i]=Math.min(dp[i],dp[i-3]+1);
            // 5kg 봉지 선택 가능하고, i-5 상태가 가능할 때
            if(i>=5 && dp[i-5]!=5001)
                dp[i]=Math.min(dp[i],dp[i-5]+1);
        }

        // dp[n]이 5001이면 불가능(-1), 아니면 최소 봉지 수 출력
        System.out.println(dp[n]==5001?-1:dp[n]);
    }
}