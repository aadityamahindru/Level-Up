import java.util.*;
class LIS{
    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }


    public static int LIS_rec(int[] arr,int n,int[] dp){
        if(n == 0) return dp[n] = 1; 
        if(dp[n]!=0) return dp[n];
        
        int maxLen = 0;
        for(int i = n-1;i>=0;i--){
            if(arr[i] < arr[n]){
                maxLen = Math.max(maxLen,LIS_rec(arr,i,dp) + 1);
            }
        }
    
        return  dp[n] = maxLen;
       }
    
    public static int LIS_rec(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        int len = 0;
        for(int i = n - 1 ; i >= 0; i--){
            len = Math.max(len,LIS_rec(arr,i,dp));
        }
        
        print(dp);
        return len;
    }

    //left to Right
    public static int[] LIS_DP(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        dp[0] = 1;
        int len = 0;

        for(int i = 1; i < n; i++ ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] < arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;

    }

    //Right to Left
    public static int[] LDS_DP(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        int len = 0;

        for(int i = n-1; i >= 0 ; i-- ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] < arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    public static int LBS(int[] arr){
        int[] LIS = LIS_DP(arr);
        int[] LDS = LDS_DP(arr);

        int maxLen = 0;
        for(int i=0;i<arr.length;i++){
            int len = LIS[i] + LDS[i] - 1;
            maxLen = Math.max(maxLen,len);
        }

        return maxLen;
    }

    // left to right
    public static int[] LDS_DP_02(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        dp[0] = 1;
        int len = 0;

        for(int i = 1; i < n; i++ ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] > arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    //Right to Left
    public static int[] LIS_DP_02(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        int len = 0;

        for(int i = n-1; i >= 0 ; i-- ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] > arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    // https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
    public static int maxSumIS(int arr[], int n)  
	{  
        int ans=0;
	    int dp[]=new int[n];
	    for(int i=0;i<n;i++){
	        dp[i]=arr[i];
	        for(int j=i-1;j>=0;j--){
	            if(arr[j]<arr[i]){
	                dp[i]=Math.max(dp[i],dp[j]+arr[i]);
	            }
	        }
	        ans=Math.max(ans,dp[i]);
	    }
	    return ans;
	}  

    public static int minNoOFDeletion(int[] arr){
        int n = arr.length;
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

    //Leet  de 673
    public int findNumberOfLIS(int[] arr) {
        int n=arr.length;
        int[] dp=new int[n];
        int[] count=new int[n];

        int maxLen=0;
        int maxCount=0;

        for(int i = 0; i < n; i++){
            
            dp[i] = 1;
            count[i] = 1;

            for(int j = i-1; j>=0; j--){
                if(arr[i] > arr[j]){

                    if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[j] + 1 == dp[i]){
                        count[i] += count[j]; 
                    }
                }
            }

            if(dp[i] > maxLen){
                maxLen = dp[i];
                maxCount = count[i];
            }else if(dp[i] == maxLen){
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    // 354
    public static int maxEnvelopes(int[][] arr) {
        if(arr.length==0) return 0;
        
        int n = arr.length;
        Arrays.sort(arr,(a,b)->{
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];  // this - other, Increasing Order
        });

        int[] dp = new int[n];
        int maxLen = 0;
        for(int i = 0;i<n;i++){
            dp[i] = 1;
            for(int j = i - 1;j>=0;j--){
                if(arr[i][1] > arr[j][1]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }

    //413 leetocode
    public int numberOfArithmeticSlices(int[] arr) {
        if(arr.length < 3) return 0;
        
        int ans = 0;
        int count = 0;
        
        for(int i = 1; i < arr.length - 1; i++){
            
            int d1 = arr[i] - arr[i-1];
            int d2 = arr[i+1] - arr[i];
            
            if(d1 == d2) ans += (++count);
            else count = 0;
        }
        
        return ans;  
    }
    
    //1218 leetocde 
    public int longestSubsequence(int[] arr, int d) {
        HashMap<Integer,Integer> map = new HashMap<>();
        
        int maxLen = 0;
        for(int ele : arr){
            map.put(ele,map.getOrDefault(ele-d,0) + 1);
            maxLen = Math.max(map.get(ele),maxLen);
        }
        
        return maxLen;   
    }

    //1027
    public int longestArithSeqLength(int[] A) { 
        int n = A.length;
        HashMap<Integer,Integer>[] dp = new HashMap[n];
        
        int len = 0;
        for(int i = 0;i<n;i++) dp[i] = new HashMap<Integer,Integer>();
        
        for(int i = 0; i < n; i++){    
            for(int j = i - 1; j >= 0; j--){
                int diff = A[i] - A[j];
                
                int currLen = dp[i].getOrDefault(diff,0);
                int newLen = dp[j].getOrDefault(diff,1) + 1;
                
                dp[i].put(diff,Math.max(currLen,newLen));
                
                len = Math.max(len,dp[i].get(diff));
            }
        }
        
        return len;
    }

    //for You ----> Leetcode 446

    // https://www.geeksforgeeks.org/longest-alternating-subsequence/?ref=rp
    static class Pair{
        int inc;
        int dec;
        Pair(int inc,int dec){
            this.inc=inc;
            this.dec=dec;
        }
    }
	public static int longestAlternatingSub(int arr[],int n){
	    Pair dp[]=new Pair[n];
	    for(int i=0;i<n;i++) dp[i]=new Pair(1,1);
	    int len=0;
	    for(int i=0;i<n;i++){
	        for(int j=i-1;j>=0;j--){
	            if(arr[i]<arr[j]) dp[i].dec=Math.max(dp[i].dec,dp[j].inc+1);
	            if(arr[i]>arr[j]) dp[i].inc=Math.max(dp[i].inc,dp[j].dec+1);
	        }
	        len=Math.max(len,Math.max(dp[i].inc,dp[i].dec));
	    }
	    return len;
    }
    
    //For you --> https://www.geeksforgeeks.org/maximum-sum-alternating-subsequence-sum/

    public static int maximumAlternatingSub(int arr[],int n){
        Pair dp[]=new Pair[n];
        int max=0;
        int flag=0;
        for(int i=0;i<n;i++) dp[i]=new Pair(0,0);
        dp[0].inc=arr[0];
        dp[0].dec=arr[0];
        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[i]<arr[j]) {
                    dp[i].dec=Math.max(dp[i].dec,dp[j].inc+arr[i]);
                    flag=1;
                }
                if(arr[i]>arr[j]&&flag==1) dp[i].inc=Math.max(dp[i].inc,dp[j].dec+arr[i]);
            }
            max=Math.max(max,Math.max(dp[i].inc,dp[i].dec));
        }
        return max;
    }



    //  https://www.geeksforgeeks.org/dynamic-programming-building-bridges/             same as russian doll
    public static int BuildingBridges(int[] arr){
        return 0;
    }



    public static void LISset(){

        // int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15,10};
        int arr[]={8, 2, 3, 5, 7, 9, 10};
        int n=arr.length;
        System.out.println(maximumAlternatingSub(arr,n));
    }

       public static void solve(){
           LISset();
       }


       public static void main(String[] args){
           solve();
       }
}