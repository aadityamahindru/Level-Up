class Set1{
    //134 leetcode

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int extraGas=0;
        int deficit=0,sp=0;
        for(int i=0;i<gas.length;i++){
            extraGas+=gas[i]-cost[i];
            if(extraGas<0){
                deficit+=extraGas;
                sp=i+1;
                extraGas=0;
            }
        }
        return (sp==gas.length || extraGas+deficit<0)?-1:sp;
    }
}