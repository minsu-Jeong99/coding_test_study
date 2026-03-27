package baekjoon1012;

import java.util.*;
import java.io.*;

public class Main {
    static int t, m , n, k;
    static int[][] dist;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int count=0;
    static ArrayList<Integer> result = new ArrayList<>();
    // 배추밭 지도를 2차원 배열로 표현 (ArrayList<int[]>는 좌표 목록일 뿐, 지도가 아님!)
    static int[][] graph;

    static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] c = queue.poll();
            int cx = c[0];
            int cy = c[1];

            for(int i=0;i<4;i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                // 범위 체크: >= 0, < m, < n (0번 인덱스를 제외하면 안됨!)
                if(nx>=0 && nx <m && ny>=0 && ny<n && !visited[nx][ny] && graph[nx][ny]==1){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx,ny});
                }
            }
            // 배추 문제는 그룹 수만 구하면 되므로 BFS 내부에서 count 불필요
        // (단지번호붙이기 문제처럼 그룹 내 개수까지 구해야 할 때만 BFS 내부에서 count 사용)
            // count++
        }
    }
    public static void main(String[] args) throws IOException{
        //1. 2차원 배열을 사용해 배추밭 표현
        //2. 또 다른 배열을 이용해서 배추밭 그룹별 개수 저장
        //2-1. 그룹별 배추를 전부 방문했으면 count ++ -> // BFS 호출 횟수 = 그룹 수 = 지렁이 수
        //3. 방문 체크 배열 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int i=0;i<t;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            graph = new int[m][n];
            visited = new boolean[m][n];

            for(int j=0;j<k;j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = 1;
            }

            for(int x=0;x<m;x++){
                for(int y=0;y<n;y++){
                    if(graph[x][y] ==1 && !visited[x][y]){
                        bfs(x,y);
                        // BFS 호출 횟수 = 그룹 수 = 지렁이 수
                        count++;
                    }
                        
                }
            }
            result.add(count);
            count=0;
        }

        for(int i : result)
            System.out.println(i);
    }
}
