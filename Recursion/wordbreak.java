import java.util.*;
class wordbreak{
    public static void main(String[] args) {
        String ques="ilikesamsungandmango";
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
        System.out.println(breaks(ques,0,max,"",words));
    }
    public static int breaks(String ques,int idx,int maxln,String ans,HashSet<String> words){
        if(idx==ques.length()){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx+1;i<=(idx+maxln+1)&&i<=ques.length();i++){
            String str=ques.substring(0,idx);
            if(words.contains(str)){
                System.out.println(str);
                count+=breaks(ques,i,maxln,ans+str+" ",words);
            }
        }
        return count;
    }
}