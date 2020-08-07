class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> smallans=new ArrayList<>();
            permutation(nums,new boolean[nums.length],0,ans,smallans);
        return ans;
    }
    public void permutation(int nums[],boolean vis[],int count,List<List<Integer>> ans,List<Integer> smallans){
       if(count==nums.length){
           List<Integer> bs=new ArrayList<>(smallans);
           ans.add(bs);
           return;
       }
        for(int i=0;i<nums.length;i++){
           if(!vis[i]){
               vis[i]=true;
               smallans.add(nums[i]);
               permutation(nums,vis,count+1,ans,smallans);
               vis[i]=false;
               smallans.remove(smallans.size()-1);
           }
       }
    }
}