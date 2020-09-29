import java.util.*;
class revision{
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
    public static int boardPath(int si,int ei){
        if(si==ei) return 1;
        int count=0;
        for(int jump=1;jump<=6&&si+jump<=ei;jump++){
            count+=boardPath(si+jump,ei);
        }
        return count;
    }

    //using max size 6 of ll
    public static int boardPath_opti(int si,int ei,LinkedList<Integer> ll){
        for(si=ei;si>=0;si--){
            if(si>=ei-1){
                ll.addFirst(1);
                continue;
            }
            if(ll.size()<=6) ll.addFirst(2*ll.getFirst());
            else{
                int val=ll.removeLast();
                ll.addFirst(2*ll.getFirst()-val);
            }
        }
        return ll.getFirst();
    }

    
    public static void solve(){
        int n=10;
        int dp[]=new int[n+1];
        System.out.println(boardPath_opti(0,n,new LinkedList<Integer>()));
    }
    public static void main(String[] args) {
        solve();
    }
}