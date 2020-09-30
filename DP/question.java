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



// leetcode 115;
class Solution {
    public int numDistinct(String s, String t) {
        int n=s.length();
        int m=t.length();
        int dp[][]=new int[n+1][m+1];
        for(int arr[]:dp) Arrays.fill(arr,-1);
        return numDistinct(s,t,n,m,dp);
    }
    public int numDistinct(String s,String t,int n,int m,int dp[][]){
        if(n<m) return 0;
        if(m==0) return 1;
        if(dp[n][m]!=-1) return dp[n][m];
        int a=numDistinct(s,t,n-1,m-1,dp);
        int b=numDistinct(s,t,n-1,m,dp);
        if(s.charAt(n-1)==t.charAt(m-1)) return dp[n][m]=a+b;
        return dp[n][m]=b;
    }
}
// tabulation

public int numDistinct(String s, String t) {
	int n=s.length();
	int m=t.length();
	int dp[][]=new int[n+1][m+1];
	return numDistinct_DP(s,t,n,m,dp);
}
public static int numDistinct_DP(String s,String t,int x,int y,int dp[][]){
	for(int n=0;n<=x;n++){
		for(int m=0;m<=y;m++){
			if(n<m){
				dp[n][m]=0;
				continue;
			}
			if(m==0) {
				dp[n][m]=1;
				continue;
			}
			int a=dp[n-1][m-1]; //numDistinct(s,t,n-1,m-1,dp);
			int b=dp[n-1][m]; //numDistinct(s,t,n-1,m,dp);
			if(s.charAt(n-1)==t.charAt(m-1))dp[n][m]=a+b;
			else dp[n][m]=b;
		}
	}
	return dp[x][y];
}

// leetcode 940

public int distinctSubseqII(String S) {
	int n=S.length();
	int dp[]=new int[n+1];
	dp[0]=1;
	int mod=(int)1e9+7;
	int locc[]=new int[26];
	Arrays.fill(locc,-1);
	for(int i=1;i<=n;i++){
		char ch=S.charAt(i-1);
		dp[i]=(2*dp[i-1])%mod;
		if(locc[ch-'a']!=-1){
			dp[i]=(dp[i]%mod-dp[locc[ch-'a']-1]%mod+mod)%mod;
		}
		locc[ch-'a']=i;
	}
	return dp[n]-1;
}

