package baekjoon2667;

import java.io.*;
import java.util.*;

public class Main {
    static int N, count=1; //count=1로 시작점 본인 포함
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[][] visited;
    static ArrayList<Integer> result = new ArrayList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        // 시작점 큐에 삽입 
        queue.offer(new int[]{x,y});

        while(!queue.isEmpty()){
            int[] c= queue.poll();
            int cx = c[0];
            int cy = c[1];

            for(int i=0;i<4;i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                // 범위 체크 + 집이 있는 곳(1) + 아직 방문 안한 곳만 탐색
                if(nx>=0 && nx<N && ny>=0 && ny<N && graph.get(nx).get(ny)==1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    count++; //같은 단지는 개수 증가
                }
            }
        }
        result.add(count); // BFS 한 번 = 단지 하나 완전 탐색 → 집 수 저장
        count=1; // 다음 단지를 위해 count 초기화 (시작점 포함이므로 1로 리셋)
    }

    public static void main(String[] args) throws IOException {
        //1. 입력 첫줄 : 정사각형 지도 크기 N, 둘째줄 : 지도 입력값
        //2. 지도 1은 집이 있음, 0은 집이 없음 
        //3. 각각의 단지별로 집의 개수를 출력
        //3-1. 출력 첫줄 : 총 단지 수, 둘째줄 : 단지별 집 수
        //4. 단지별로 출력을 오름차순으로 출력
        //5. count=1로 시작 : 시작점도 단지에 포함되므로 1로 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        visited = new boolean[N][N];

        for(int i=0;i<N;i++){
            //공백이 없으므로 Stringtokenizer 없이 charAt으로 한 글자씩 읽어서 파싱
            // 입력이 "0110100" 처럼 공백 없이 붙어있으므로
            // charAt(j)으로 한 글자씩 읽은 뒤 parseInt로 변환
            // StringTokenizer st = new StringTokenizer(br.readLine());
            String line = br.readLine();
            graph.add(new ArrayList<>());
            for(int j=0;j<N;j++){
                graph.get(i).add(Integer.parseInt(String.valueOf(line.charAt(j))));
                visited[i][j] = false;
            }
        }

        //2중 for문 핵심: 전체 격자 순회하며 미방문 집(1)을 만날 때마다 새 단지 BFS 시작
        // BFS가 끝나면 해당 단지는 모두 visited=true → 자연스럽게 건너뜀
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(graph.get(i).get(j)==1 && !visited[i][j])
                    bfs(i,j);
            }
        }
        Collections.sort(result);

        System.out.println(result.size());
        for(int i : result){
            System.out.println(i);
        }

    }
}
