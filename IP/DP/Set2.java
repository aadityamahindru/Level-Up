class Set2{
    //https://www.geeksforgeeks.org/minimum-maximum-values-expression/
    static class Pair{
        int min=(int)1e8;
        int max=(int)-1e8;
        Pair(int min,int max){
            this.min=min;
            this.max=max;
        }
    }
    public static Pair minMaxExpression(int si,int ei,String str,Pair dp[][]){
        if(si==ei){
            int val=str.charAt(si)-'0';
            return dp[si][ei]=new Pair(val,val);
        }
        int min=(int)1e8,max=(int)-1e8;
        if(dp[si][ei]!=null) return dp[si][ei];
        for(int cut =si+1;cut<ei;cut+=2){
            Pair leftTree=minMaxExpression(si,cut-1,str,dp);
            Pair rightTree=minMaxExpression(cut+1,ei,str,dp);
            Pair myAns=evaluate(leftTree,rightTree,str.charAt(cut));
            min=Math.min(min,myAns.min);
            max=Math.max(max,myAns.max);
        }
        return dp[si][ei]=new Pair(min,max);
    }
    public static Pair evaluate(Pair leftTree,Pair rightTree,char ch){
        Pair ans=new Pair(0,0);
        if(ch=='*'){
            ans.min=leftTree.min*rightTree.min;
            ans.max=leftTree.max*rightTree.max;
        }else{
            ans.max=leftTree.max+rightTree.max;
            ans.min=leftTree.min+rightTree.min;
        }
        return ans;
    }

    public static void print1D(int arr[]){
        for(int a:arr){
            System.out.print(a+"\t");
        }
        System.out.println();
    }
    public static void print2D(int arr[][]){
        for(int a[]:arr){
            print1D(a);
        }
    }
    public static void solve(){
        String str="1+2*3+4*5";
        int n=str.length();
        Pair dp[][]=new Pair[n][n];
        System.out.println(minMaxExpression(0,n-1,str,dp).max);
    }
    public static void main(String[] args) {
        solve();
    }
}