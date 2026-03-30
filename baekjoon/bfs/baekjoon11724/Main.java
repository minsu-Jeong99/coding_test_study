package baekjoon11724;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, count=0;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    //1. 입력 첫 줄 : 정점 개수 n, 간선 개수 m
    //2. 다음 m 줄 : 간선 정보 - 양방향
    //3. 출력 : 연결 요소 개수 출력

    static void bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        visited[node] = true;
        queue.offer(node);

        while(!queue.isEmpty()){
            int c = queue.poll();

            for(int n : graph.get(c)){
                if(!visited[n]){
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[n+1];

        // ArrayList 인덱스는 0부터 시작하므로
        // i=0부터 돌려 n+1개를 생성해야 graph.get(n)까지 접근 가능
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                bfs(i);
                count++;
            }
        }

        System.out.print(count);
    }

}