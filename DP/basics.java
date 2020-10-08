import java.util.*;
class basics{
    public static void main(String[] args) {
        solve();
        // stringSet();
    }
    public static void solve(){
        // int n=5;
        // int dp[][]=new int[n][n];
        // int arr[]={2,5,8,3};
        // // LinkedList<Integer> list=new LinkedList<>();
        // // System.out.println(boardPath_jumps(0,10,arr,list));
        // int dir[][]={{1,0},{0,1},{1,1}};
        // System.out.println(multi_jumps(0,0,n-1,n-1,dp,dir));

        //target sum
        int arr[]={2,3,5,7};
        int dp[][]=new int[5][11];
        System.out.println(targetSum_DP(arr,10,4,dp));
        print2d(dp);
    }

    public static void print(int arr[]){
        for(int val:arr)
            System.out.print(val+"\t");
        System.out.println();
    }
    public static void print2d(int arr[][]){
        for(int ar[]:arr)
            print(ar);
        System.out.println();
    }


    // recursion
    public static int pathVH(int sr,int sc,int er,int ec){
        if(sr==er&&sc==ec) return 1;
        int h=0,v=0;
        if(sr+1<=er)
        h=pathVH(sr+1,sc,er,ec);
        if(sc+1<=ec)
        v=pathVH(sr,sc+1,er,ec);
        return h+v;
    }
    // memo
    public static int pathVH(int sr,int sc,int er,int ec,int dp[][]){
        if(sr==er&&sc==ec) return 1;
        int h=0,v=0;
        if(dp[sr][sc]!=0) return dp[sr][sc];
        if(sr+1<=er)
        h=pathVH(sr+1,sc,er,ec,dp);
        if(sc+1<=ec)
        v=pathVH(sr,sc+1,er,ec,dp);
        return dp[sr][sc]=h+v;
    }

    // tabulation 
    public static int pathVH(int n){
        int dp[][]=new int[n][n]; 
        int er=n-1,ec=n-1;
      for(int sr=n-1;sr>=0;sr--){
          for(int sc=n-1;sc>=0;sc--){
            if(sr==er&&sc==ec){
                dp[sr][sc]=1;
                continue;
            }
            int h=0,v=0;
            if(sr+1<=er)
                h=dp[sr+1][sc];
            if(sc+1<=ec)
                v=dp[sr][sc+1];
            dp[sr][sc]=h+v;
          }
      }
      print2d(dp);
      return dp[0][0];
    }


    public static int boardPath(int si,int ei){
        if(si==ei) return 1;
        int count=0;
        for(int no=1;no<=6;no++){
            if(si+no<=ei)
            count+=boardPath(si+no,ei);
        }
        return count;
    }
    // memo
    public static int boardPath(int si,int ei,int dp[]){
        if(si==ei) return dp[si]= 1;
        if(dp[si]!=0) return dp[si];
        int count=0;
        for(int no=1;no<=6;no++){
            if(si+no<=ei)
            count+=boardPath(si+no,ei,dp);
        }
        return dp[si]=count;
    }
    //tab
     public static int boardPath_dp(int si,int ei,int dp[]){
        for(si=ei;si>=0;si--){
            if(si==ei){
                dp[si]= 1;
                continue;
            } 
            int count=0;
            for(int no=1;no<=6;no++){
                if(si+no<=ei)
                count+=dp[si+no];
            }
            dp[si]=count;
        }
        return dp[0];
    }

  
    // board with jumps
    public static int boardPath_jumps(int si,int ei,int arr[],int dp[]){
        if(si==ei) return dp[si]= 1;
        if(dp[si]!=0) return dp[si];
        int count=0;
        for(int no=0;no<arr.length;no++){
            if(si+arr[no]<=ei)
            count+=boardPath_jumps(si+arr[no],ei,arr,dp);
        }
        return dp[si]=count;
    }

