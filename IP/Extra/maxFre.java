import java.util.*;

public class Main {

    public static void main(String[] args) {

        // write your code here.
        String S1 = "geeks is best place to learn bad account abbount";
        String S2 = "bad place";
        count(S1, S2);
    }
    public static void count(String s1,String s2){
        HashMap<String,Integer> hm=new HashMap<>();
        String arr1[]=s1.split(" ");
        String arr2[]=s2.split(" ");
        for(int i=0;i<arr1.length;i++){
            if(hm.containsKey(arr1[i])){
                int ov=hm.get(arr1[i]);
                hm.put(arr1[i],ov+1);
            }else{
                hm.put(arr1[i],1);
            }
        }
        String s="";
        for(int i=0;i<arr2.length;i++){
            if(hm.containsKey(arr2[i]))
                hm.remove(arr2[i]);
        }
        int max=0;
        for(String key:hm.keySet()){
            int val=hm.get(key);
            if(val>max||(val==max&&key.compareTo(s)<0)){
                max=val;
                s=key;
            }
        }
        System.out.print(s);
    }
}