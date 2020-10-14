import java.util.*;
class Set1{

    //221 leetcode 
    //recursion
    public int maximalSquare(char[][] arr) {
        if(arr.length==0||arr[0].length==0) return 0;
        int n=arr.length;
        int m=arr[0].length;
        int dp[][]=new int[n][m];
        for(int d[]:dp) Arrays.fill(d,-1);
        int max=0;
        for(int i=0;i<n;i++){
		    for(int j=0;j<m;j++){
		        if(arr[i][j]=='1'){
		            max=Math.max(max,dfs(arr,i,j,dp));
		        }
		    }
		}
        return max*max;
    }
    public  int dfs(char arr[][],int i,int j,int dp[][]){
	    if(i<0||j<0||i>=arr.length||j>=arr[0].length||arr[i][j]=='0') return 0;
	    if(dp[i][j]!=-1) return dp[i][j];
	    dp[i][j]=Math.min(dfs(arr,i+1,j,dp),Math.min(dfs(arr,i,j+1,dp),dfs(arr,i+1,j+1,dp)))+1;
	    return dp[i][j];
    }
    // tabulation
    public int maximalSquare_tab(char[][] arr) {
        if(arr.length==0||arr[0].length==0) return 0;
        int n=arr.length;
        int m=arr[0].length;
        int dp[][]=new int[n+1][m+1];
        int max=0;
        for(int i=n-1;i>=0;i--){
		    for(int j=m-1;j>=0;j--){
		        if(arr[i][j]=='0') {
                   dp[i][j]=0;
                    continue;
                }
	            dp[i][j]=Math.min(dp[i+1][j],Math.min(dp[i][j+1],dp[i+1][j+1]))+1;
                max=Math.max(dp[i][j],max);
		    }
		}
        return max*max;
    }

