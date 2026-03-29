package baekjoon2606;

import java.io.*;
import java.util.*;

public class Main {
    //1. 바이러스 감염시 간선으로 연결된 네트워크는 전부 감염
    //2. 출력이 1번 컴퓨터에서 시작하여 바이러스에 감염된 수를 출력
    //2-1. 1번 컴퓨터에서 시작하므로 1번 컴퓨터는 포함하지 않는다
    //3. 입력 첫줄 : 총 컴퓨터 수, 둘째줄 : 간선의 수
    static int N, M, count=0;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    static void bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        //시작 노드 방문 처리
        visited[node] = true;
        //시작 노드 큐에 넣기
        queue.offer(node);

        //큐가 빈 값일때까지 반복
        while(!queue.isEmpty()){
            //큐에서 하나 꺼내서 저장
            int c = queue.poll();
            count++;

            //꺼낸 노드의 이웃을 전부 확인
            //방문하지 않은 노드민 다시 true 처리 후, 큐에 넣어 다음에 탐색
            for(int next : graph.get(c)){
                if(!visited[next]){
                    //큐에 넣을 때 방문 처리(중복 방지 )
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력이 한 줄에 하나씩이므로 StringTokenizer 없이 바로 파싱
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        // 간선 입력: 한 줄에 두 값이므로 StringTokenizer로 쪼개기
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs(1);

        System.out.println(count-1);
    }
}
