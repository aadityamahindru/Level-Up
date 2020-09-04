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



//1249 leetcode
class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st=new Stack<>();
        boolean isValid[]=new boolean[s.length()];
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='(') st.push(i);
            else if(ch==')'){
                if(st.size()>0&&s.charAt(st.peek())=='('){
                    int j=st.pop();
                    isValid[j]=true;
                    isValid[i]=true;
                }
            }else{
                isValid[i]=true;
            }
        }
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(isValid[i]) sb.append(ch);
        }
        return sb.toString();
    }
}


//735 leetcode

class Solution {
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0) st.push(arr[i]);
            else{
                while(st.size()>0&&st.peek()>0&& st.peek()< -arr[i]) st.pop();
                if(st.size()>0&&st.peek() == -arr[i]) st.pop();
                else if(st.size()==0||st.peek()<0) st.push(arr[i]);
            }
        }
        int ans[]=new int[st.size()];
        int i=st.size()-1;
        while(st.size()!=0){
            ans[i--]=st.pop();
        }
        return ans;
    }
    
}
// leetcode 84

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        int ans=0;
        for(int i=0;i<heights.length;i++){
            while(st.size()>1&&heights[st.peek()]>=heights[i]){
                int h=heights[st.pop()];
                int w=i-st.peek()-1;
                ans=Math.max(ans,h*w);
            }
            st.push(i);
        }
        while(st.size()>1){
            int h=heights[st.pop()];
            int w=heights.length-st.peek()-1;
            ans=Math.max(ans,h*w);
        }
        return ans;
    }
}


//85 leetcode

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int n=matrix.length;
        int m=matrix[0].length;
        int height[]=new int[m];
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                height[j]= matrix[i][j]=='1'?height[j]+1:0;
            }
            ans=Math.max(ans,largestRectangleArea(height));
        }
        return ans;
    }
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        int ans=0;
        for(int i=0;i<heights.length;i++){
            while(st.size()>1&&heights[st.peek()]>=heights[i]){
                int h=heights[st.pop()];
                int w=i-st.peek()-1;
                ans=Math.max(ans,h*w);
            }
            st.push(i);
        }
        while(st.size()>1){
            int h=heights[st.pop()];
            int w=heights.length-st.peek()-1;
            ans=Math.max(ans,h*w);
        }
        return ans;
    }
}