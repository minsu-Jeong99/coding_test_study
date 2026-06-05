package programmers12941;

import java.io.*;
import java.util.*;

public class Main{
    //1. 그리디 - 항상 최소값을 찾아야함
    //2. A배열의 가장 큰값 * B배열의 가장 작은값 일경우 최소값이 나옴
    //2-1. A배열의 가장 작은값 * B배열의 가장 큰값
    //2-2. 둘다 가장 큰값끼리 곱 = 최댓값
    //2-3. 둘다 가장 작은값끼리 곱 = 최소값이 구해지겠지만 반례가 존재함
    //3. 둘 중 하나를 내림차순 , 오름차순 정렬
    //4. 둘다 길이는 같으니, 하나의 반복문안을 사용하여 계산
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            A[i]=Integer.parseInt(st.nextToken());            
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            B[i]=Integer.parseInt(st.nextToken());            
        }

        int answer = 0;
        //내림차순 정렬을 위한 인덱스 활용 count, 값교환할 temp
        int count=0,temp=0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        //내림차순 정렬
        //서로 끝지점을 교환하면서 오기에 B.length/2 지정함
        for(int i=B.length-1;i>=B.length/2;i--){
            temp=B[count];
            B[count]=B[i];
            B[i]=temp;
            count++;
        }
        
        for(int i=0;i<A.length;i++){
            answer+=(A[i]*B[i]);
        }

        System.out.println(answer);
    }
}