package baekjoon.dfs.baekjoon2583;
import java.util.*;
import java.io.*;

public class Main {
    static int m,n,k,count;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static List<Integer> area= new ArrayList<>();
    //1. 2차원 배열의 직사각형
    //2. k개의 직사각형의 개수를 주어지고, 각 직사각형의 좌표를 입력받음
    //2-1. 직사각형 좌표는 왼쪽아래 x, 왼쪽아래 y, 오른쪽위 x, 오른쪽위 y로 입력
    //3. 메인직사각형에서 k개의 직사각형을 제외한 나머지 구역의 개수와 영역넓이를 출력
    static void dfs(int x, int y){
        // ★ 여기서 x는 "x좌표"가 아니라 행(row) 인덱스!
        // dfs(i,j)로 호출했으므로 x=행, y=열
        visited[x][y]=true;
        count++;

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            // ★ nx는 행 → m과 비교, ny는 열 → n과 비교
            // map[m][n]에서 행 크기=m, 열 크기=n
            if(nx>=0 && nx<m && ny>=0 &&ny<n && !visited[nx][ny] && map[nx][ny]==0){
                dfs(nx,ny);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map =new int[m][n];
        visited = new boolean[m][n];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // ★ 입력은 (x,y)로 받지만 배열은 map[행][열] = map[y][x]
            // → 입력의 x,y 순서와 배열 인덱스 순서가 반대!
            for(int j=x1;j<x2;j++){
                for(int l=y1;l<y2;l++){
                    map[l][j]=1;
                    visited[l][j]=true;
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && map[i][j]==0){
                    // 새 영역 탐색 전 초기화
                    count=0;
                    dfs(i,j);
                    // ★ dfs 안이 아닌 여기서 한 번만 add
                    //영역이 끝나면 add시킴
                    area.add(count);
                }
            }
        }

        Collections.sort(area);
        System.out.println(area.size());
        for(int i : area)
            System.out.print(i+" ");
    }
}
