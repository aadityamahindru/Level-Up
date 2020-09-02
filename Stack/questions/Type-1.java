// leetcode 20
class Solution {
    public boolean isValid(String s) {
        Stack<Character> st=new Stack<>();
       for(int i=0;i<s.length();i++){
           char ch=s.charAt(i);
          if(ch=='{'||ch=='('||ch=='[') st.push(ch);
           else{
               if(st.size()==0) return false;
               else if(ch==')'&&st.peek()!='(') return false;
               else if(ch=='}'&&st.peek()!='{') return false;
               else if(ch==']'&&st.peek()!='[') return false;
               else st.pop();
           }
       } 
        return st.size()==0;
    }
}

// leetcode 1021
class Solution {
    public String removeOuterParentheses(String S) {
        StringBuilder sb=new StringBuilder();
        int count=0;
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)=='('&&count++>0) sb.append('(');
            else if(S.charAt(i)==')'&&count-->1) sb.append(')');
        }
        return sb.toString();
    }
}

//leetcode 503

class Solution {
    public int[] nextGreaterElements(int[] arr) {
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int ans[]=new int[n];
        Arrays.fill(ans,-1);
        for(int idx=0;idx<2*n;idx++){
            int i=idx%n;
            while(st.size()>0&&arr[st.peek()]<arr[i]){
                ans[st.pop()]=arr[i];
            }
            if(i<n)
            st.push(i);
        }
        return ans;
    }
}


// leetcode 921
public int minAddToMakeValid(String str) {
    int count=0;
    Stack<Character> st=new Stack<>();
    for(int i=0;i<str.length();i++){
        if(str.charAt(i)=='('){
            count++;
            st.push('(');
        } 
        else{
           if(st.size()>0&&st.peek()=='('){
               st.pop();
               count--;
           } else{
               count++;
           }
        } 
    }
    return count;
}

// leetcode 32

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st=new Stack<>();
        int max=0;
        st.push(-1);
       for(int i=0;i<s.length();i++){
           char ch=s.charAt(i);
           if(st.peek()!=-1&&s.charAt(st.peek())=='('&&s.charAt(i)==')'){
               st.pop();
               max=Math.max(max,i-st.peek());
           }else st.push(i);
         
       } 
        return max;
    }
}

