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
    //https://practice.geeksforgeeks.org/problems/check-arithmetic-progression/0
    public static void checkAP(int arr[],int n){
	    PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
	        return b-a;
	    });
	    HashSet<Integer> hs=new HashSet<>();
	    for(int val:arr){
	        pq.add(val);
	        if(pq.size()>2) pq.poll();
	        hs.add(val);
	    }
	    int a=pq.poll();
	    int b=pq.poll();
	    int d=a-b;
	    int ele=a+d;
	    int count=2;
	    while(hs.contains(ele)){
	        hs.remove(ele);
	        ele+=d;
	        count++;
	    }
	    System.out.println(count==n?"YES":"NO");
    }
    
    // 380 leet
    class RandomizedSet {
        ArrayList<Integer> arr;
        HashMap<Integer,Integer> map;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            arr=new ArrayList<>();
            map=new HashMap<>();
        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)) return false;
            arr.add(val);
            map.put(val,arr.size()-1);
            return true;
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(arr.size()==0|| !map.containsKey(val)) return false;
            int index=map.get(val);
            int lastVal=arr.get(arr.size()-1);
            arr.set(index,lastVal);
            map.put(lastVal,index);
            arr.remove(arr.size()-1);
            map.remove(val);
            return true;
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            Random rand = new Random();
            int index = rand.nextInt(arr.size());
            return arr.get(index);
        }
    }

    // 895 very good ques
    class FreqStack {
        HashMap<Integer,Integer> freq;
        int maxFreq;
        HashMap<Integer,Stack<Integer>> stack;
        public FreqStack() {
            freq=new HashMap<>();
            stack=new HashMap<>();
            maxFreq=0;
        }
        
        public void push(int x) {
            freq.put(x,freq.getOrDefault(x,0)+1);
            maxFreq=Math.max(maxFreq,freq.get(x));
            stack.putIfAbsent(maxFreq,new Stack<Integer>());
            stack.get(freq.get(x)).push(x);
        }
        
        public int pop() {
            int rv=stack.get(maxFreq).pop();
            freq.put(rv,maxFreq-1);
            if(stack.get(maxFreq).size()==0){
                stack.remove(maxFreq);
                maxFreq--;
            }
            return rv;
        }
    }
    //https://practice.geeksforgeeks.org/problems/check-frequencies4211/1
    boolean sameFreq(String s) {
        // code here
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        HashMap<Integer,Integer> freq =new HashMap<>();
        for(char key:map.keySet()){
            int fre=map.get(key);
            freq.put(fre,freq.getOrDefault(fre,0)+1);
        }
        if(freq.size()>2) return false;
        if(freq.size()<=1) return true;
        else{
            int arr[]=new int[2];
            int i=0;
            for(int key:freq.keySet()){
                arr[i++]=key;
            }
            if(Math.abs(arr[1]-arr[0])==1) return true;
            else if(arr[0]==1||arr[1]==1) return true;
            else return false;
        }
    }
    //781 leet
    public int numRabbits(int[] arr) {
        if(arr.length==0) return 0;
        int ans=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int ele : arr){
            map.put(ele,map.getOrDefault(ele,0)+1);
            if(map.get(ele)==1) ans+=ele +1;
            if(map.get(ele)==ele+1) map.remove(ele);
        }
        return ans;
    }

    // 560 leet
    public int subarraySum(int[] nums, int k) {
        if(nums.length==0) return 0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int count=0,sum=0;
        for(int ele:nums){
            sum+=ele;
            if(map.containsKey(sum-k)) count +=map.get(sum-k);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }
    // 974 leet
    public int subarraysDivByK(int[] nums, int k) {
        if(nums.length==0) return 0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int count=0,sum=0;
        for(int ele:nums){
            sum+=ele;
            int val=(sum % k + k)%k;
            if(map.containsKey(val)) count +=map.get(val);
            map.put(val,map.getOrDefault(val,0)+1);
        }
        return count;
    }
    //https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s/0

    public static int equal01(int arr[],int n){
	    if(n==0) return 0;
	    HashMap<Integer,Integer> map=new HashMap<>();
	    map.put(0,1);
	    int count=0,sum=0;
	    for(int ele: arr){
	        sum+=ele==1?1:-1;
	        if(map.containsKey(sum)) count+=map.get(sum);
	        map.put(sum,map.getOrDefault(sum,0)+1);
	    }
	    return count;
    }
    
    //https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k/0
    public static int longestSubArray(int arr[],int n, int k){
	    if(n==0) return 0;
	    HashMap<Integer,Integer> map=new HashMap<>();
	    map.put(0,-1);
	    int sum=0,max=0;
	    for(int i=0;i<n;i++){
	        sum+=arr[i];
	        if(map.containsKey(sum-k)) max=Math.max(max,i-map.get(sum-k));
	        if(!map.containsKey(sum)) map.put(sum,i);
	    }
	    return max;
    }
    //https://www.geeksforgeeks.org/longest-subarray-sum-divisible-k/
    int longSubarrWthSumDivByK(int nums[], int n, int k)
    {
        if(nums.length==0) return 0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int max=0,sum=0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
            int val=(sum % k + k)%k;
            if(map.containsKey(val)) max=Math.max(max,i-map.get(val));
            else map.put(val,i);
        }
        return max;
    }
    //https://www.geeksforgeeks.org/substring-equal-number-0-1-2/


    // leetcode 3
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<=1) return s.length();
        int n=s.length();
        int freq[]=new int[256];
        int si=0,ei=0,len=0,count=0;
        while(ei<n){
            if(freq[s.charAt(ei)]++ > 0) count++;
            while(count > 0){
                if(freq[s.charAt(si++)]-- > 1) count--;
            }
            len=Math.max(len,ei-si+1);
            ei++;
        }
        return len;
    }
    //https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1
    int maxLen(int[] arr, int n)
    {
        if(n==0) return 0;
	    HashMap<Integer,Integer> map=new HashMap<>();
	    map.put(0,-1);
	    int max=0,sum=0;
	    for(int i=0;i<n;i++){
	        sum+=arr[i]==1?1:-1;
	        if(map.containsKey(sum)) max=Math.max(max,i-map.get(sum));
	        else map.put(sum,i);
	    }
	    return max;
    }
    // 525 leetcode
    public int findMaxLength(int[] arr) {
        int n=arr.length;
        if(n==0) return 0;
	    HashMap<Integer,Integer> map=new HashMap<>();
	    map.put(0,-1);
	    int max=0,sum=0;
	    for(int i=0;i<n;i++){
	        sum+=arr[i]==1?1:-1;
	        if(map.containsKey(sum)) max=Math.max(max,i-map.get(sum));
	        else map.put(sum,i);
	    }
	    return max;
    }


    //https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
    public int numSubarraysWithSum(int[] nums, int S) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int count = 0;
        int sum = 0;
             
        for(int i=0;i<nums.length;i++){
            sum  += nums[i];
            int val = sum - S;
            if(map.containsKey(val)) count += map.get(val);   
            map.put(sum,map.getOrDefault(sum,0) + 1);
        }
         
         return count;
     }
     //https://www.geeksforgeeks.org/number-subarrays-sum-less-k/
     public static void numOfSubarraysSumLessK(int[] arr,int k){
        int si = 0,ei=0;
        int n = arr.length;

        int count = 0;
        int sum = 0;
        while(ei < n){
            sum += arr[ei++];
            while(sum > k && si < ei){
                sum -= arr[si++]; 
            }

            count += (ei - si);
        }

        return count;
    }

    // 239 leetcode


    // 76 leetcode

}