import java.util.Scanner;

public class Q1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();

        int[] result = findMin(N, L);
        if (result==null){
            System.out.println("No");
            return;
        }
        for (int i = result[0]; i < result[1]+result[0]; i++) {
            if(i != result[0]){
                System.out.print(" ");
            }
            System.out.print(i);
        }
    }

    static private int[]  findMin(int N, int L){
        int x;
        for(int l=L; l<=100; l++){
            if ((2*N+l-l*l) % (2*l) == 0){ //通过求余来判断x是否存在
                x = (2*N+l-l*l) / (2*l);
                return new int[]{x, l};
            }
        }

        //说明不存在这样的数
        return null;
    }

}