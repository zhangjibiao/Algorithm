public class Q20 {
    public static int largest1BorderedSquare(int[][] arr){
        if (arr == null){
            return -1;
        }

        int N = arr.length;
        int M = arr[0].length;
        int[][] rightOnes = new int[N][M];
        int[][] downOnes = new int[N][M];

        for (int i = 0; i < N; i++) {
            rightOnes[i][M-1] = arr[i][M-1];
        }
        for (int j = 0; j < M; j++) {
            downOnes[N-1][j] = arr[N-1][j];
        }

        for (int j = M-2; j >= 0; j--) { // 生成rightOnes
            for (int i = 0; i < N; i++) {
                rightOnes[i][j] = arr[i][j]==1 ? 1+rightOnes[i][j+1] : 0;
            }
        }

        for (int i = N-2; i >= 0; i--) { // 生成downOnes
            for (int j = 0; j < M; j++) {
                downOnes[i][j] = arr[i][j]==1 ? 1+downOnes[i+1][j] : 0;
            }
        }

        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int maxLength = Math.min(N-i, M-j);
                for (int k = 1; k <= maxLength; k++) {
                    if (rightOnes[i][j] >= k && downOnes[i][j] >= k
                            && rightOnes[i+k-1][j] >= k && downOnes[i][j+k-1] >= k ){
                        maxArea = Math.max(k * k, maxArea);
                    }
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        int[] arr1 = {1,1,0};
        int[] arr2 = {1,1,1};
        int[] arr3 = {1,1,1};
        int[] arr4 = {1,1,1};
        int[][] arr = {arr1,arr2,arr3,arr4};
        System.out.println(largest1BorderedSquare(arr));
    }
}
