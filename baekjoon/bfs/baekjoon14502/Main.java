package baekjoon14502;
import java.io.*;
import java.util.*;

public class Main{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static ArrayList<Integer> safe = new ArrayList<>();

    static void bfs(int[][] wall){
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(wall[i][j]==2){
                    queue.offer(new int[]{i,j});
                    visited[i][j]=true;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] c = queue.poll();
            for(int i=0;i<4;i++){
                int nx = c[0] +dx[i];
                int ny = c[1]+ dy[i];
                
                // ★ wall[nx][ny] == 0 체크로 visited 없이도 가능하지만, 둘 다 써도 OK
                if(nx>=0 && nx <n && ny >=0 && ny<m && visited[nx][ny] == false && wall[nx][ny] == 0){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx,ny});
                    wall[nx][ny]=2;
                }
            }
        }

        // ★ 감염 수가 아니라, BFS 끝난 후 남은 0의 개수를 세야 함
        // 0인 칸이 안전 영역이므로, BFS 끝난 후 wall 배열에서 0의 개수 세기
        int count=0;
        for(int x=0;x<n;x++){
            for(int y=0;y<m;y++){
                if(wall[x][y]==0)
                    count++;
            }
        }
        safe.add(count);
    }
    //1. 입력 첫 줄 : 연구소 크기 N, M
    //2. 0은 빈칸, 1은 벽, 2는 바이러스
    //3. 출력 : 안전 영역의 최대 크기
    //4. 벽은 3개 세울 수 있음
    //4-1. 바이러스는 상하좌우 인접한 빈 칸으로 퍼져나감
    //4-2. 벽을 세운 후 바이러스 퍼지는 막으면 됨
    //5. 벽을 세우는 경우의 수 : 먼저 0인 칸을 리스트로 모아둠
    //5-1. 빈칸 리스트에서 3개를 뽑는 조합
    //5-2. 조합으로 뽑은 3개의 칸에 벽 세우고 바이러스 퍼뜨리고 안전 영역 크기 계산
    //5-3. 반복하면서 최대 안정 영역이 되는 경우 찾기
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // ★ 빈 칸(0)을 리스트로 모아두면 3중 for문으로 조합을 깔끔하게 구성 가능
        List<int[]> blank = new ArrayList<>();
        // 1. 0 빈 칸 수집
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 0){
                    blank.add(new int[]{i,j});
                }
            }
        }

        // 2. 3개 조합 선택 → 벽 세우기 → BFS → 세기
        // ★ 핵심: 벽 3개를 전부 시도 (브루트포스), j=i+1, k=j+1로 중복 없는 조합
        for(int i=0;i<blank.size();i++){
            for(int j=i+1;j<blank.size();j++){
                for(int k=j+1;k<blank.size();k++){
                    int[][] wall = new int[n][m];
                    for(int x=0;x<n;x++){
                        for(int y=0;y<m;y++){
                            wall[x][y] = map[x][y];
                        }
                    }
                    wall[blank.get(i)[0]][blank.get(i)[1]] = 1;
                    wall[blank.get(j)[0]][blank.get(j)[1]] = 1;
                    wall[blank.get(k)[0]][blank.get(k)[1]] = 1;

                    bfs(wall);
                }
            }
        }
        Collections.sort(safe, Collections.reverseOrder());
        System.out.println(safe.get(0));
    }
}