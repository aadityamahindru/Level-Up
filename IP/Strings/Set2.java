class Set2{
    //https://www.geeksforgeeks.org/lexicographically-first-palindromic-string/
    public static boolean isPalindrmoic(String str,int freq[]){
        for(int i=0;i<str.length();i++) freq[str.charAt(i)]++;
        int count=0;
        for(int i=0;i<freq.length;i++){
            if(freq[i]%2!=0) count++;
        }
        return count<=1;
    }
    public static String lexoPalindromic(String str){
        int freq[]=new int[128];
        if(!isPalindrmoic(str,freq)) return "NOT POSSIBLE";
        char arr[]=new char[str.length()];
        int i=0,j=arr.length-1;
        for(int k=0;k<128;k++){
            if(freq[k]>1){
                while(freq[k]!=0){
                    arr[i++]=(char)(k);
                    arr[j--]=(char)(k);
                    freq[k]-=2;
                }
            }
        }
        if(arr.length%2!=0){
            for(int k=0;k<128;k++){
                if(freq[k]==1){
                    arr[i]=(char)(k);
                    break;
                }
            }
        }
        return new String(arr);   
    }
    // https://www.geeksforgeeks.org/longest-non-palindromic-substring/
    public static int longestNonPalindromic(String str){
        if(str.length()==0) return 0;
        int n=str.length();
        int freq[]=new int[128];
        int i=0;
        for(i=1;i<n;i++){
            if(str.charAt(i)!=str.charAt(i-1)) break;
        }
        if(i==n) return 0;
        return isPalindrmoic(str,freq)?n-1:n;
    }
    // hashedin ques
    public static int countWords(String str, String word){
        int freq1[]=new int[256];
        int freq2[]=new int[256];
        for(int i=0;i<str.length;i++){
            freq1[str.charAt(i)]++;
        }
        for(int i=0;i<word.length;i++){
            freq2[word.charAt(i)]++;
        }
        for(int i=0;i<word.length;i++){
            if(freq1[word.charAt(i)]==0) return 0;

        }
    }
    public static void solve(){
        // System.out.println(lexoPalindromic("malayalam"));
        // System.out.println(longestNonPalindromic("aaditya"));
        String str="this is a test string";
        String word="tsit";
        System.out.println(countWords(str,word));
    }
    public static void main(String[] args) {
        solve();    
    }
}