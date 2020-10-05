class cutPoint{
    public static void main(String[] args) {
        int arr[]={1,2,3,4,3};
        matrixChainMul(arr);
    }
    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+"\t");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }


    // matrix mul=====================================================
    public static void matrixChainMul(int arr[]){
        int n=arr.length;
        int dp[][]=new int[n][n];
        // System.out.println(matrixChainMul_recc(arr,0,arr.length-1));
        System.out.println(matrixChainMul_dp(arr,0,arr.length-1,dp));
        print2D(dp);
    }
    public static int matrixChainMul_recc(int arr[],int si,int ei){
        if(si+1==ei){
            return 0;
        }
        int myAns=(int)1e8;
        for(int cut=si+1;cut<ei;cut++){
            int leftTree=matrixChainMul_recc(arr,si,cut);
            int rightTree=matrixChainMul_recc(arr,cut,ei);
            int myCost=leftTree+arr[si]*arr[cut]*arr[ei]+rightTree;
            myAns=Math.min(myAns,myCost);
        }
        return myAns;
    }
    public static int matrixChainMul_dp(int arr[],int si,int ei, int dp[][]){
        if(si+1==ei){
            return dp[si][ei]=0;
        }
        if(dp[si][ei]!=0) return dp[si][ei];
        int myAns=(int)1e8;
        for(int cut=si+1;cut<ei;cut++){
            int leftTree=matrixChainMul_dp(arr,si,cut,dp);
            int rightTree=matrixChainMul_dp(arr,cut,ei,dp);
            int myCost=leftTree+arr[si]*arr[cut]*arr[ei]+rightTree;
            myAns=Math.min(myAns,myCost);
        }
        return dp[si][ei]=myAns;
    }


    //https://www.geeksforgeeks.org/minimum-maximum-values-expression/  -------------------->hw
}