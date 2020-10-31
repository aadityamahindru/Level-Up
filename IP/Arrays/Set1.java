import java.util.*;
class Set1{

    // leet 152
    public int maxProduct(int[] arr) {
        int n=arr.length;
        if(n==0) return 0;
        long currP=1;
        long oPro=-(int)1e8;
        boolean flag=false;
        for(int i=0;i<n;i++){
            if(arr[i]==0){
                currP=1;
                flag=true;
            }else{
                currP*=arr[i];
                oPro=Math.max(oPro,currP);
            }
        }
        currP=1;
        for(int i=n-1;i>=0;i--){
            if(arr[i]==0){
                currP=1;
                flag=true;
            }else{
                currP*=arr[i];
                oPro=Math.max(oPro,currP);
            }
        }
        if(flag&&oPro<0) return 0;
        return oPro==-(int)1e8?0:(int)oPro;
    }

    // https://practice.geeksforgeeks.org/problems/minimize-the-heights3351/1
    // imp ques
    int getMinDiff(int[] arr, int n, int k) {
        // code here
        if(n<=1) return 0;
        Arrays.sort(arr);
        int ans=arr[n-1]-arr[0];
        int smallP=arr[0]+k;
        int largeP=arr[n-1]-k;
        if(smallP>largeP){
            int temp=smallP;
            smallP=largeP;
            largeP=temp;
        }
        for(int i=1;i<n-1;i++){
            int smallVal=arr[i]-k;
            int largeVal=arr[i]+k;
            if(smallVal>=smallP||largeVal<=largeP) continue;
            
            if(largeP-smallVal<=largeVal-smallP) smallP=smallVal;
            else largeP=largeVal;
        }
        return Math.min(ans,largeP-smallP);
    }
    // 278 leetcode
    public int findDuplicate(int[] arr) {
        if(arr.length==0) return 0;
        int n=arr.length;
        for(int i=0;i<n;i++){
            int idx=arr[i]%n;
            arr[idx-1]+=n;
        }
        int maxIdx=0;
        for(int i=0;i<n;i++){
            if(arr[i]>arr[maxIdx]) maxIdx=i;
        }
        return maxIdx+1;
    }
    // leetcode 56
    public int[][] merge(int[][] intervals) {
        Stack<int[]> st=new Stack<>(); 
        Arrays.sort(intervals,(a,b)->{
           return a[0]-b[0]; 
        });
        for(int i=0;i<intervals.length;i++){
            if(st.size()==0||st.peek()[1]<intervals[i][0]) st.push(intervals[i]);
            else{
                st.peek()[1]=Math.max(st.peek()[1],intervals[i][1]);
            }
        }
        int ans[][]=new int[st.size()][2];
        int idx=st.size()-1;
        while(st.size()>0){
            ans[idx--]=st.pop();
        }
        return ans;
    }
    // 31 leet
    public void nextPermutation(int[] arr) {
        if(arr.length==0) return;
        int n=arr.length;
        int idx=arr.length-2;
        while(idx>=0){
            if(arr[idx]<arr[idx+1]){
                break;
            }
            idx--;
        }
        if(idx>=0){
            int tarIdx=idx+1;
            for(int i=idx+1;i<n;i++){
                if(arr[i]>arr[idx]&&arr[i]<=arr[tarIdx]) tarIdx=i;
            }
            int temp=arr[idx];
            arr[idx]=arr[tarIdx];
            arr[tarIdx]=temp;
            reverse(arr,idx+1,n-1);
        }else{
            reverse(arr,0,n-1);
        }
        
    }
    public void reverse(int arr[],int si,int ei){
        while(si<ei){
            int temp=arr[si];
            arr[si]=arr[ei];
            arr[ei]=temp;
            si++;
            ei--;
        }
    }
    //https://practice.geeksforgeeks.org/problems/count-pairs-with-given-sum5022/1#
    int getPairsCount(int[] arr, int n, int k) {
        // code here
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int val:arr){
            map.put(val,map.getOrDefault(val,0)+1);
        }
        for(int val:arr){
            count+=map.getOrDefault(k-val,0);
            if(k-val==val) count--;
        }
        return count/2;
    }
    //https://practice.geeksforgeeks.org/problems/longest-consecutive-subsequence/0
    	public static int longestSubequence(int arr[],int n){
	    HashSet<Integer> set =new HashSet<>();
	    for(int val:arr) set.add(val);
	    int len=0;
	    for(int val:arr){
	        if(!set.contains(val)) continue;
	        int prev=val-1;
	        int next=val+1;
	        while(set.contains(prev)) set.remove(prev--);
	        while(set.contains(next)) set.remove(next++);
	        len=Math.max(len,next-prev-1);
	    }
	    return len;
    }
    //https://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/
    public static void generalMorreVoting(int arr[],int K){
        int n=arr.length;
        int val[]=new int[K];
        int count[]=new int[K];
        Arrays.fill(val,-1);
        val[0]=arr[0];
        count[0]=1;
        ArrayList<Integer> ans =new ArrayList<>();
        for(int i=1;i<n;i++){
            for(int k=0;k<K;k++){
                if(arr[i]==val[k]){
                    count[k]++;
                    break;
                }else if(count[k]==0){
                    val[k]=arr[i];
                    count[k]=1;
                    break;
                }else{
                    count[k]--;
                }
            }
        }
        Arrays.fill(count,0);
        for(int i=0;i<n;i++){
            for(int k=0;k<K;k++){
                if(arr[i]==val[k]) count[k]++;
            }
        }
        for(int k=0;k<K;k++){
            if(count[k]>n/K) ans.add(val[k]);
            System.out.print(val[k]+"--->"+count[k]+"   ");

        }
        System.out.println(ans);
    }
    //https://practice.geeksforgeeks.org/problems/array-subset-of-another-array/0#
    public static boolean subset(int arr1[],int arr2[]){
	    Arrays.sort(arr1);
	    for(int i=0;i<arr2.length;i++){
	        if(binarySearch(arr1,arr2[i])==-1) return false;
	    }
	    return true;
	}
	public static int binarySearch(int arr[],int ele){
	    int si=0;
	    int ei=arr.length-1;
	    while(si<=ei){
	        int mid=(si+ei)>>1;
	        if(arr[mid]==ele) return mid;
	        else if(arr[mid]<ele) si=mid+1;
	        else ei=mid-1;
	    }
	    return -1;
    }

    // hashedin print path
    public static boolean path(int r,int c,int arr[][],boolean vis[][],int dir[][]){
        if(r==arr.length-1&&c==arr[0].length-1) return true;
        for(int d=0;d<dir.length;d++){
            int x=r+dir[d][0];
            int y=c+dir[d][1];
            if(x>=0&&y>=0&&x<arr.length&&y<arr.length&&arr[x][y]==1&& !vis[x][y]){
                vis[x][y]=true;
                if(path(x,y,arr,vis,dir)) return true;
                vis[x][y]=false;
            }
        }
        return false;
    }
    public static void saveJhon(){
        int arr[][]={{1,0,0,0},{1,1,0,1},{1,1,0,0},{0,1,1,1}};
        int n=arr.length;
        if(arr[0][0]==0||arr[n-1][n-1]==0) return;
        int dir[][]={{0,1},{1,0},{1,1}};
        boolean vis[][]=new boolean[n][n];
        vis[0][0]=true;
        if(path(0,0,arr,vis,dir)){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(vis[i][j]){
                        arr[i][j]=1;
                    }else arr[i][j]=0;
                }
            }
        }else{
            System.out.println("NO");
        }
        for(int a[]:arr){
            for(int val:a){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
    public static int maxMoney(int arr[]){
        int n=arr.length;
        int notRob=0;
        int rob=arr[0];
        for(int i=1;i<n;i++){
            int prevNotRob=notRob;
            notRob=Math.max(prevNotRob,rob);
            rob=prevNotRob+arr[i];
        }   
        return Math.max(notRob,rob);
    }
    public static void main(String[] args) {
        int arr[]= {10,7,12,44,30,25};
        // generalMorreVoting(arr,3);
        System.out.println(maxMoney(arr));
        // saveJhon();
    }
}