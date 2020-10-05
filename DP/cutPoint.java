class cutPoint{
    public static void main(String[] args) {
        int arr[]={1,2,3,4,3};
        cutType(arr);
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
    public static void cutType(int arr[]){
        // int n=arr.length;
        // int dp[][]=new int[n][n];
        // System.out.println(matrixChainMul_recc(arr,0,arr.length-1));
        // System.out.println(matrixChainMul_dp(arr,0,arr.length-1,dp));
        // print2D(dp);
        String str="1+2*3+4*5";
        int n=str.length();
        Pair dp[][]=new Pair[n][n];
        Pair ans=minMaxExpression_tab(str,0,str.length()-1,dp);
        System.out.println(ans.min);
        System.out.println(ans.max);
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(dp[i][j]==null)
        //         System.out.print("0\t");
        //         else
        //         System.out.print(dp[i][j].min+"\t");
        //     }
        //     System.out.println();
        // }
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
    static class Pair{
        int min;
        int max;
        Pair(int min,int max){
            this.min=min;
            this.max=max;
        }
    }
    public static Pair minMaxExpression(String str,int si,int ei){
        if(si==ei){
            int val=str.charAt(si)-'0';
            return new Pair(val,val);
        }
        int min=(int)1e8,max=(int)-1e8;
        for(int cut=si+1;cut<ei;cut+=2){
            Pair lc=minMaxExpression(str,si,cut-1);
            Pair rc=minMaxExpression(str,cut+1,ei);
            max=Math.max(max,calculate(lc.max,rc.max,str.charAt(cut)));
            min=Math.min(min,calculate(lc.min,rc.min,str.charAt(cut)));
        }
        return new Pair(min,max);
    }
    // dp
    public static Pair minMaxExpression_dp(String str,int si,int ei, Pair dp[][]){
        if(si==ei){
            int val=str.charAt(si)-'0';
            return dp[si][ei]= new Pair(val,val);
        }
        if(dp[si][ei]!=null) return dp[si][ei];
        int min=(int)1e8,max=(int)-1e8;
        for(int cut=si+1;cut<ei;cut+=2){
            Pair lc=minMaxExpression_dp(str,si,cut-1,dp);
            Pair rc=minMaxExpression_dp(str,cut+1,ei,dp);
            max=Math.max(max,calculate(lc.max,rc.max,str.charAt(cut)));
            min=Math.min(min,calculate(lc.min,rc.min,str.charAt(cut)));
        }
        return dp[si][ei]=new Pair(min,max);
    }
    public static Pair minMaxExpression_tab(String str,int Si,int Ei, Pair dp[][]){
        for(int gap=0;gap<str.length();gap++){
            for(int si=0,ei=gap;ei<str.length();ei++,si++){
                if(si==ei){
                    int val=str.charAt(si)-'0';
                    dp[si][ei]= new Pair(val,val);
                    continue;
                }
                int min=(int)1e8,max=(int)-1e8;
                for(int cut=si+1;cut<ei;cut+=2){
                    Pair lc=dp[si][cut-1];
                    Pair rc=dp[cut+1][ei];
                    max=Math.max(max,calculate(lc.max,rc.max,str.charAt(cut)));
                    min=Math.min(min,calculate(lc.min,rc.min,str.charAt(cut)));
                }
                dp[si][ei]=new Pair(min,max);
            }
        }
        return dp[Si][Ei];
    }


    public static int calculate(int val1,int val2,char op){
        if(op=='+'){
            return val1+val2;
        }else{
            return val1*val2;
        }
    }
}