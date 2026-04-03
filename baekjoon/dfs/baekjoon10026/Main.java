package baekjoon.dfs.baekjoon10026;

import java.io.*;

public class Main {
    //count1은 정상, count2는 적록색약
    static int n, count1=0, count2=0;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static void dfs1(int x, int y){
        visited[x][y]=true;
        //현재 칸의 색 저장
        char color = map[x][y];

        for(int i=0;i<4;i++){
            int nx = x +dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny]){
                //인접 칸이 현재 색과 같은 색일 때만 탐색
                if(map[nx][ny] == color){
                    dfs1(nx,ny);
                }
                else if(map[nx][ny] == color){
                    dfs1(nx,ny);
                }
                else if(map[nx][ny] == color){
                    dfs1(nx,ny);
                }
            }
        }
    }
    static void dfs2(int x, int y){
        visited[x][y]=true;
        //현재 칸의 색 저장
        char color = map[x][y];

        for(int i=0;i<4;i++){
            int nx = x +dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny]){
                //현재 칸만 보면 경계를 못 막음 , 인접 칸만 보면 다른 구역으로 넘어감
                //e.g.) 현재 B, 인접 R = 인접만 보면 R/G 구역 조건 true -> B에서 R로 넘어가는 오류
                // if(map[nx][ny] == 'R' || map[nx][ny] == 'G'){
                //     dfs2(nx,ny);
                // }
                // else if(map[nx][ny] == 'B'){
                //     dfs2(nx,ny);
                // }

                //현재 칸과 인접 간 둘 다 확인 - 적 + 녹
                if((map[nx][ny] =='R' || map[nx][ny]=='G')&&(color=='R' || color=='G')){
                    dfs2(nx,ny);
                }
                //B는 B끼리만 탐색
                else if(map[nx][ny]=='B' && color=='B'){
                    dfs2(nx,ny);
                }
            }
        }
    }
    //1. n * n 크기의 그림 - rgb
    //2. 적록색약 : 빨간 + 초록 , 파랑
    //2-1. 정상 : 빨간, 초록, 파랑
    //3. rgb 구역을 정상과 적록생략 각각 구분하여 출력

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<n;j++){
                map[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    dfs1(i,j);
                    count1++;
                }
            }
        }

        visited = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    dfs2(i,j);
                    count2++;
                }
            }
        }

        System.out.print(count1+ " " + count2);
    }
}
