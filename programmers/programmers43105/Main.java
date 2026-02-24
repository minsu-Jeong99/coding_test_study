package programmers43105;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //1.위에서부터 차례대로 비교하면서 누적해 나가는 그리디 방식은 최대값을 놓칠 수 있음
        //1-1.밑에서부터 누적 합을 비교하면서 올라가는 bottom-up 방식 사용
        //2.이중for문 사용
        //3.Math.max 사용
        //4.각 원소들의 합을 윗배열의 누적하면서 최대값을 구함
        Scanner sc = new Scanner(System.in);

        System.out.print("행 수를 입력하세요 : ");
        int n = sc.nextInt();
        int[][] triangle = new int[n][];

        System.out.println("삼각형의 각 원소를 입력하세요 : ");
        for(int i=0;i<n;i++){
            triangle[i] = new int[i+1];
            for(int j=0;j<=i;j++){
                triangle[i][j]=sc.nextInt();
            }
        }

        for(int i=triangle.length-1;i>0;i--){
            for(int j=0;j<triangle[i-1].length;j++){
                triangle[i-1][j]+=Math.max(triangle[i][j],triangle[i][j+1]);
            }
        }

        System.out.println("삼각형의 꼭대기에 최댓값 : " + triangle[0][0]);


        //그리디 방식, 이럴 경우 오답 가능성있음, 경로를 정해놓고 가는게 아님
        //코드 하다가 변경함
        //모든 경로의 합을 누적해 나가는 dp bottom-up 방식으로 수정필요
//         int count=0;
        
//         for(int i=0;i<triangle.length;i++){
//             if(triangle[i].length==1)
//                 answer+=triangle[i][0];
//             else if(triangle[i].length==2){
//                 if (triangle[i][0] > triangle[i][1]) {
//                     answer += triangle[i][0];
//                     count = 0; // 0번 인덱스 선택
//                 } else {
//                     answer += triangle[i][1];
//                     count = 1; // 1번 인덱스 선택
//                 }
//             }
//         }
    }
}
