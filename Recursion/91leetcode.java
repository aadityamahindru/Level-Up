class Solution {
    public int numDecodings(String s) {
        return count(s,0);
    }
    public int count(String nums,int idx){
        if(idx==nums.length()){
            return 1;
        }
        int cidx=nums.charAt(idx)-'0';
        if(cidx==0)
            return 0;
        int c=0;
        c+=count(nums,idx+1);
        if(idx<nums.length()-1){
            cidx=cidx*10+(nums.charAt(idx+1)-'0');
            if(cidx>=10&&cidx<=26){
                c+=count(nums,idx+2);
            }
        }
        return c;
    }
}