    public static int multi_jumps(int sr,int sc,int er,int ec,int dp[][],int dir[][]){
        if(sr==er&&sc==ec) return dp[sr][sc]=1;
        if(dp[sr][sc]!=0) return dp[sr][sc];
        int count=0;
        for(int d=0;d<dir.length;d++){
            for(int jump=1;jump<=Math.max(er,ec);jump++){
                int r=sr+jump*dir[d][0];
                int c=sc+jump*dir[d][1];
                if(r>=0&&c>=0&&r<=er&&c<=ec){
                    count+=multi_jumps(r,c,er,ec,dp,dir);
                }
            }
        }
        return dp[sr][sc]=count;
    }

    // string set=============================================================================
    public static void stringSet(){
        // String str = "geeksforgeeks";
        // int n=str.length();
        // int dp[][]=new int[n][n];
        // System.out.println(longestPSS(0,n-1,str,dp));
        // int len = dp[0][n-1];
        // char[] ans = new char[len];
        // longestPSS_String(str,0,n-1,dp,ans,0,len-1);

        //lcs
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        System.out.println(lCSS(s1,s2,0,0,dp));
        int len=dp[0][0];
        char ans[]=new char[len];
        lCSS_String(s1,s2,0,0,dp,ans,0);
        print2d(dp);
    }
    public static int longestPSS(int i,int j,String str,int dp[][]){
        if(i==j){
            return dp[i][j]=1;
        }
        if(i>j){
            return 0;
        }
        if(dp[i][j]!=0) return dp[i][j];
        if(str.charAt(i)==str.charAt(j)){
            return dp[i][j]= longestPSS(i+1,j-1,str,dp)+2;
        }else{
            return dp[i][j]=Math.max(longestPSS(i,j-1,str,dp),longestPSS(i+1,j,str,dp));
        }
    }
    /// tabulation
    public int longestPalindromeSubseq(String s) {
        int n=s.length();
        int dp[][]=new int[n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;j++,i++){
                if(gap==0){
                    dp[i][j]=1;
                }else{
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j]=dp[i+1][j-1]+2;
                    }else{
                        dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
    public static void longestPSS_String(String str,int i ,int j,int[][] dp,char[] ans,int si,int ei){
        if(i>=j){
            if(i==j){
                ans[si]=str.charAt(i);
                for(char ch:ans) System.out.print(ch);
                System.out.println();
            }
            return;
        }
        if(str.charAt(i)==str.charAt(j)){
            ans[si]=ans[ei]=str.charAt(i);
            longestPSS_String(str,i+1,j-1,dp,ans,si+1,ei-1);
        }else if(dp[i+1][j]>dp[i][j-1]){
            longestPSS_String(str,i+1,j,dp,ans,si,ei);
        }else{
            longestPSS_String(str,i,j-1,dp,ans,si,ei);
        }
    }



    // lcs ===================================================
    public static int lCSS(String s1,String s2,int i,int j,int[][] dp){
        if(i == s1.length() || j == s2.length()){
            return dp[i][j] = 0;
        }

        if(dp[i][j] != 0) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)) return dp[i][j] = lCSS(s1,s2,i+1,j+1,dp) + 1;
        else return dp[i][j] = Math.max(lCSS(s1,s2,i+1,j,dp),lCSS(s1,s2,i,j+1,dp));        
    }
    public static void lCSS_String(String s1,String s2,int i,int j,int[][] dp,char ans[],int idx){
        if(i==s1.length()||j==s2.length()){
            for(char ch:ans) System.out.print(ch);
            System.out.println();
            return;
        }
        if(s1.charAt(i)==s2.charAt(j)){
            ans[idx]=s1.charAt(i);
            lCSS_String(s1,s2,i+1,j+1,dp,ans,idx+1);
        }else if(dp[i+1][j]>dp[i][j+1]){
            lCSS_String(s1,s2,i+1,j,dp,ans,idx);
        }else{
            lCSS_String(s1,s2,i,j+1,dp,ans,idx);
        }
    }


    //https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    int countPS(String str)
    {
        // Your code here
        int n=str.length();
        int dp[][]=new int[n][n];
        return countPS(0,n-1,str,dp);
    }
    public int countPS(int i,int j,String str,int dp[][]){
        if(i>=j){
            return i==j?1:0;
        }
        if(dp[i][j]!=0) return dp[i][j];
        int x=countPS(i+1,j-1,str,dp);
        int y=countPS(i,j-1,str,dp);
        int z=countPS(i+1,j,str,dp);
        if(str.charAt(i)==str.charAt(j)){
            dp[i][j]=(y+z+1);
        }else{
            dp[i][j]=(y+z-x);
        }
        return dp[i][j];
    }

