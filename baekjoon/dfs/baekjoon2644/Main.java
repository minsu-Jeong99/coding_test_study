package baekjoon.dfs.baekjoon2644;
import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int start, end;
    // 그래프 인접 리스트: 각 노드에 연결된 노드 번호들을 저장
    static ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
    static boolean[] visited;
    // 촌수 계산 성공 여부
    static boolean found = false;
    //1. 입력 1번 - 사람의 수(노드수) , 입력 2번 - 촌수 계산할 사람 두명 입력
    //2. 입력 3번 - 부모 자식들 간의 관계 수(간선의 수), 입력 4번 - 부모 자식 간의 연결
    //3. 촌수가 없으면 -1
    //4. 노드 레벨을 계산 - 간선의 수를 계산
    static void dfs(int node, int count){
        visited[node] = true;

        if(node == end){
            System.out.println(count);
            found = true;
            return;
        }

        // 인접 노드 순회 - 방문 안 한 노드만 재귀 탐색, count+1로 촌수 증가
        for(int i=0;i<graph.get(node).size();i++){
            int n = graph.get(node).get(i);
            if(!visited[n])
                dfs(n,count+1);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt (st.nextToken());
        m = Integer.parseInt(br.readLine());

        // n번 노드까지 접근하려면 n+1이어야 해요
        for(int i=0;i<=n;i++)
            graph.add(new ArrayList<>());

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph.get(p).add(c);
            graph.get(c).add(p);
        }

        visited = new boolean[n+1];

        dfs(start,0);

        // ★ DFS 종료 후 found가 false면 두 사람은 친척 관계가 아님
        if(!found)
            System.out.print("-1");
    }
}
