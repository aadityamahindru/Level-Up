class Set1{
    // 739 leetcode
    public int[] dailyTemperatures(int[] arr) {
        int n=arr.length;
        int ans[]=new int[n];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<n;i++){
            while(st.size()>0&&arr[st.peek()]<arr[i]){
                int idx=st.pop();
                ans[idx]=i-idx;
            }
            st.push(i);
        }
        return ans;
    }
    //316 leetcode

    public String removeDuplicateLetters(String s) {
        int lastIdx[]=new int[26];
        for(int i=0;i<s.length();i++){
            lastIdx[s.charAt(i)-'a']=i;
        }
        Stack<Character> st=new Stack<>();
        boolean vis[]=new boolean[26];
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(vis[ch-'a']) continue;
            while(st.size() > 0 && st.peek() > ch && lastIdx[st.peek()-'a'] > i){
                char rmv=st.pop();
                vis[rmv-'a']=false;
            }
            vis[ch-'a']=true;
            st.push(ch);
        }
        StringBuilder sb=new StringBuilder();
        while(st.size()>0) sb.append(st.pop());
        return sb.reverse().toString();
    }

    
    public static void main(String[] args) {
        
    }
}