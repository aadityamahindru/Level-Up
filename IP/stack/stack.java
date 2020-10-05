import java.util.*;
class stack{
    public static void middle(){
        middleStack st=new middleStack();
        for(int i=1;i<=5;i++) st.push(i*10);
        System.out.println(st);
        st.push(10);
        System.out.println(st.findMiddle());
        System.out.println(st.removeMiddle());
        System.out.println(st.findMiddle());
        System.out.println(st.pop());
    }

    // reverse stack
    //https://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
    public static void insert_bottom(int val,Stack<Integer> st){
        if(st.size()==0){
            st.push(val);
            return;
        }
        int x=st.pop();
        insert_bottom(val,st);
        st.push(x);
    }
    public static void reverseStack(Stack<Integer> st){
        if(st.size()==0) return;
        int val=st.pop();
        reverseStack(st);
        insert_bottom(val,st);
    }
    public static void solve(){
    //    middle();
        Stack<Integer> st=new Stack<>();
        for(int i=1;i<=5;i++) st.push(i*10);
        System.out.println(st);
        reverseStack(st);
        System.out.println(st);
    }
    public static void main(String[] args) {
        solve();
    }
}