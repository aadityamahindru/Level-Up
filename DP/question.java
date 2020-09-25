//https://practice.geeksforgeeks.org/problems/gold-mine-problem/

class GFG {
	public static void main (String[] args) {
		//code
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
		    int n=sc.nextInt();
		    int m=sc.nextInt();
		    int arr[][]=new int[n][m];
		    for(int i=0;i<n;i++){
		        for(int j=0;j<m;j++){
		            arr[i][j]=sc.nextInt();
		        }
		    }
		    goldmineSolver(arr);
		}
	}
	public static void goldmineSolver(int arr[][]){
	    int n=arr.length;
	    int m=arr[0].length;
	    int dp[][]=new int[n][m];
	    int max=0;
	    for(int i=0;i<n;i++){
	        int ans=goldmine(arr,i,0,dp);
	        if(ans>max)
	        max=ans;
	    }
	    System.out.println(max);
	}
	public static int goldmine(int arr[][],int row,int col,int dp[][]){
	    if(col==arr[0].length-1){
	        return dp[row][col]=arr[row][col];
	    }
	    if(dp[row][col]!=0) return dp[row][col];
	    int v1=-(int)1e8,v2=-(int)1e8,v3=-(int)1e8;
	    if(row-1>=0){
	        v1=goldmine(arr,row-1,col+1,dp);
	    }
	    if(row+1<arr.length){
	        v2=goldmine(arr,row+1,col+1,dp);
	    }
	    v3=goldmine(arr,row,col+1,dp);
	    return dp[row][col]=Math.max(v1,Math.max(v2,v3))+arr[row][col];
	}
}

//https://practice.geeksforgeeks.org/problems/friends-pairing-problem/0

class GFG {
	public static void main (String[] args) {
		//code
	    Scanner sc=new Scanner(System.in);
	    int t=sc.nextInt();
	    while(t-->0){
	        int n=sc.nextInt();
	        System.out.println(pair(n,new int[n+1]));
	    }
	}
	public static int pair(int n,int dp[]){
	    if(n<=1) return dp[n]=1;
	    if(dp[n]!=0) return dp[n];
	    int single=pair(n-1,dp);
	    int pairUp=(n-1)*pair(n-2,dp);
	    return dp[n]=single+pairUp;
    }
    // tabulation
    public static int pair_dp(int n,int dp[]){
	    for(int i=0;i<=n;i++){
            if(i<=1){
                dp[i]=1;
                continue;
            }
	        int ans=0;
	        ans+=dp[i-1];
	        ans+=(i-1)*dp[i-2];
	        dp[i]=ans;
        }
        return dp[n];
    }
    //optimized
    public static int pair_dp(int n,int dp[]){
        int a=1,b=1;
	    for(int i=2;i<=n;i++){
            int sum=(i-1)*a + b;
            a=b;
            b=sum;
        }
        return b;
    }
}

//https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad/0   hw