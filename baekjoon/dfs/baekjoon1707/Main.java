package baekjoon.dfs.baekjoon1707;
import java.util.*;
import java.io.*;

public class Main{
    static int k,v,e;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    // static boolean[] visited;
    //이중 그래프는 visited만으로 구분 힘듬 - 0(미방문),1(구역1),-1(구역2)로 구분하여 그래프 구분
    static int[] color;
    // check: 이분 그래프 여부
    static boolean check;
    //1. 이분 그래프 : 그래프의 정점 집합을 두개의 서로 다른 그룹으로 나눌때, 같은 내룹 내의 정점끼리는 간선 연결 x
    //2. 입력1 : 테스트 케이스 수k , 입력2 : 각 테스트 케이스의 정점 수v 간선 수e , 입력3 : e개의 ㅜㄹ의 걸쳐 각 줄의 인접한 두 정점 번호 
    //2-1. 각 정점에는 1부터 v까지 번호
    //3. 각 그래프 k개의 줄에 걸친 입력으로 이분 그래프이면 yes, 아니면 no
    static void dfs(int node, int c){
        color[node]=c;

        for(int i=0;i<graph.get(node).size();i++){
            int n = graph.get(node).get(i);
            if(color[n]==0){
                // 미방문 이웃 → 반대 색으로 재귀
                dfs(n,-c);
            }
            else if(color[n]==c){
                // ★ 이미 방문한 이웃이 같은 색 → 이분 그래프 X
                check=false;
                return;   
            }
        }
    }    
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        for(int i=0;i<k;i++){
            // 테스트케이스마다 초기화
            check=true;
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            color = new int[v+1];

            for(int j=0;j<=v;j++){
                graph.add(new ArrayList<>());
            }

            for(int j=0;j<e;j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            // ★ 비연결 그래프 대비 모든 정점 순회
            for(int j=1;j<=v;j++){
                if(color[j]==0){
                    dfs(j,1);
                }
            }

            System.out.println(check?"YES":"NO");
            // 다음 테스트케이스 대비 초기화
            graph.clear();
        }
    }
}