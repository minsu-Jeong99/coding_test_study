package baekjoon.dfs.baekjoon1520;
import java.util.*;
import java.io.*;

public class Main{
    static int m,n,count=0;
    static int[][] map;
    // dp[x][y] = (x,y)에서 도착지까지의 경로 수 (-1: 미방문)
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    //1. (0,0)에서 시작해서 제일 오른쪽아래 칸으로 이동하는 경우의 수
    //2. 각 인덱스 별로 숫자가 낮은 지점으로 이동함
    //3. 상하좌우 1칸씩 이동
    //4. 2차원 배열 맵
    static int dfs(int x, int y) {
        // 도착지 도달 시 경로 1개 완성
        if(x==m-1 && y==n-1){
            return 1;
        }

        // 이미 계산한 칸이면 저장된 값 바로 반환 (메모이제이션)
        if(dp[x][y] != -1)
            return dp[x][y];
        
        dp[x][y]=0;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내 && 현재 칸보다 낮은 칸이면 탐색 후 경로 수 합산
            if(nx>=0 && nx<m && ny>=0 && ny<n && map[nx][ny]<map[x][y]){
                dp[x][y]+=dfs(nx,ny);
            }
        }

        return dp[x][y];
        
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m=Integer.parseInt(st.nextToken());
        n=Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열 -1로 초기화 (미방문 표시)
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
                dp[i][j]=-1;
        }

        // (0,0)에서 탐색 시작 후 경로 수 출력
        System.out.println(dfs(0,0));
    }
}