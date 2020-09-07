import java.util.*;
class basics{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        int n=10;
        int dp[]=new int[n+1];
        int arr[]={2,5,8,3};
        LinkedList<Integer> list=new LinkedList<>();
        System.out.println(boardPath_jumps(0,10,arr,list));
        print(dp);
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

}