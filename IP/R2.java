//https://practice.geeksforgeeks.org/problems/rearrange-characters/0
class GFG {
	public static void main (String[] args) {
		//code
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
		    String str=sc.next();
		    System.out.println(rearrange(str));
		}
	}
	static class Pair implements Comparable<Pair>{
	    char ch;
	    int f;
	    Pair(char ch,int f){
	        this.ch=ch;
	        this.f=f;
	    }
	    public int compareTo(Pair o){
	        return o.f-this.f;
	    }
	}
	public static int rearrange(String str){
	    int n=str.length();
	    int fre[]=new int[26];
	    for(int i=0;i<n;i++){
	        char ch=str.charAt(i);
	        fre[ch-'a']++;
	    }
	    PriorityQueue<Pair> pq=new PriorityQueue<>();
	    for(int i=0;i<26;i++){
	        if(fre[i]>0){
	           char ch=(char)(i+'a');
	            pq.add(new Pair(ch,fre[i]));
	        }
	    }
	    Pair prev=new Pair('$',-1);
	    int count=0;
	    while(pq.size()>0){
	        Pair rm=pq.remove();
	        count++;
	        if(prev.f>0)
	            pq.add(prev);
	        rm.f--;
	        prev=rm;
	    }
	    if(count==n) return 1;
	    return 0;
	}
}

// leetcode 79 word search

class Solution {
    public boolean exist(char[][] board, String word) {
        int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};
        int n=board.length;
        int m=board[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(word.charAt(0)==board[i][j]&&dfs(i,j,0,board,word,dir)) 
                    return true;
            }
        }
        return false;
    }
    public boolean dfs(int i,int j,int idx,char board[][], String word, int dir[][]){
        if(word.charAt(idx)!=board[i][j]) return false;
        if(idx==word.length()-1) return true;
        char temp=board[i][j];
        board[i][j]='$';
        boolean res=false;
        for(int d=0;d<4;d++){
            int r=i+dir[d][0];
            int c=j+dir[d][1];
            if(r>=0&&c>=0&&r<board.length&&c<board[0].length&&board[r][c]!='$'){
                res=res||dfs(r,c,idx+1,board,word,dir);
            }
        }
        board[i][j]=temp;
        return res;
    }
}

//https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1

ArrayList<Integer> countDistinct(int arr[], int n, int k)
    {
        // code here 
        ArrayList<Integer> ans=new ArrayList<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<k;i++){
            hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
        }
        ans.add(hm.size());
        int st=0;
        for(int i=k;i<arr.length;i++){
            if(hm.get(arr[st])==1){
                hm.remove(arr[st]);
            }else{
                hm.put(arr[st],hm.get(arr[st])-1);
            }
            st++;
            hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
            ans.add(hm.size());
        }
        return ans;
    }

    //https://practice.geeksforgeeks.org/problems/transform-to-sum-tree/1
    class Tree{
        public void toSumTree(Node root){
             //add code here.
             sumTree(root);
        }
        public int sumTree(Node node){
            if(node==null) return 0;
            int lc=sumTree(node.left);
            int rc=sumTree(node.right);
            int val=node.data;
            node.data=lc+rc;
            return node.data+val;
        }
    }