    // leetcode 416 
    // rec
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int val:nums) sum+=val;
        if(sum%2!=0) return false;
        int tar=sum/2;
        int n=nums.length;
        int dp[][]=new int[n+1][tar+1];
        for(int d[]:dp) Arrays.fill(d,-1);
        return canPartition(n,tar,nums,dp)==1;
        
    }
    public int canPartition(int n,int tar,int nums[],int dp[][]){
        if(n==0||tar==0){
            return tar==0?1:0;
        }
        boolean res=false;
        if(dp[n][tar]!=-1) return dp[n][tar];
        if(tar-nums[n-1]>=0){
            res=res||canPartition(n-1,tar-nums[n-1],nums,dp)==1;
        }
        res=res||canPartition(n-1,tar,nums,dp)==1;
        return dp[n][tar]=(res)?1:0;
    }
    // tabulation
    public int canPartition_tab(int n,int tar,int nums[],int dp[][]){
        int N=n,Tar=tar;
        for(n=0;n<=N;n++){
            for(tar=0;tar<=Tar;tar++){
                if(n==0||tar==0){
                    dp[n][tar]=tar==0?1:0;
                    continue;
                }
                boolean res=false;
                if(tar-nums[n-1]>=0){
                    res=res||dp[n-1][tar-nums[n-1]]==1;
                }
                res=res||dp[n-1][tar]==1;
                dp[n][tar]=(res)?1:0;
            }
        }
        return dp[N][Tar];
    }
    // leetcode 45 jump game 2
    public int jump(int[] nums) {
        if(nums.length==0) return 0;
       int jump=0;
        int maxending=0;
        int maxDist=0;
        for(int i=0;i<nums.length-1;i++){
            maxDist=Math.max(maxDist,nums[i]+i);
            if(i==maxending){
                jump++;
                maxending=maxDist;
            }
        }
        return jump;
    }
    //https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public int LongestBitonicSequence(int[] arr)
    {
        // Code here
        if(arr.length==0) return 0;
        int n=arr.length;
        int lis[]=new int[n];
        int lds[]=new int[n];
        // lis
        for(int i=0;i<n;i++){
            int max=0;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]&lis[j]>max){
                    max=lis[j];
                }
            }
            lis[i]=max+1;
        }
        //lds
        for(int i=n-1;i>=0;i--){
            int max=0;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[i]&lds[j]>max){
                    max=lds[j];
                }
            }
            lds[i]=max+1;
        }
        int ans=0;
        for(int i=0;i<n;i++){
            if(lis[i]+lds[i]-1>ans){
                ans=lis[i]+lds[i]-1;
            }
        }
        return ans;
    }
    //https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence/0
    public static int maxSumBitonic(int arr[],int n){
	    if(arr.length==0) return 0;
        int lis[]=new int[n];
        int lds[]=new int[n];
        // lis
        for(int i=0;i<n;i++){
            int max=0;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]&lis[j]>max){
                    max=lis[j];
                }
            }
            lis[i]=max+arr[i];
        }
        //lds
        
        for(int i=n-1;i>=0;i--){
            int max=0;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[i]&lds[j]>max){
                    max=lds[j];
                }
            }
            lds[i]=max+arr[i];
        }
        int ans=0;
        for(int i=0;i<n;i++){
            if(lis[i]+lds[i]-arr[i]>ans){
                ans=lis[i]+lds[i]-arr[i];
            }
        }
        return ans;
    }
    //https://practice.geeksforgeeks.org/problems/lcs-of-three-strings/0
    public static int lcs(String s1,String s2,String s3,int n,int m,int k,int dp[][][]){
        if(n==0||k==0||m==0){
            return dp[n][m][k]=0;
        }
        if(dp[n][m][k]!=-1) return dp[n][m][k];
        if(s1.charAt(n-1)==s2.charAt(m-1)&&s1.charAt(n-1)==s3.charAt(k-1)){
            return dp[n][m][k]=lcs(s1,s2,s3,n-1,m-1,k-1,dp)+1;
        }else{
            int x=lcs(s1,s2,s3,n-1,m,k,dp);
            int y=lcs(s1,s2,s3,n,m-1,k,dp);
            int z=lcs(s1,s2,s3,n,m,k-1,dp);
            dp[n][m][k]=Math.max(x,Math.max(y,z));
            return dp[n][m][k];
        }
    }
    //https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    public long countFriendsPairings(int n,long dp[]){
        if(n<=2) return n;
        if(dp[n]!=0) return dp[n];
       long ways=0;
       ways=countFriendsPairings(n-1,dp)+(n-1)*countFriendsPairings(n-2,dp);
       ways%=1000000007;
       return dp[n]=ways;
    }
    //https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    public static int maxOverlappingBridges(int arr[][],int n){
        Arrays.sort(arr,(a,b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
         });
         int max=0;
         int dp[]=new int[n];
         for(int i=0;i<n;i++){
             int len=0;
             for(int j=i-1;j>=0;j--){
                 if(arr[j][1]<arr[i][1]&&dp[j]>len){
                     len=dp[j];
                 }
             }
             dp[i]=len+1;
             max=Math.max(dp[i],max);
         }
         return max;
    }
    //https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    public static int partitionKsubSet(int n,int k,int dp[][]){
        if(k==n||k==1){
            return dp[n][k]=1;
        }
        if(dp[n][k]!=0) return dp[n][k];
        int mySet=partitionKsubSet(n-1,k-1,dp);
        int partOfSet=partitionKsubSet(n-1,k,dp);
        return dp[n][k]=mySet+k*partOfSet;
    }
    // tabulation
    public static int partitionKsubSet_tab(int n,int k,int dp[][]){
        int K=k,N=n;
        for(n=1;n<=N;n++){
            for(k=1;k<=K;k++){
                if(k==n||k==1){
                    dp[n][k]=1;
                    continue;
                }
                int mySet= dp[n-1][k-1];
                int partOfSet=dp[n-1][k];
                dp[n][k]=mySet+k*partOfSet;
            }
        }
        return dp[N][K];
    }
    // LPS 516 LEET
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
    // 887 leetcode eggdrop
    public int superEggDrop(int K, int N) {
        int dp[][]=new int[N+1][K+1];
        // for(int d[]:dp) Arrays.fill(d,-1);
        return superEggDrop_tab(N,K,dp);
    }
    // n= floor, k=eggs
    public int superEggDrop(int n,int k,int dp[][]){
        if(k==1||n<=1) return n;
        if(dp[n][k]!=-1) return dp[n][k];
        int min=(int)1e8;
        for(int x=1;x<=n;x++){
            int eggBreak=superEggDrop(x-1,k-1,dp);
            int eggNotBreak=superEggDrop(n-x,k,dp);
            int worstCase=Math.max(eggBreak,eggNotBreak);
            min=Math.min(min,worstCase);
        }
        return dp[n][k]=min+1;
    }
    public int superEggDrop_tab(int n,int k,int dp[][]){
        int N=n,K=k;
        for(n=0;n<=N;n++){
            for(k=1;k<=K;k++){
                if(k==1||n<=1){ 
                    dp[n][k]=n;
                    continue;
                }
                int min=(int)1e8;
                for(int x=1;x<=n;x++){
                    int eggBreak= dp[x-1][k-1];
                    int eggNotBreak= dp[n-x][k];
                    int worstCase=Math.max(eggBreak,eggNotBreak);
                    min=Math.min(min,worstCase);
                }
                dp[n][k]=min+1;
            }
        }
        return dp[N][K];
    }
    //https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
    public static int optimalBST(int si,int ei,int freq[],int dp[][]){
        if(si==ei){
            return dp[si][ei]=freq[si];
        }
        if(dp[si][ei]!=0) return dp[si][ei];
        int sum=0,min=(int)1e8;
        for(int cut=si;cut<=ei;cut++){
            sum+=freq[cut];
            int leftTree=(cut-1>=si)?optimalBST(si,cut-1,freq,dp):0;
            int rightTree=(cut+1<=ei)?optimalBST(cut+1,ei,freq,dp):0;
            int myans=leftTree+rightTree;
            min=Math.min(min,myans);
        }
        return dp[si][ei]=min+sum;
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
        // int n=5,k=2;
        // int dp[][]=new int[n+1][k+1];
        // System.out.println(partitionKsubSet_tab(n,k,dp));
        int freq[] = {34, 8, 50};
        int n=freq.length;
        int dp[][]=new int[n][n];
        System.out.println(optimalBST(0,n-1,freq,dp));
        print2D(dp);
    }
    public static void main(String[] args) {
        solve();
    }
}