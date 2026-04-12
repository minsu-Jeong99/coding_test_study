package baekjoon.dfs.baekjoon4963;

import java.io.*;
import java.util.*;

public class Main {
    static int w, h, count=0;
    static int[][] map;
    static boolean[][] visited;
    //대각선의 경우도 계산해야 하므로 8방향으로 이동하게끔 초기화
    static int[] dx = {0,0,1,-1,-1,-1,1,1};
    static int[] dy = {1,-1,0,0,-1,1,-1,1};
    //1. 2차원 배열을 사용해서 지도를 표시
    //2. 1은 땅, 0은 바다
    //3. 입력의 마지막은 0, 0
    //4. 한 정사각형과 가로, 세로, 대각선으로 연결되면 하나의 섬으로 취급
    //5. 각 테스트 케이스마다 섬의 개수 출력
    static void dfs(int x, int y){
        visited[x][y]=true;
    
        for(int i=0;i<8;i++){
            int nx = x +dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<h && ny>=0 && ny<w && map[nx][ny]==1 && !visited[nx][ny]){
                dfs(nx,ny);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        //BufferdReader는 while 안에 있으면 안됨
        //while 루프 안에서 매번 새로 만들고 있어서
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0) break;
            map = new int[h][w];
            visited = new boolean[h][w];

            for(int i=0;i<h;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<w;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    if(map[i][j]==1 && !visited[i][j]){
                        dfs(i,j);
                        count++;
                    }
                }
            }

            System.out.println(count);
            count=0;
        }
    }
}