class Trees8{
    public static void main(String[] args) {
        solve();
    }
public static void solve(){
    int coins[]={2,3,5,7};
    int tar=10;
    //System.out.println(coinChangePermutationInfinite(coins,0,tar,""));
    //System.out.println(coinChangeCombinationInfinite(coins,0,tar,""));
    System.out.println(coinChangePermutationSingle(coins,0,tar,""));
    //System.out.println(coinChangeCombinationSingle(coins,0,tar,""));
    System.out.println("----------------------------------------");
    // System.out.println(coinChangeCombinationSingle_sub(coins,0,tar,""));
    System.out.println(coinChangePermutationSingle_sub(coins,0,tar,""));
}
public static int coinChangePermutationInfinite(int coins[],int idx,int tar,String ans){
    if(tar==0){
        System.out.println(ans);
        return 1;
    }
    int c=0;
    for(int i=0;i<coins.length;i++){
        if(tar-coins[i]>=0){
            c+=coinChangePermutationInfinite(coins,0,tar-coins[i],ans+coins[i]);
        }
    }
    return c;
}
public static int coinChangeCombinationInfinite(int coins[],int idx,int tar,String ans){
    if(tar==0){
        System.out.println(ans);
        return 1;
    }
    int c=0;
    for(int i=idx;i<coins.length;i++){
        if(tar-coins[i]>=0){
            c+=coinChangeCombinationInfinite(coins,i,tar-coins[i],ans+coins[i]);
        }
    }
    return c;
}
public static int coinChangePermutationSingle(int coins[],int idx,int tar,String ans){
    if(tar==0){
        System.out.println(ans);
        return 1;
    }
    int c=0;
    for(int i=0;i<coins.length;i++){
        if(coins[i]>0&&tar-coins[i]>=0){
            int temp=coins[i];
            coins[i]=-coins[i];
            c+=coinChangePermutationSingle(coins,0,tar-temp,ans+temp);
            coins[i]=-coins[i];
        }
    }
    return c;
}
public static int coinChangeCombinationSingle(int coins[],int idx,int tar,String ans){
    if(tar==0){
        System.out.println(ans);
        return 1;
    }
    int c=0;
    for(int i=idx;i<coins.length;i++){
        if(tar-coins[i]>=0){
            c+=coinChangeCombinationSingle(coins,i+1,tar-coins[i],ans+coins[i]);
        }
    }
    return c;
}
public static int coinChangeCombinationSingle_sub(int coins[],int idx,int tar,String ans){
    if(tar==0||idx==coins.length){
        if(tar==0){
        System.out.println(ans);
        return 1;
        }
        return 0;
    }
    int c=0;
    if(tar-coins[idx]>=0)
    c+=coinChangeCombinationSingle_sub(coins,idx+1,tar-coins[idx],ans+coins[idx]);
    c+=coinChangeCombinationSingle_sub(coins,idx+1,tar,ans);
    return c;
}
public static int coinChangePermutationSingle_sub(int coins[],int idx,int tar,String ans){
    if(tar==0||idx==coins.length){
        if(tar==0){
        System.out.println(ans);
        return 1;
        }
        return 0;
    } 
    int c=0;
    if(coins[idx]>0&&tar-coins[idx]>=0){
        int temp=coins[idx];
        coins[idx]=-coins[idx];
        c+=coinChangePermutationSingle_sub(coins,0,tar-temp,ans+temp);
        coins[idx]=-coins[idx];
    }
    c+=coinChangePermutationSingle_sub(coins,idx+1,tar,ans);
    return c;
}
}