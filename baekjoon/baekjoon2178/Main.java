package baekjoon2178;

import java.io.*;
import java.util.*;

public class Main {
    // 상하좌우 4방향 이동을 위한 방향 배열
    // dx, dy를 같은 인덱스로 묶어서 사용 ex) i=0 이면 (0,-1) → 왼쪽
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M;
    static int[][] maze;
    static int[][] dist;

    static void bfs(int x , int y){
        // ✅ x,y 두 좌표를 동시에 큐에 넣기 위해 int[] 배열로 묶음
        // Queue<Integer>는 값 하나만 저장 가능하므로 2차원 좌표는 배열로 묶어야 함
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        // ✅ 시작점은 1로 초기화 (문제에서 시작칸도 포함해서 세기 때문)
        // dist 배열 초기값은 0이므로 0 = 미방문, 1이상 = 방문으로 구분 가능
        dist[x][y] = 1;

        while(!queue.isEmpty()){
            // ✅ 큐에서 꺼낸 배열에서 x,y 좌표 분리
            int[] c = queue.poll();
            int cx = c[0];
            int cy = c[1];

            for(int i=0;i<4;i++){
                // ✅ 현재 위치에서 4방향으로 이동한 다음 좌표
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                //배열 범위 체크 (위아래), (좌우) + 이동 가능한 칸인지 체크 + 방문 여부 체크
                if(nx>=0 && nx<N && ny>=0 && ny<M && maze[nx][ny] == 1 && dist[nx][ny] == 0){
                    // ✅ 이전 칸의 거리 + 1 → 최단거리 보장
                    // BFS는 가까운 곳부터 탐색하므로 처음 방문했을 때가 항상 최단거리
                    dist[nx][ny] = dist[cx][cy]+1;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //1. 입력받은 N M으로 미로 크기 설정
        //2. (0,0)에서 (N,M)까지 이동하는데 최소한의 칸수를 구하기 - bfs
        //3. 이동되는 칸수는 1칸, 1로 입력된 부분만 이동가능
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // ✅ 공백으로 구분된 입력값 분리를 위해 StringTokenizer 사용
        // br.readLine()으로 한 줄을 통째로 읽고 → StringTokenizer로 공백 기준 분리
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        dist = new int[N][M];

        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
                // ✅ charAt(j)로 문자 하나씩 읽고 - '0'으로 숫자로 변환
                // "1011"은 공백 없이 붙어있어서 parseInt 불가
                // '1' - '0' = 49 - 48 = 1 (ASCII 코드 차이 이용)
                maze[i][j] = line.charAt(j)- '0';
            }
        }

        bfs(0,0);
        System.out.println(dist[N-1][M-1]);
    }
}
