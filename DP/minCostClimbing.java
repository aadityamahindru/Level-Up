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


// min cost multiple moves

public int minCostWJumps(int st,int sc,int er,int ec,int cost[][]){
    if(st==er&&sc==ec) return cost[st][sc];
    int c=0;
    for(int jump=1;jump+sc<=ec;jump++){
        c=Math.min(c,minCostWJumps(st,sc+jump,er,ec,cost));
    }
    for(int jump=1;jump+st<=er;jump++){
        c=Math.min(c,minCostWJumps(st+jump,sc,er,ec,cost));
    }
    for(int jump=1;jump+sc<=ec&&jump+st<=er;jump++){
        c=Math.min(c,minCostWJumps(st+jump,sc+jump,er,ec,cost));
    }
    return c+cost[st][sc];
}