class Extra{
    public static void main(String[] args) {
        int arr[]={0,-5,-10,1,3,0,0,2};
        minMoves(arr);
    }
    //https://www.geeksforgeeks.org/minimum-steps-to-make-the-product-of-the-array-equal-to-1/
    public static void minMoves(int arr[]){
        int zero=0;
        int positive=0;
        int negative=0;
        int ans=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0){
                zero++;
            }else if(arr[i]>0){
                positive++;
                ans+=arr[i]-1;
            }else{
                negative++;
                ans+=(-1-arr[i]);
            }
        }
        if(negative%2==0){
            ans+=zero;
        }else if(zero>0){
            ans+=zero;
        }else{
            ans+=2;
        }
        System.out.println(ans);
    }
}