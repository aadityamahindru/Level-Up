class permutation{
    public static void main(String[] args) {
        String str="ABCD";
        System.out.println(print(str,""));
    }
    public static int print(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }
        int c=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            String ros=str.substring(0,i)+str.substring(i+1);
            c+=print(ros,ans+ch);
        }
        return c;
    }
}