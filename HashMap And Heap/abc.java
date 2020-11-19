class abc{
    public static void main(String[] args) {
        String str="abcdf20bcfgh23basch1";
        int idx=0;
        int sum=0;
        String temp = "0"; 
        for (int i = 0; i < str.length(); i++) { 
            char ch = str.charAt(i); 
            if (Character.isDigit(ch)) 
                temp += ch; 
            else { 
                sum += Integer.parseInt(temp); 
                temp = "0"; 
            } 
        } 
        System.out.println(sum + Integer.parseInt(temp));
        
        String s1="geee#e#ks";
        String s2="gee##eeks";
        // findAndPrintUncommonChars(s1,s2);
        findNoWith1(4);
    }
    static int MAX_CHAR = 26; 
    static void findAndPrintUncommonChars(String str1, String str2){ 
        int present[] = new int[MAX_CHAR]; 
        for (int i = 0; i < MAX_CHAR; i++)  
        { 
            present[i] = 0; 
        } 
  
        int l1 = str1.length(); 
        int l2 = str2.length(); 

        for (int i = 0; i < l1; i++) 
        { 
            present[str1.charAt(i) - 'a'] = 1; 
        } 
        for (int i = 0; i < l2; i++) 
        { 
            if (present[str2.charAt(i) - 'a'] == 1
                || present[str2.charAt(i) - 'a'] == -1)  
            { 
                present[str2.charAt(i) - 'a'] = -1; 
            }  
            else
            { 
                present[str2.charAt(i) - 'a'] = 2; 
            } 
        } 
        for (int i = 0; i < MAX_CHAR; i++) 
        { 
            if (present[i] == 1 || present[i] == 2)  
            { 
                System.out.print((char) (i + 'a') + " "); 
            } 
        } 
    } 
    public static void findNoWith1(int n){
        int dp[][]=new int[n][2];
        dp[0][0]=1;
        dp[0][1]=1;
        for(int i=1;i<n;i++){
            dp[i][0]=dp[i-1][0]+dp[i-1][1];
            dp[i][1]=dp[i-1][0];
        }
        System.out.println( (1 << n) - dp[n-1][0] - dp[n-1][1]);
    }
} 