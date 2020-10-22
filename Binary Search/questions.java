class questions{
    // leetcode 34
    public int[] searchRange(int[] nums, int target) {
        int ans[]=new int[2];
        ans[0]=firstOccr(nums,target);
        if(ans[0]==-1) return new int[]{-1,-1};
        ans[1]=lastOccr(nums,target);
        return ans;
    }
    public int firstOccr(int arr[],int ele){
        int si=0,ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==ele){
                if(mid-1>=0&&arr[mid-1]==ele) ei=mid-1;
                else return mid;
            }
            else if(arr[mid]<ele) si=mid+1;
            else ei=mid-1;
        }
        return -1;
    }
    public int lastOccr(int arr[],int ele){
        int si=0,ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==ele){
                if(mid+1<arr.length&&arr[mid+1]==ele) si=mid+1;
                else return mid;
            }
            else if(arr[mid]<ele) si=mid+1;
            else ei=mid-1;
        }
        return -1;
    }
    // 74 leetcode
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0) return false;
        int i=0,j=matrix[0].length-1;
        while(i<matrix.length&&j>=0){
            if(matrix[i][j]==target) return true;
            else if(matrix[i][j]<target) i=i+1;
            else j=j-1;
        }
        return false;
    }

    // 658 leetcode
    public List<Integer> findClosestElements(int[] a, int k, int x) {
        List<Integer> arr=new ArrayList<>();
        for(int ele:a) arr.add(ele);
        int n=a.length;
        if(x<=a[0]) return arr.subList(0,k);
        else if(x>=a[n-1]) return arr.subList(n-k,n);
        else{
            int idx=Collections.binarySearch(arr,x);
            if(idx<0){
                idx= -idx -1;
            }
            int si=Math.max(0,idx-k);
            int ei=Math.min(idx+k,n-1);
            while(ei-si>=k){
                if(x-a[si] > a[ei]-x) si++;
                else ei--;
            }
            return arr.subList(si,ei+1);
        }
    }

    // 33 leetcode with pivot
    public int search(int[] nums, int target) {
        int pivot=findPivot(nums);
        int n=nums.length;
        if(pivot==-1) return binarySearch(nums,0,n-1,target);
        else  if(nums[pivot]==target) return pivot;
        else if(nums[0]<=target) 
            return binarySearch(nums,0,pivot-1,target);
        else
            return binarySearch(nums,pivot+1,n-1,target);
    }
    public int findPivot(int arr[]){
        int si=0,ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(mid+1<arr.length&&arr[mid+1]<arr[mid]) return mid;
            if(mid-1>=0&&arr[mid-1]>arr[mid]) return mid-1;
            if(arr[si]<=arr[mid]) si=mid+1;
            else ei=mid-1;
        }
        return -1;
    }
    public int binarySearch(int arr[],int si,int ei,int ele){
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==ele) return mid;
            else if(arr[mid]<ele) si=mid+1;
            else ei=mid-1;
        }
        return -1;
    }
}