class wordbreak{
    public static void main(String[] args) {
    String str[]={ "mobile", "samsung", "sam", 
    "sung", "man", "mango", 
    "icecream", "and", "go", 
    "i", "like", "ice", "cream" }; 
        HashSet<String> words=new HashSet<>();
        int max=0;
        for(String s:str){
            max=Math.max(max,s.length());
            words.add(s);
        }
    }

}