package baekjoon1260;

import java.util.*;
import java.io.*;

public class Main {
    //1. 방문할 수 있는 정점이 여러 개일경우 , 번호가 작은 것을 먼저 방문
    //2. 더 이상 방문할 수 없으면 종료
    //3. 정점 번호는 1번부터 N번까지, 양방향 코드
    //4. 정점 개수, 간선 개수, 시작 정점 번호로 입력 받음
    //5. 출력값 - dfs 방문한 정점 순서, bfs 방문한 정점 순서 순으로 출력
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    // static int[] dist;
    static int N, M;

    static void dfs(int node){
        visited[node] = true;
        System.out.print(node + " ");

        for(int next : graph.get(node)){
            if(!visited[next]){
                dfs(next);
            }
        }
    }

    static void bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        visited[node] = true;
        queue.offer(node);

        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current + " ");

            for(int next : graph.get(current)){
                // if(dist[next] == -1){
                //     dist[next] = dist[current] + 1;
                //     queue.offer(next);
                // }
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // Scanner sc = new Scanner(System.in);

        // N = sc.nextInt();
        // M = sc.nextInt();
        // int V = sc.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            // int a = sc.nextInt();
            // int b = sc.nextInt();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // ✅ 정렬 추가
        for(int i=0; i<=N; i++){
            Collections.sort(graph.get(i));
        }

        visited = new boolean[N+1];
        dfs(V);
        System.out.println();

        visited = new boolean[N+1]; // ✅ BFS 전 visited 초기화
        // dist = new int[N+1];
        // Arrays.fill(dist, -1);
        bfs(V);

    }
}