package baekjoon.dfs.baekjoon1987;

import java.io.*;
import java.util.*;

public class Main {
    static int r, c, max=0;
    static char[][] map;
    static boolean[] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    //1. 2차원 배열을 사용
    //2. 상하좌우로 한칸씩 이동가능, 같은 알파벳이 적힌 칸은 두 번 못지남
    //3. 최대 몇 칸을 이동 가능한지 출력
    //4. 알바벳 수는 26개 - boolean 배열 사용하여 체크
    //5. 백트래킹 사용 - dfs로 탐색하면서 방문한 알파벳 체크, 더이상 이동할 수 없을 때 count 갱신
    //5-1. dfs 탐색이 끝나면 방문한 알파벳 체크 해제하여 다른 경로 탐색 가능하도록

    static void dfs(int x,int y,int count){
        visited[map[x][y]-'A']=true;
        // ★ 최대값 갱신 - 전역변수 max에 저장, count는 파라미터라 여기서 갱신해야함
        max = Math.max(max,count);

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<r && ny>=0 && ny<c &&!visited[map[nx][ny]-'A']){
                dfs(nx,ny,count+1);
            }
        }
        // ★ 백트래킹 - 이 경로 탐색이 끝났으므로 방문 해제
        // 해제하지 않으면 다른 경로에서 이 알파벳을 사용하지 못함
        // 반드시 dfs 안에서 재귀 호출 후에 해야 함 (main에서 하면 안됨)
        visited[map[x][y]-'A']=false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[26];

        for(int i=0;i<r;i++){
            String line = br.readLine();
            for(int j=0;j<c;j++){
                map[i][j] = line.charAt(j);
            }
        }

        // for(int i=0;i<r;i++){
        //     for(int j=0;j<c;j++){
        //         if(!visited[map[i][j]-'A']){
        //             dfs(i,j,count+1);
                    
        //         }
        //     }
        // }
        // ★ 항상 좌측 상단(0,0)에서만 시작, 시작칸도 1칸으로 카운트
        dfs(0,0,1);
        // ★ count(전역변수, 항상 0)가 아닌 max를 출력해야 함
        System.out.print(max);
    }
}
