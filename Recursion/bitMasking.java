// 191 leetcode
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int c=0;
        for(int i=0;i<32;i++){
            int temp=(n&1);
            if(temp==1){
                c++;
            }
            n=(n>>>1);
        }
        return c;
    }
}

//342
class Solution {
    public boolean isPowerOfFour(int n) {
        return n>0&& (n & (n-1))==0 &&(n-1)%3==0;
    }
}

//136
class Solution {
    public int singleNumber(int[] nums) {
        int ans=0;
        for(int ele : nums){
            ans^=ele;
        }
        return ans;
    }
}

//137
class Solution {
    public int singleNumber(int[] nums) {
       int ans=0;
        for(int i=0;i<32;i++){
            int count=0;
            int mask=(1<<i);
            for(int ele : nums){
                if((ele & mask)!=0){
                    count++;
                }
            }
            if(count%3!=0){
                ans |=mask;
            }
        }
        return ans;
    }
}

// leetcode 260

class Solution {
    public int[] singleNumber(int[] nums) {
        int xorVal=0;
        for(int ele:nums){
            xorVal ^=ele;
        }
        int a=0,b=0;
        int mask = (xorVal & (-xorVal));   //gets first on bit;
        for(int ele :nums){
            if((ele & mask)==0){
                a^=ele;             // divide no's into two diff groups
            }else{
                b^=ele;
            }
        }
        return new int[]{a,b};
    }
}