    // target sum
    public static int targetSum(int arr[],int target,int n,int dp[][]){
        if(target==0||n==0){
            return dp[n][target]=target==0?1:0;
        }
        if(dp[n][target]!=0) return dp[n][target];
        if(target-arr[n-1]>=0){
            dp[n][target]+=targetSum(arr,target-arr[n-1],n-1,dp);
        }
        dp[n][target]+=targetSum(arr,target,n-1,dp);
        return dp[n][target];
    }
    public static int targetSum_DP(int arr[],int target,int n,int dp[][]){
        int N=n, Tar=target;
        for(n=0;n<=N;n++){
            for(target=0;target<=Tar;target++){
                if(target==0||n==0){
                    dp[n][target]=target==0?1:0;
                    continue;
                }
                if(target-arr[n-1]>=0){
                    dp[n][target]+= dp[n-1][target-arr[n-1]];
                }
                dp[n][target]+= dp[n-1][target];
            }
        }
        return dp[N][Tar];
    }

    // leetode 494 target sum
    
    public int findTargetSumWays(int[] nums, int tar) {
        int n = nums.length;
        if(n == 0) return 0;
        int sum=0;
        for(int val:nums) sum+=val;
        if(tar > sum || tar < -sum) return 0;
        int dp[][]=new int[n+1][2*sum+1];
        for(int arr[]:dp) Arrays.fill(arr,-1);
        return findTargetSumWays(nums,n,tar+sum,sum,dp);
    }
    public int findTargetSumWays(int arr[],int n,int tar,int sum, int dp[][]){
        if(n==0){
            return tar==sum?1:0;
        }
        if(dp[n][sum]!=-1) return dp[n][sum];
        int ans=0;
        ans+=findTargetSumWays(arr,n-1,tar,sum-arr[n-1],dp);
        ans+=findTargetSumWays(arr,n-1,tar,sum+arr[n-1],dp);
        return dp[n][sum]=ans;
        
    }

    // liss====================================================================
    
    public static int minNoOFDeletion(int[] arr){
        int n = arr.length();
        int[] dp = new int[n];

        int len = 0;
        for(int i = 0; i < n; i++ ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] >= arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return n - len;
    }

    // https://practice.geeksforgeeks.org/problems/find-if-string-is-k-palindrome-or-not1923/1
    // same as lps
    static int kPalindrome(String str, int n, int k)
    {
        // code here
        int dp[][]=new int[n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0){
                    dp[i][j]=1;
                }else if(str.charAt(i)==str.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return (n-dp[0][n-1]<=k)?1:0;
    }

    // 688 leetcode
    public double knightProbability(int N, int K, int r, int c) {
        int dir[][]={{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        double dp[][][]=new double[K+1][N][N];
        return knightProbability(N,K,r,c,dp,dir);
    }
    public double knightProbability(int N, int K, int r, int c, double dp[][][],int dir[][]){
        if(K==0){
            return dp[K][r][c]=1;
        }
        if(dp[K][r][c]!=0.0) return dp[K][r][c];
        double count=0.0;
        for(int d=0;d<dir.length;d++){
            int x=r+dir[d][0];
            int y=c+dir[d][1];
            if(x>=0&&y>=0&&x<N&&y<N){
                count+=knightProbability(N,K-1,x,y,dp,dir);
            }
        }
        return dp[K][r][c]=count/8.0;
    }

    // leetcode 45 optimal solution O(n)
    public int jump(int[] nums) {
        if(nums.length==0) return 0;
        int n=nums.length;
        int jumps=0;
        int maxEnding=0;
        int maxDist=0;
        for(int i=0;i<n-1;i++){
            maxDist=Math.max(maxDist,nums[i]+i);
            if(i==maxEnding){
                maxEnding=maxDist;
                jumps++;
            }
        }
        return jumps;
    }
}