class cutPoint{
    public static void main(String[] args) {
        int arr[]={4,6,3,1,3};
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
        // // System.out.println(matrixChainMul_recc(arr,0,arr.length-1));
        // System.out.println(matrixChainMul_String(arr,0,arr.length-1,dp));
        // print2D(dp);



        // String str="1+2*3+4*5";
        // int n=str.length();
        // Pair dp[][]=new Pair[n][n];
        // Pair ans=minMaxExpression_tab(str,0,str.length()-1,dp);
        // System.out.println(ans.min);
        // System.out.println(ans.max);
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(dp[i][j]==null)
        //         System.out.print("0\t");
        //         else
        //         System.out.print(dp[i][j].min+"\t");
        //     }
        //     System.out.println();
        // }
        int keys[] = {10, 12, 20}, freq[] = {34, 8, 50};
        int n=keys.length;
        int dp[][]=new int[n][n];
        System.out.println(optimalBst(keys,freq,0,keys.length-1,1,dp));
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
    public static int matrixChainMul_String(int arr[],int Si,int Ei, int dp[][]){
        String sdp[][]=new String[arr.length][arr.length];  
       for(int gap=0;gap<arr.length;gap++){
            for(int si=0,ei=gap;ei<arr.length;si++,ei++){
                if(si+1==ei){
                   dp[si][ei]=0;
                   sdp[si][ei]=(char)(si+'A')+"";
                   continue;
                }
                int myAns=(int)1e8;
                String s="";
                for(int cut=si+1;cut<ei;cut++){
                    int leftTree= dp[si][cut];
                    int rightTree=dp[cut][ei];
                    int myCost=leftTree+arr[si]*arr[cut]*arr[ei]+rightTree;
                    if(myAns>myCost){
                        myAns=myCost;
                        s= "("+sdp[si][cut]+sdp[cut][ei]+")";
                    }
                }
                dp[si][ei]=myAns;
                sdp[si][ei]=s;
            }
       }
       System.out.println(sdp[Si][Ei]);
       return dp[Si][Ei];
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
    // public static minMaxPair evalCombination(minMaxPair p1,minMaxPair p2,char operator){

    //     int a = calculate(operator,p1.minVal,p2.minVal);
    //     int b = calculate(operator,p1.minVal,p2.maxVal);
    //     int c = calculate(operator,p1.maxVal,p2.minVal);
    //     int d = calculate(operator,p1.maxVal,p2.maxVal);

    //     minMaxPair p = new minMaxPair();
    //     p.minVal = Math.min(Math.min(a,b),Math.min(c,d));
    //     p.maxVal = Math.max(Math.max(a,b),Math.max(c,d));

    //     return p;
    // }

    // public static minMaxPair minMaxValue_02(int[] numArr,char[] chArr,int si,int ei,minMaxPair[][] dp){
    //     if(si == ei){
    //         int val = numArr[si];
    //         return dp[si][ei] = new minMaxPair(val,val);
    //     }

    //     if(dp[si][ei] != null) return dp[si][ei];
        
    //     minMaxPair myAns = new minMaxPair();

    //     for(int cut = si; cut < ei; cut++){
    //         minMaxPair leftTree = minMaxValue(str,si , cut,dp);
    //         minMaxPair rightTree = minMaxValue(str,cut + 1, ei,dp);

    //         char ch = chArr[cut];
    //         minMaxPair p = evalCombination(operator,leftTree,rightTree);

    //         myAns.minVal = Math.min(myAns.minVal, p.minVal);
    //         myAns.maxVal = Math.max(myAns.maxVal, p.maxVal);
    //     }

    //     return dp[si][ei] = myAns;        
    // }

    public static int calculate(int val1,int val2,char op){
        if(op=='+'){
            return val1+val2;
        }else{
            return val1*val2;
        }
    }



    // https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

    public static int optimalBst(int keys[],int freq[],int si, int ei,int level, int dp[][]){
        if(si==ei){
            return level*freq[si];
        }
        int min=(int)1e8;
        for(int cut=si;cut<=ei;cut++){
            int leftTree=si==cut?0:optimalBst(keys,freq,si,cut-1,level+1,dp);
            int rightTree=ei==cut?0:optimalBst(keys,freq,cut+1,ei,level+1,dp);
            int myCost=leftTree+rightTree+(level*freq[cut]);
            min=Math.min(min,myCost);
        }
        return min;
    }

    // 1039 leetcode

    class Solution {
        public int minScoreTriangulation(int[] arr) {
            int n=arr.length;
            int dp[][]=new int [n][n];
            for(int d[]:dp) Arrays.fill(d,-1);
            return minScoreTriangulation(0,n-1,arr,dp);
        }
        public int minScoreTriangulation(int si,int ei, int arr[],int dp[][]){
            if(ei-si<2){
                return dp[si][ei]=0;
            }
            if(dp[si][ei]!=-1) return dp[si][ei];
            int min=(int)1e8;
            for(int cut=si+1;cut<ei;cut++){
                int leftTree=minScoreTriangulation(si,cut,arr,dp);
                int rightTree=minScoreTriangulation(cut,ei,arr,dp);
                int myAns=leftTree+arr[si]*arr[cut]*arr[ei]+rightTree;
                min=Math.min(min,myAns);
            }
            return dp[si][ei]=min;
        }
    }

    //leetcode 132 optimized
    class Solution {
        public int minCut(String s) {
            int n=s.length();
            int dp[]=new int[n];
            Arrays.fill(dp,-1);
            boolean isPalindrome[][]=new boolean[n][n];
            for(int gap=0;gap<n;gap++){
                for(int i=0,j=gap;j<n;i++,j++){
                    if(gap==0) isPalindrome[i][j]=true;
                    else if(gap==1&&s.charAt(i)==s.charAt(j)) isPalindrome[i][j]=true;
                    else if(s.charAt(i)==s.charAt(j)&&isPalindrome[i+1][j-1])
                        isPalindrome[i][j]=true;
                }
            }
            return minCut(0,n-1,dp,isPalindrome);
        }
        public int minCut(int si,int ei,int dp[], boolean isPalindrome[][]){
            if(isPalindrome[si][ei]) return 0;
            if(dp[si]!=-1) return dp[si];
            int min=(int)1e8;
            for(int cut=si;cut<ei;cut++){
                if(isPalindrome[si][cut]){
                    min=Math.min(min,minCut(cut+1,ei,dp,isPalindrome)+1);
                }
            }
            return dp[si]=min;
        }
    }

    //https://practice.geeksforgeeks.org/problems/boolean-parenthesization/0 diff approch in foundation
    static int mod=1003;
    private static class Pair{
        int tw=0;
        int fw=0;
        Pair(int tw,int fw){
            this.tw=tw;
            this.fw=fw;
        }
    }
    private static Pair booleanParenthesization(int si,int ei,String str,Pair dp[][]){
        if(si==ei){
            Pair base=new Pair(0,0);
            base.tw=str.charAt(si)=='T'?1:0;
            base.fw=str.charAt(si)=='F'?1:0;
            return base;
        }
        if(dp[si][ei]!=null) return dp[si][ei];
        int t=0,f=0;
        for(int cut=si+1;cut<ei;cut+=2){
            Pair left=booleanParenthesization(si,cut-1,str,dp);
            Pair right=booleanParenthesization(cut+1,ei,str,dp);
            Pair ans=evaluate(left,right,str.charAt(cut));
            t=(t%mod +ans.tw%mod)%mod;
            f=(f%mod + ans.fw%mod)%mod;
        }
        return dp[si][ei]=new Pair(t,f);
    }
    private static Pair evaluate(Pair left,Pair right,char op){
        Pair ans=new Pair(0,0);
        int Ltf=(left.tw % mod + left.fw % mod)%mod;
        int Rtf=(right.tw % mod + right.fw % mod)%mod;
        int Ttf=((Ltf % mod ) * (Rtf % mod))%mod;
        if(op=='&'){
            ans.tw=(left.tw%mod * right.tw%mod)%mod;
            ans.fw=((Ttf % mod) - (left.tw%mod * right.tw%mod)%mod +mod )%mod; 
        }else if(op=='|'){
            ans.tw=((Ttf % mod) - (left.fw%mod * right.fw%mod)%mod +mod )%mod; 
            ans.fw=(left.fw%mod * right.fw%mod)%mod;
        }else{
            ans.tw=( (left.tw%mod * right.fw%mod)%mod + (left.fw%mod * right.tw% mod)%mod )%mod;
            ans.fw=( (left.tw%mod * right.tw%mod)%mod + (left.fw%mod * right.fw% mod)%mod )%mod;
        }
        return ans;
    }

    // leetcode 1278
    public int palindromePartition(String s, int k) {
        int n=s.length();
        int dp[][]=new int[n+1][k+1];
        for(int d[]:dp) Arrays.fill(d,-1);
        int pdp[][]=new int[n][n];
        for(int gap=1;gap<n;gap++){
            for(int i=0,j=gap;j<n;j++,i++){
                pdp[i][j]+=pdp[i+1][j-1];
                if(s.charAt(i)!=s.charAt(j)) pdp[i][j]+=1;
            }
        }
       return palindromePartition(s,k,0,n-1,dp,pdp);
    }
    public int palindromePartition(String s,int k,int si,int ei,int dp[][],int pdp[][]){
        if(k>=(ei-si+1)){
            dp[ei][k]=k==(ei-si+1)?0:(int)1e8;
        }
        if(k==1||si==ei){
            return dp[ei][k]=(si==ei)?0:pdp[si][ei];
        }
        if(dp[ei][k]!=-1) return dp[ei][k];
        int min=(int)1e8;
        for(int cut=si;cut<ei;cut++){
            int recAns=palindromePartition(s,k-1,si,cut,dp,pdp);
            if(recAns!=(int)1e8){
                min=Math.min(recAns+pdp[cut+1][ei],min);
            }
        }
        return dp[ei][k]=min;
    }
}