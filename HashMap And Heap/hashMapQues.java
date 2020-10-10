class hashMapQues{
    //349
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> arr=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
           set.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++){
            if(set.contains(nums2[i])){
                arr.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }
        int ans[]=new int[arr.size()];
        for(int i=0;i<ans.length;i++){
            ans[i]=arr.get(i);
        }
        return ans;
    }

    //350
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> arr=new ArrayList<>();
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums1.length;i++){
           map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        for(int i=0;i<nums2.length;i++){
           if(map.containsKey(nums2[i])&&map.get(nums2[i])>0){
               arr.add(nums2[i]);
               map.put(nums2[i],map.get(nums2[i])-1);
           }
        }
        int ans[]=new int[arr.size()];
        for(int i=0;i<ans.length;i++){
            ans[i]=arr.get(i);
        }
        return ans;
    }

    //128 leet
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs=new HashSet<>();
        for(int val: nums) hs.add(val);
        int len=0;
        for(int val:nums){
            if(!hs.contains(val)) continue;
            int prev=val-1;
            int next=val+1;
            hs.remove(val);
            while(hs.contains(prev)) hs.remove(prev--);
            while(hs.contains(next)) hs.remove(next++);
            len=Math.max(len,next-prev-1);
        }
        return len;
    }

    // 49 leet
    public List<List<String>> groupAnagrams(String[] words) {
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            String key=calculateKey(words[i]);
            map.putIfAbsent(key,new ArrayList<String>());
            map.get(key).add(words[i]);
        }
        List<List<String>> ans=new ArrayList<>();
        for(String key:map.keySet()){
            ans.add(map.get(key));
        }
        return ans;
    }
    public String calculateKey(String str){
        StringBuilder sb=new StringBuilder();
        int freq[]=new int[26];
        for(int i=0;i<str.length();i++){
            freq[str.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            while(freq[i]-->0) sb.append((char)(i+'a'));
        }
        return sb.toString();
    }

    // 347
    class Pair implements Comparable<Pair>{
        int key;
        int val;
        Pair(int key,int val){
            this.key=key;
            this.val=val;
        }
        public int compareTo(Pair other){
           return other.val-this.val;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
       int ans[]=new int[k];
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        for(int key:hm.keySet()){
            pq.add(new Pair(key,hm.get(key)));
        }
        for(int i=0;i<k;i++){
            Pair max=pq.remove();
            ans[i]=max.key;
        }
        return ans;
    }
}