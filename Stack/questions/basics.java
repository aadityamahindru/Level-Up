import java.util.*;
class basics{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        int arr[]={5,2,6,3,2,1,4,7,6};
        nextSL(arr);
    }
    public static void nextGR(int arr[]){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int ans[]=new int[n];
        Arrays.fill(ans,n);
        for(int i=0;i<n;i++){
            while(st.size()>0&&arr[st.peek()]<arr[i]){
                ans[st.pop()]=i;
            }
            st.push(i);
        }
        for(int i=0;i<arr.length;i++)
        System.out.print(ans[i]+" ");
    }
    public static void nextGL(int arr[]){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int ans[]=new int[n];
        Arrays.fill(ans,-1);
        for(int i=n-1;i>=0;i--){
            while(st.size()>0&&arr[st.peek()]<arr[i]){
                ans[st.pop()]=i;
            }
            st.push(i);
        }
        for(int i=0;i<arr.length;i++)
        System.out.print(ans[i]+" ");
    }
    public static void nextSR(int arr[]){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int ans[]=new int[n];
        Arrays.fill(ans,n);
        for(int i=0;i<n;i++){
            while(st.size()>0&&arr[st.peek()]>arr[i]){
                ans[st.pop()]=i;
            }
            st.push(i);
        }
        for(int i=0;i<arr.length;i++)
        System.out.print(ans[i]+" ");
    }
    public static void nextSL(int arr[]){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int ans[]=new int[n];
        Arrays.fill(ans,-1);
        for(int i=n-1;i>=0;i--){
            while(st.size()>0&&arr[st.peek()]>arr[i]){
                ans[st.pop()]=i;
            }
            st.push(i);
        }
        for(int i=0;i<arr.length;i++)
        System.out.print(ans[i]+" ");
    }
}