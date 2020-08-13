class nQueenBit{
    // without using board
    static boolean rows[];
    static boolean cols[];
    static boolean dia[];
    static boolean Antidia[];
    static int rows=0;
    static int cols=0;
    static int dia=0;
    static int antidia=0;
    public static void main(String[] args) {
        int n=4;
        int m=4;
        rows=new boolean[n];
        cols=new boolean[m];
        dia=new boolean[n+m-1];
        Antidia=new boolean[n+m-1];
        System.out.println(nQueenBool(n,n,0,""));
        System.out.println(nQueenBits(n,n,0,""));
    }
    public static int nQueenBool(int n,int qno,int idx,String ans){
        if(qno==0){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<n*n;i++){
            int r=i/n;                            //n=m=4
            int c=i%n;
            if(!rows[r] && !cols[c] && !Antidia[r+c] && !dia[r-c+n-1]){     //(row-col+m  m=4 ==> m-1=3)
                rows[r]=true;
                cols[c]=true;
                Antidia[r+c]=true;
                dia[r-c+n-1]=true;
                count+=nQueenBool(n,qno-1,i+1,ans+"( "+r+" , "+c+" ),");
                rows[r]=false;
                cols[c]=false;
                Antidia[r+c]=false;
                dia[r-c+n-1]=false;
            }
        }
        return count;
    }

    //using bits 
    public static int nQueenBits(int n,int qno,int idx,String ans){
        if(qno==0){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<n*n;i++){
            int r=i/n;
            int c=i%n;
            if((rows & (1<<r))==0 && (cols & (1<<c))==0 && (dia & (1<<(r-c+n-1)))==0 && (antidia & (1<<(r+c)))==0){
                rows ^= (1<<r);
                cols ^= (1<<c);
                dia ^= (1<<(r-c+n-1));
                antidia ^=(1<<(r+c));
                count+=nQueenBits(n,qno-1,i+1,ans+"( "+r+" , "+c+" ),");
                rows ^= (1<<r);
                cols ^= (1<<c);
                dia ^= (1<<(r-c+n-1));
                antidia ^=(1<<(r+c));
            }
        }
        return count;
    }
}




