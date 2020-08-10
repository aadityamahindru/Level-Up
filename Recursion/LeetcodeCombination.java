// 77 leetcode
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        combination(n,k,1,list,ans);
        return ans;
    }
    public void combination(int n,int k,int idx,List<Integer> list,List<List<Integer>> ans){
            if(k==0){
                List<Integer> bs=new ArrayList<>(list);
                ans.add(bs);
                return;
            }
        for(int i=idx;idx<=n;idx++){
            list.add(idx);
            combination(n,k-1,idx+1,list,ans);
            list.remove(list.size()-1);
        }
    }
}