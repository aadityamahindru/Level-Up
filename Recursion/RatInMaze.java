//   https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1



// { Driver Code Starts
// Initial Template for Java

import java.util.*;
class Rat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) a[i][j] = sc.nextInt();

            GfG g = new GfG();
            ArrayList<String> res = g.printPath(a, n);
            if (res.size() > 0) {
                for (int i = 0; i < res.size(); i++)
                    System.out.print(res.get(i) + " ");
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }
}
 } Driver Code Ends


// User function Template for Java

// m is the given matrix and n is the order of matrix
class GfG {
    public static ArrayList<String> printPath(int[][] m, int n) {
        // Your code here
        if(n==0||m[n-1][n-1]==0||m[0][0]==0){
            return new ArrayList<>();
        }
            ArrayList<String> ans=ratmaze(m);
            return ans;
        }
    public static ArrayList<String> ratmaze(int arr[][]){
        int dir[][]={{1,0},{0,-1},{0,1},{-1,0}};
        String dirS[]={"D","L","R","U"};
        int n=arr.length;
        ArrayList<String> ans=new ArrayList<String>();
        ratPath(arr,0,0,n-1,n-1,dir,dirS,"",ans);
        return ans;
    }
    public static void ratPath(int arr[][],int sr,int sc,int er,int ec,int dir[][],String dirS[],String psf,ArrayList<String> res){
        if(sr==er&&sc==ec){
            res.add(psf);
            return;
        }
        arr[sr][sc]=0;
        for(int i=0;i<dir.length;i++){
            int r=sr+dir[i][0];
            int c=sc+dir[i][1];
            if(r>=0&&r<=er&&c>=0&&c<=ec&&arr[r][c]==1){
              ratPath(arr,r,c,er,ec,dir,dirS,psf+dirS[i],res);
            }
        }
        arr[sr][sc]=1;
    }
}