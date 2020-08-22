import java.util.*;
class pairSum{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int nums[]=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        pairSumSolver(nums);
    }
    public static void pairSumSolver(int nums[]){
        int count=0;
        for(int i=0;i<nums.length;i++){
            String str=convertToString(nums[i]);
            count+=countVowels(str);
        }
        Arrays.sort(nums);
        int i=0,j=nums.length-1;
        int pair=0;
        while(i<j){
            if(nums[i]+nums[j]==count){
                pair++;
                i++;
                j--;
            }else if(nums[i]+nums[j]<count){
                i++;
            }else{
                j--;
            }
        }
        if(pair<=100){
            System.out.println(convertToString(pair));
        }else{
            System.out.println("greater 100");
        }
    }
    public static int countVowels(String str){
        int count=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')
            count++;
        }
        return count;
    }
    public static String convertToString(int n){
        String ones[]={"zero","one","two","three","four","five","six","seven","eight","nine"};
        String twos[]={"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen",""};
        String tens[]={"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        String hund="hundred";
        if(n<10){
            return ones[n];
        }else if(n==100){
            return hund;
        }else if(n>=10&&n<=19){
            return twos[n%10];
        }else if(n%10==0){
            return tens[n/10];
        }else{
            String str=tens[n/10]+"-"+ones[n%10];
            return str;
        }
    }
}