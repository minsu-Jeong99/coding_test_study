package baekjoon.dfs.baekjoon11725;

import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] parents;
    //1. 트리의 루트는 무조건 1로 시작
    //2. 입력 둘째줄부터 각 노드별로 연결된 간선을 입력 받음
    //3. 2번 노드부터 시작해서 각 노드별로 부모 노드를 출력
    static void dfs(int node, int parent){
        //해당 node의 모든 이웃 노드를 확인
        for(int next : graph.get(node)){
            //visited 역할 - 이미 방문한 노드의 경우 부모 노드가 이미 정해져 있으므로, 부모 노드가 정해지지 않은 노드만 탐색
            if(next!=parent){
                //next의 노드 부모는 node
                parents[next] = node;
                dfs(next, node);
            }
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parents = new int[n+1];

        graph = new ArrayList<>();

        for(int i=0;i<=n;i++)
            graph.add(new ArrayList<>());

        for(int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, 0);

        for(int i=2;i<=n;i++)
            System.out.println(parents[i]);
    }
}
