import java.util.Scanner;

public class Q12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //TODO:将这些变量作为类的属性，就不需要传参数了
        int m = sc.nextInt();
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(findWays(arr, n, m, x1-1, y1-1, x2-1, y2-1));
    }

    private static int findWays(int[][] arr, int n, int m , int x1, int y1, int x2, int y2){
        //入参规范性检验
        if(arr == null || x1<0 || x1>n || y1<0 || y1>m || x2<0 || x2>n || y2<0 || y2>m ){
            return -1;
        }

        int[][] old = new int[n][m];
        return findByRec(n,m,arr,x1,y1,x2,y2,old);
    }

    private static int findByRec(int n, int m, int[][] arr, int row1, int col1, int row2, int col2, int[][] old){
        int pathUP=0;
        int pathDown=0;
        int pathL=0;
        int pathR=0;
        if(row1==row2 && col1==col2){
            return 0;
        }else {
            old[row1][col1] = 1;
            if(row1 != 0 && old[row1-1][col1] != 1 && arr[row1-1][col1] >= arr[row1][col1]){//往上走
                pathUP = 1 + findByRec( n, m, arr, row1-1, col1, row2, col2, old);
            }
            if(row1 != n-1 && old[row1+1][col1] != 1 &&  arr[row1+1][col1] >= arr[row1][col1]){//往下走
                pathDown = 1+ findByRec(n, m, arr, row1+1, col1, row2, col2, old);
            }
            if(col1 != 0 && old[row1][col1-1] != 1 && arr[row1][col1-1] >= arr[row1][col1]){//往左走
                pathL = 1 + findByRec(n, m, arr, row1, col1-1, row2, col2, old);
            }
            if(col1 != m-1 && old[row1][col1+1] != 1 && arr[row1][col1+1] >= arr[row1][col1]){//往右走
                pathR = 1+ findByRec(n, m, arr, row1, col1+1, row2, col2, old);
            }

            if(pathUP==0 && pathDown==0 && pathL==0 && pathR==0){
                return -1;
            }else {
                if(pathUP==0) pathUP=n*m;
                if(pathDown==0) pathDown=n*m;
                if(pathL==0) pathL=n*m;
                if(pathR==0) pathR=n*m;
                return Math.min(Math.min(pathDown,pathL),Math.min(pathR,pathUP));
            }
        }
    }
}
