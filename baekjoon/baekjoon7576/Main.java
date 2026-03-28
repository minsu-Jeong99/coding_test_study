package baekjoon7576;
import java.io.*;
import java.util.*;

public class Main {
    static int m, n, count=0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] graph;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();

    //1. 격자 모양 토마토 상자 m*n
    //2. 익은 토마토 1, 익지 않은 토마토 0, 토마토 없는 칸 -1
    //3. 토마토가 모두 익을 때가지 최소 날짜
    //3-1. 토마토가 모두 익지 못하는 상황은 -1

    static void bfs(){
        // 멀티소스 BFS: 익은 토마토가 여러 개면 동시에 퍼져야 하므로
        // main에서 미리 모든 시작점을 큐에 넣고 bfs 호출

        // Queue<int[]> queue = new LinkedList<>();
        // visited[x][y] = true;
        // queue.offer(new int[]{x,y});

        while(!queue.isEmpty()){
            // 오늘 처리할 토마토 수 = 하루치 한번에 묶음
            int size = queue.size(); 
            for(int s=0;s<size;s++){
                int[] c = queue.poll();
                int cx = c[0];
                int cy = c[1];

                for(int i=0;i<4;i++){
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if(nx>=0 && nx<n && ny>=0 && ny<m && !visited[nx][ny] && graph[nx][ny] ==0){
                        visited[nx][ny] = true;
                        // graph.get(nx).get(ny).set(1);
                        graph[nx][ny] = 1;
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
            count++;
        }
        count--; // 마지막 날은 퍼뜨린 게 없으므로 -1
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m];

        // 입력 + 익은 토마토 큐에 삽입
        for(int i=0;i<n;i++){
            // 한 행씩 읽기 (칸마다 호출하면 오류)
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] ==1){
                    visited[i][j] = true;
                    // 시작점 모두 큐에 추가
                    queue.offer(new int[]{i, j});
                }
            }
        }

        bfs();

        // for(int i=0;i<m;i++){
        //     for(int j=0;j<n;j++){
        //         if(graph.get(i).get(j) == 1 && !visited[i][j]){
        //             bfs(i,j);
        //         }
        //     }
        // }

        // bfs 후 0이 남아있으면 익지 못한 토마토 존재 → -1 출력
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(graph[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(count);
    }
}