//https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck/0
public static void main (String[] args) {
	//code
	Scanner sc=new Scanner(System.in);
	int t=sc.nextInt();
	for(int i=0;i<t;i++){
		String str=sc.next();
	int dp[][]=new int[3][str.length()];
	if(str.charAt(0)=='a'){
		dp[0][0]=1;
	}
	for(int j=1;j<str.length();j++){
		char ch=str.charAt(j);
		if(ch=='a'){
			dp[0][j]=1+(2*dp[0][j-1]);
		}else{
			dp[0][j]=dp[0][j-1];
		}
		if(ch=='b'){
			dp[1][j]=dp[0][j]+(2*dp[1][j-1]);
		}else{
			dp[1][j]=dp[1][j-1];
		}
		if(ch=='c'){
			dp[2][j]=dp[1][j]+(2*dp[2][j-1]);
		}else{
			dp[2][j]=dp[2][j-1];
		}
	}
	System.out.println(dp[2][str.length()-1]);
	}

	// lcs three strings
	//https://practice.geeksforgeeks.org/problems/lcs-of-three-strings/0

	public static void main (String[] args) {
		//code
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
		    int n=sc.nextInt();
		    int m=sc.nextInt();
		    int k=sc.nextInt();
		    String s1=sc.next();
		    String s2=sc.next();
		    String s3=sc.next();
		    int dp[][][]=new int[n+1][m+1][k+1];
		    for(int arr[][]:dp){
		        for(int a[]:arr){
		            Arrays.fill(a,-1);
		        }
		    }
		    System.out.println(lcs(s1,s2,s3,n,m,k,dp));
		}
	}
	public static int lcs(String s1,String s2,String s3,int n,int m,int k,int dp[][][]){
	    if(n==0||m==0||k==0){
	        return 0;
	    }
	    if(dp[n][m][k]!=-1)return dp[n][m][k];
	    if(s1.charAt(n-1)==s2.charAt(m-1)&&s1.charAt(n-1)==s3.charAt(k-1)){
	        return dp[n][m][k]=lcs(s1,s2,s3,n-1,m-1,k-1,dp)+1;
	    }else{
	        int x=lcs(s1,s2,s3,n-1,m,k,dp);
	        int y=lcs(s1,s2,s3,n,m-1,k,dp);
	        int z=lcs(s1,s2,s3,n,m,k-1,dp);
	        return dp[n][m][k]=Math.max(x,Math.max(y,z));
	    }
	}

	//tabulation
	public static int lcs(String s1,String s2,String s3,int n,int m,int k,int dp[][][]){
	    int N=n,M=m,K=k;
	    for(n=0;n<=N;n++){
	        for(m=0;m<=M;m++){
	            for(k=0;k<=K;k++){
	                if(n==0||m==0||k==0){
	                       dp[n][m][k]= 0;
	                       continue;
	                }
	                if(s1.charAt(n-1)==s2.charAt(m-1)&&s1.charAt(n-1)==s3.charAt(k-1)){
	                    dp[n][m][k]=dp[n-1][m-1][k-1]+1;
	                }else{
	                    int x=dp[n-1][m][k];
	                    int y=dp[n][m-1][k];
	                    int z=dp[n][m][k-1];
	                    dp[n][m][k]=Math.max(x,Math.max(y,z));
	                }
	            }
	        }
	    }
	    return dp[N][M][K];
	}
	//leetcode 72
	public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int dp[][]=new int[n+1][m+1];
        for(int arr[]:dp) Arrays.fill(arr,-1);
        return editDistance(word1,word2,n,m,dp);
    }
    int editDistance(String word1, String word2,int n,int m,int[][] dp) {
        if(n==0 || m==0){
            return n!=0?n:m;
        }
    
        if(dp[n][m]!=-1) return dp[n][m];
    
        if(word1.charAt(n-1)==word2.charAt(m-1)) 
            return dp[n][m]=editDistance(word1,word2,n-1,m-1,dp);
        else{
            int insert_  = editDistance(word1,word2,n,m-1,dp);
            int delete_  = editDistance(word1,word2,n-1,m,dp);
            int replace_ =editDistance(word1,word2,n-1,m-1,dp);
        
            return dp[n][m]=Math.min(Math.min(insert_,replace_),delete_)+1;
        }
	}
	// leetcode 44
	public boolean isMatch(String s, String p) {
        p=removeStar(p);
        int n=s.length();
        int m=p.length();
        int dp[][]=new int[n+1][m+1];
        for(int arr[]:dp) Arrays.fill(arr,-1);
        return isMatch(s,p,n,m,dp)==1;
    }
    public int isMatch(String s,String p,int n,int m,int dp[][]){
        if(n==0&&m==0) return 1;
        if(n==0||m==0){
            if(m==1&&n==0&&p.charAt(m-1)=='*') return 1;
            return 0;
        }
        if(dp[n][m]!=-1) return dp[n][m];
        char ch1=s.charAt(n-1);
        char ch2=p.charAt(m-1);
        int val=-1;
        if(ch1==ch2||ch2=='?'){
            val=isMatch(s,p,n-1,m-1,dp);
        }else if(ch2=='*'){
            boolean res=false;
            res=res||isMatch(s,p,n-1,m,dp)==1;
            res=res||isMatch(s,p,n,m-1,dp)==1;
            val=res?1:0;
        }else{
            val=0;
        }
        return dp[n][m]=val;
    }
    public String removeStar(String p){
        if(p.length()==0) return "";
        StringBuilder sb=new StringBuilder();
        sb.append(p.charAt(0));
        int i=1;
        while(i<p.length()){
            while(i<p.length()&&p.charAt(i)==p.charAt(i-1)&&p.charAt(i)=='*') i++;
            if(i<p.length()) sb.append(p.charAt(i));
            i++;
        }
        return sb.toString();
	}
	// tabulation
	public int isMatch(String s,String p,int n,int m,int dp[][]){
		int N=n,M=m;
		 for(n=0;n<=N;n++){
			 for(m=0;m<=M;m++){
				 if(n==0&&m==0){
					 dp[n][m]=1;
					 continue;
				 }
				 if(n==0||m==0){
					 if(m==1&&n==0&&p.charAt(m-1)=='*'){
						 dp[n][m]=1;
					 }else
						dp[n][m]=0;
					 continue;
				 }
				 char ch1=s.charAt(n-1);
				 char ch2=p.charAt(m-1);
				 int val=-1;
				 if(ch1==ch2||ch2=='?'){
					 val=dp[n-1][m-1];
				 }else if(ch2=='*'){
					 boolean res=false;
					 res=res||dp[n-1][m]==1;
					 res=res||dp[n][m-1]==1;
					 val=res?1:0;
				 }else{
					 val=0;
				 }
				 dp[n][m]=val;
			 }
		 }
		 return dp[N][M];
	 }