// backwards approach

public int minCostClimbingStairs(int[] cost, int n, int[] dp) {
    if(n<=1) return dp[n]=cost[n];
    if(dp[n]!=0) return dp[n];
    
    int ans = Math.min(minCostClimbingStairs(cost,n-1,dp),minCostClimbingStairs(cost,n-2,dp));

    return dp[n] = ans + (n != cost.length ? cost[n] : 0);
}

public int minCostClimbingStairs_DP(int[] cost, int n, int[] dp) {
    for(n=0;n<=cost.length;n++){
        if(n<=1){
            dp[n]=cost[n];
            continue;
        }

        int ans = Math.min(dp[n-1],dp[n-2]);
        dp[n] = ans + (n != cost.length ? cost[n] : 0);
    }
    
    return dp[cost.length];
}

public  int minCostClimbingStairs(int[] cost) {
    int[] dp = new int[cost.length + 1];
    int ans = minCostClimbingStairs(cost,dp.length,dp);
    int ans = minCostClimbingStairs_DP(cost,dp.length,dp);
    return ans;
}