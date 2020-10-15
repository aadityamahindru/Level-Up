class Set2{
    public static void print1D(int arr[]){
        for(int a:arr){
            System.out.print(a+"\t");
        }
        System.out.println();
    }
    public static void print2D(int arr[][]){
        for(int a[]:arr){
            print1D(a);
        }
    }
    public static void solve(){
    }
    public static void main(String[] args) {
        solve();
    }
}