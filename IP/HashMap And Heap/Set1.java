class Set1{
    //leetocde  205
    public boolean isIsomorphic(String s, String t) {
        int freq1[]=new int[256];
        int freq2[]=new int[256];
        for(int i=0;i<s.length();i++){
            freq1[s.charAt(i)]++;
            freq2[t.charAt(i)]++;
        }
        HashMap<Character,Character> map=new HashMap<Character,Character>();
        for(int i=0;i<s.length();i++){
            if(freq1[s.charAt(i)]!=freq2[t.charAt(i)]) return false;
            freq1[s.charAt(i)]--;
            freq2[t.charAt(i)]--;
            if(map.containsKey(s.charAt(i))){
                if(t.charAt(i)!=map.get(s.charAt(i))) return false;
            }else{
                map.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
    }
    public static void main(String[] args) {
        
    }
    public static boolean getPath(Node node)
}