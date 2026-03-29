package baekjoon1697;

import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] dx = {1, -1, 2};
    // 각 위치까지 도달하는 최소 시간 저장
    static int[] graph;
    static boolean[] visited = new boolean[100001];
    //1. 수빈이는 걷거나 순간이동이 가능
    //1-1. 걷는 경우 : x + 1, x - 1
    //1-2. 순간이동 : x * 2
    //2. 수빈이가 동생을 찾는 가장 빠른 시간 출력
    //3. 1차원 그래프 사용
    //4. 입력 : 수빈이 위치  동생 위치

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        graph[start] = 0;
        visited[start] = true;

        while(!queue.isEmpty()){
            int x = queue.poll();

            // 동생 위치에 도달하면 시간 출력 후 종료
            if(x == k){
                System.out.println(graph[x]);
                break;
            }

            for(int i=0;i<3;i++){
                int nx = 0;

                if(dx[i] == 2){
                    nx = x * dx[i];
                }
                else
                    nx = x + dx[i];

                if(nx>=0 && nx<=100000 && !visited[nx]){
                    visited[nx] =true;
                    // 현재 시간 + 1초
                    graph[nx] = graph[x] + 1;
                    queue.offer(nx);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new int[100001];
        bfs(n);
    }
}
