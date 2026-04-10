package baekjoon.dfs.baekjoon2468;

import java.util.*;
import java.io.*;

public class Main {
    static void dfs(int h, int x, int y){
        visited[x][y]=true;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<n && ny>=0 && ny<n && map[nx][ny]>h && !visited[nx][ny]){
                dfs(h, nx, ny);
            }
        }
    }
    //1. 2차원 배열의 크기를 입력받은 정사각형 지역정보 초기화
    //2. 높이를 차례대로 지정하여 물에 잠기지 않는 구역의 개수를 저장
    //3. 안전 구역의 개수가 최대일 경우 최댓값 갱신 - 출력
    static int n,max=1,count=0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static ArrayList<Integer> safe = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        // for(int i=1;i<=max;i++){
        //비가 안오는경우의 수도 추가
        // ★ i=0부터: 비가 안 오는 경우도 포함 (노트: "아무 지역도 물에 잠기지 않을 수도 있다")
        // ★ i<max: i==max이면 전부 잠겨서 구역 0개이므로 불필요
        for(int i=0;i<max;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(map[j][k]>i && !visited[j][k]){
                        dfs(i,j,k);
                        // ★ DFS 한 번 완료 = 연결된 안전 구역 1개 완성
                        count++;
                    }
                }
            }
            safe.add(count);
            //높이마다 count, visited 초기화
            count=0;
            visited = new boolean[n][n];
        }

        Collections.sort(safe, Collections.reverseOrder());
        System.out.println(safe.get(0));
            
    }
}
