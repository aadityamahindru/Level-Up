class Mazepath{
    public static void main(String[] args) {
        int c=mazepath(0,0,2,2,"");
        System.out.println(c);
    }
    public static int mazepath(int sr,int sc,int er,int ec,String ans){
        if(sr==er&&sc==ec){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        if(sc+1<=ec)
        count+=mazepath(sr,sc+1,er,ec,ans+"h");
        if(sr+1<=er)
        count+=mazepath(sr+1,sc,er,ec,ans+"v");
        if(sc+1<=ec&&sr+1<=er)
        count+=mazepath(sr+1,sc+1,er,ec,ans+"d");
        return count;
    }
}