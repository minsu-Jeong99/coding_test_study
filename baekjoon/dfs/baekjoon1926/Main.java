package baekjoon.dfs.baekjoon1926;
import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int n, m, count;
    //각 구역의 그림 넓이 저장
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    //1. 입력1 : 도화지 크기 n, m , 입력2 : 도화지에 그려진 그림 유무 1 - 색칠됨 0 - 색칠x
    //2. 출력1 : 그림의 개수 - 색칠된 구역의 개수, 출력2 : 가장 넓은 그림의 넓이 - 색칠된 구역이 제일 큰 구역의 넓이
    //2-1. 그림이 없을경우 가장 큰 넓이는 0
    //3. 2차원 배열
    static void dfs(int x, int y){
        visited[x][y]=true;
        count++;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<n && ny>=0 && ny<m &&!visited[nx][ny] && map[nx][ny]==1){
                dfs(nx,ny);
            }
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && map[i][j]==1){
                    // 새 그림마다 넓이 초기화
                    count=0;
                    dfs(i,j);
                    // 탐색 완료된 그림 넓이 저장
                    list.add(count);
                }
            }
        }

        Collections.sort(list, Collections.reverseOrder());

        System.out.println(list.size());
        if(list.size()==0){
            System.out.println("0");
        }
        else{
            System.out.println(list.get(0));
        }
    }
}
