import java.util.*;
class Set1{
    // ip list Q1-10
    //https://www.geeksforgeeks.org/print-words-together-set-characters/
    public static int calculateKey(String str){
        int key=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            key+=(ch-'a')+1;
        }
        return key;
    }
    public static void groupSameTogeter_(String words[]){
        HashMap<Integer,ArrayList<String>> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            int key=calculateKey(words[i]);
            map.putIfAbsent(key,new ArrayList<String>());
            map.get(key).add(words[i]);
        }
        for(int key:map.keySet()){
            System.out.println(map.get(key));
        }
    }
    public static void groupSameTogeter(){
        String words[] = { "may", "student", "students", "dog", 
        "studentssess", "god", "cat", "act", "tab", 
        "bat", "flow", "wolf", "lambs", "amy", "yam", 
        "balms", "looped", "poodle"}; 
        groupSameTogeter_(words);
    }
    // https://practice.geeksforgeeks.org/problems/generate-binary-string/0
    public static void generateBinary(String str, int idx,String asf){
	    if(idx==str.length()){
	        System.out.print(asf+" ");
	        return;
	    }
	    char ch=str.charAt(idx);
	    if(ch=='?'){
	        generateBinary(str,idx+1,asf+'0');
	        generateBinary(str,idx+1,asf+'1');
	    }else{
	        generateBinary(str,idx+1,asf+ch);
	    }
    }
    //https://www.geeksforgeeks.org/smallest-window-contains-characters-string/
    public static void smallestWindow(String str){
	    boolean dist[]=new boolean[26];
	    int distAlpha=0;
	    for(int i=0;i<str.length();i++){
	        char ch=str.charAt(i);
	        if(!dist[ch-'a']){
	            dist[ch-'a']=true;
	            distAlpha++;
	        }
	    }
	    int freq[]=new int[26];
	    int minLen=(int)1e8;
	    int st=0,count=0;
	    for(int ed=0;ed<str.length();ed++){
	        char ch=str.charAt(ed);
	        freq[ch-'a']++;
	        if(freq[ch-'a']==1){
	            count++;
	        }
	        if(count==distAlpha){
	            while(freq[str.charAt(st)-'a']>1){
	                freq[str.charAt(st)-'a']--;
	                st++;
	            }
	            minLen=Math.min(minLen,ed-st+1);
	        }
	    }
	    System.out.println(minLen);
    }
    // https://www.geeksforgeeks.org/count-ways-increase-lcs-length-two-strings-one/   to be done

    //https://www.geeksforgeeks.org/anagram-substring-search-search-permutations/

    //https://www.geeksforgeeks.org/check-two-strings-k-anagrams-not/
    boolean areKAnagrams(String s1, String s2, int k){
      //add code here.
        if(s1.length()!=s2.length()) return false;
        int freq[]=new int[26];
        for(int i=0;i<s1.length();i++){
            char ch=s1.charAt(i);
            freq[ch-'a']++;
        }
        int missMatch=0;
        for(int i=0;i<s2.length();i++){
            char ch=s2.charAt(i);
            if(freq[ch-'a']>0) freq[ch-'a']--;
            else missMatch++;
        }
        return missMatch<=k;
     }
     //https://www.geeksforgeeks.org/check-binary-representations-two-numbers-anagram/
     public static int getOnes(int num){
         int count=0;
         while(num>0){
            count+=(num&1);
            num=(num>>1);
         }
         return count;
     }
     public static void checkBinaryAnagram(int num1,int num2){
         int ones1=getOnes(num1);
         int ones2=getOnes(num2);
         System.out.println(ones1==ones2);
     }
     // https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string/0
     public static void lpss(String str){
        boolean dp[][] = new boolean[str.length()][str.length()];
        int st=0,ed=0;
        int max=-1;
        for(int dia=0;dia<str.length();dia++){
            for(int i=0,j=dia;j<str.length();i++,j++){
                if(dia==0){
                    dp[i][j] = true;
                }else if(dia==1){
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = true;
                    }
                }else{
                    if(str.charAt(i) == str.charAt(j) && dp[i+1][j-1] == true){
                        dp[i][j] = true;
                    }
                }
                if(dia>max&&dp[i][j]==true){
                    st=i;
                    ed=j;
                    max=dia;
                }
            }
        }
        System.out.println(str.substring(st,ed+1));
    }
    //https://practice.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string/0
    public static String smallestWindow2(String s1,String s2){
	    if(s1.length()<s2.length()) return "-1";
	    int freqS2[]=new int[26];
	    for(int i=0;i<s2.length();i++){
	        freqS2[s2.charAt(i)-'a']++;
	    }
	    int freqS1[]=new int[26];
	    int count=0;
	    int sidx=0,st=0,eidx=0;
	    int min=(int)1e8;
	    for(int j=0;j<s1.length();j++){
	        char ch=s1.charAt(j);
	        freqS1[ch-'a']++;
	        if(freqS1[ch-'a']!=0&&freqS1[ch-'a']<=freqS2[ch-'a']){
	            count++;
	        }
	        if(count==s2.length()){
	            while(freqS1[s1.charAt(st)-'a']>freqS2[s1.charAt(st)-'a']||freqS1[s1.charAt(st)-'a']==0){
	                freqS1[s1.charAt(st)-'a']-=freqS1[s1.charAt(st)-'a']==0?0:1;
	                st++;
	            }
	            if((j-st+1)<min){
	                min=j-st+1;
	                sidx=st;
	                eidx=j;
	            }
	        }
	    }
	    if(min==(int)1e8){
	        return "-1";
	    } 
	    return s1.substring(sidx,eidx+1);
    }
    

    public static void solve(){
        // groupSameTogeter();
        checkBinaryAnagram(5,4);
    }
    public static void main(String[] args) {
        solve();
    }
}