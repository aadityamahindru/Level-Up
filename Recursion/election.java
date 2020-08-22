import java.util.*;
class election{
    static class Pair{
        int first;
        int sec;
        Pair(){
            first=Integer.MAX_VALUE;
            sec=Integer.MAX_VALUE;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String s=sc.next();
        Pair check[]=new Pair[n];
        for(int i=0;i<n;i++){
            check[i]= new Pair();
        }
        findGovt(s,n,check);
    }
    public static void findGovt(String s,int n,Pair check[]){
        for(int i=0;i<n;i++){
            char ch1=s.charAt(i);
            if(ch1=='A'){
                int mov=0;
                int j=i-1;
                if(j>=0){
                    for(;j>=0;j--){
                        char ch2=s.charAt(j);
                        if(ch2=='-'){
                            check[j].first=Math.min(check[j].first,mov);
                        }else if(ch2=='A'||ch2=='B'){
                            break;
                        }
                        ++mov;
                    }
                }
            }else if(ch1=='B'){
                int mov=0;
                int j=i+1;
                if(j<n){
                    for(;j<n;j++){
                        char ch2=s.charAt(j);
                        if(ch2=='-'){
                            check[j].sec=Math.min(check[j].sec,mov);
                        }else if(ch2=='B'||ch2=='A'){
                            break;
                        }
                        mov++;
                    }
                }
            }
        }
        int countA=0,countB=0;
        for(int i=0;i<n;i++){
            int a=check[i].first;
            int b=check[i].sec;
            char ch=s.charAt(i);
            if(ch=='A'){
                countA++;
            }else if(ch=='B'){
                countB++;
            }
            if(ch=='-'){
                if(a>b){
                    countB++;
                }else if(a<b){
                    countA++;
                }
            }
        }
        if(countA>countB){
            System.out.print("A");
        }else if(countB>countA){
            System.out.print("B");
        }else{
            System.out.print("Coalition government");
        }
    }
}
