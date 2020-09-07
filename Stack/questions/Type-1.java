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

// leetcode 42 

class Solution {
    public int trap(int[] height) {
        if(height.length<=2){
            return 0;
        }
        Stack<Integer> st=new Stack<>();
        int sum=0;
        int larr[]=new int[height.length];
        int rarr[]=new int[height.length];
        for(int i= height.length-1;i>=0;i--){
            while(st.size()>0&&height[st.peek()]<=height[i])
            {
                st.pop();
            }
            if(st.size()==0){
                rarr[i]=height.length;
            }else{
                rarr[i]=st.peek();
            }
            st.push(i);
        }
        st=new Stack<>();
        for(int i=0;i<height.length;i++){
             while(st.size()>0&&height[st.peek()]<height[i])
            {
                st.pop();
            }
            if(st.size()==0){
                larr[i]=-1;
            }else{
                larr[i]=st.peek();
            }
            st.push(i);
        }
        for(int i=0;i<height.length;i++){
            if((larr[i]!=-1)&&(rarr[i]!=height.length)){
                int width=rarr[i]-larr[i]-1;
                int h=Math.min(height[larr[i]],height[rarr[i]])-height[i];
                sum+=h*width;
            }
        }
        return sum;
    }
}

// leetcode 42 optimized

class Solution {
    public int trap(int[] height) {
        Stack<Integer> st=new Stack<>();
        int ans=0;
        for(int i=0;i<height.length;i++){
            while(st.size()>0&&height[st.peek()]<=height[i]){
                int idx=st.pop();
                if(st.size()>0){
                    int h=Math.min(height[st.peek()],height[i])-height[idx];
                    int w=i-st.peek()-1;
                    ans+=h*w;
                }
            }
            st.push(i);
        }
        return ans;
    }
}
// 42 leet most optimal using 2 pointer approach

class Solution {
    public int trap(int[] height) {
        if(height.length==0) return 0;
        int n=height.length;
        int lmax=0,rmax=0;
        int water=0,li=0,ri=n-1;
        while(li<ri){
            lmax=Math.max(lmax,height[li]);
            rmax=Math.max(rmax,height[ri]);
            if(lmax<=rmax) water+=lmax-height[li++];
            else water+=rmax-height[ri--];
        }
        return water;
    }
}

// 155 leetcode

class MinStack {
    /** initialize your data structure here. */
    Stack<Long> st=new Stack<>();
    long msf=0;
    public MinStack() {
        
    }
    
    public void push(int val) {
        long x=val;
        if(st.size()==0){
            msf=x;
            st.push(x);
            return;
        }
        if(x<msf){
            st.push((x-msf)+x);
            msf=x;
        }else{
            st.push(x);
        }
    }
    
    public void pop() {
        if(st.peek()<msf){
            msf=(msf-st.peek())+msf;
        }
        st.pop();
    }
    
    public int top() {
        if(st.peek()<msf) return (int)msf;
        
        return (int)((long)st.peek());
    }
    
    public int getMin() {
        return (int)msf;
    }
}