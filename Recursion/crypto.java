class crypto{
    public static void main(String[] args) {
        cryptoFn();
    }
    static String a="send";
    static String b="more";
    static String c="money";
    static int charToNumber[]=new int[26];

    public static void cryptoFn(){
        String str=a+b+c;
        int freq[]=new int[26];
        for(int i=0;i<str.length();i++){
            freq[str.charAt(i)-'a']++;
        }
        String nstr="";
        for(int i=0;i<freq.length;i++){
            if(freq[i]>0){
                nstr+=(char)(i+'a');
            }
        }
        System.out.println(cryptoFinder(nstr,0,new boolean[10]));
    }
    public static int stringToNumber(String str){
        int ans=0;
        for(int i=0;i<str.length();i++){
            int d=charToNumber[str.charAt(i)-'a'];
            ans=ans*10+d;
        }
        return ans;
    }
    public static int cryptoFinder(String str,int idx,boolean used[]){
        if(idx==str.length()){
            int x=stringToNumber(a);
            int y=stringToNumber(b);
            int z=stringToNumber(c);
            if(x+y==z&&charToNumber[a.charAt(0)-'a']!=0&&charToNumber[b.charAt(0)-'a']!=0&&charToNumber[c.charAt(0)-'a']!=0){
                System.out.println(" "+x + "\n+" + y + "\n" + "----\n" + z + '\n');
                return 1;
            }
            return 0;
        }
        int c=0;
        char ch=str.charAt(idx);
        for(int no=0;no<=9;no++){
            if(!used[no]){
                used[no]=true;
                charToNumber[ch-'a']=no;
                c+=cryptoFinder(str,idx+1,used);
                charToNumber[ch-'a']=0;
                used[no]=false;
            }
        }
        return c;
    }
}
