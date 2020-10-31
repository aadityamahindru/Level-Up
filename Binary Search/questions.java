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
    // 74 leetcode && 240 leetcode
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
    // 33 without pivot
    public int search(int[] arr, int target) {
        int si=0,ei=arr.length-1;
        
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==target) return mid;
            else if(arr[si]<=arr[mid]){
                if(arr[si]<=target&&arr[mid]>target) ei=mid-1;
                else si=mid+1;
            }else{
                if(arr[mid]<target&&arr[ei]>=target) si=mid+1;
                else ei=mid-1;
            }
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
    // leet 300 lis nlogn
    public int lengthOfLIS(int[] nums) {
        if(nums.length<=1) return nums.length;
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(nums[0]);
        for(int i=1;i<nums.length;i++){
            int idx=Collections.binarySearch(arr,nums[i]);
            if(idx>=0) continue;
            
            idx=-idx-1;
            if(idx==arr.size()) arr.add(nums[i]);
            else arr.set(idx,nums[i]);
        }
        return arr.size();
    }

    //own binary search
    public int lengthOfLIS(int[] nums) {
        if(nums.length<=1) return nums.length;
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(nums[0]);
        for(int i=1;i<nums.length;i++){
            int si=0,ei=arr.size();
            while(si<ei){
                int mid=(si+ei)/2;
                if(arr.get(mid)<nums[i]) si=mid+1;
                else ei=mid;
            }
            if(ei==arr.size()) arr.add(nums[i]);
            else arr.set(ei,nums[i]);
        }
        return arr.size();
    }

    //https://practice.geeksforgeeks.org/problems/inversion-of-array/0
    public static long inversionCount(int arr[],int sortedArr[],int si,int ei){
        if(si>ei) return 0;
        int mid=(si+ei)/2;
        long count=0;
        count+=inversionCount(arr,sortedArr,si,mid);
        count+=inversionCount(arr,sortedArr,mid+1,ei);
        count+=totalInversion(arr,sortedArr,si,mid+1,ei);
        return count;
    }
    public static long totalInversion(int arr[],int sortedArr[],int si,int mid,int ei){
        long count=0;
        int i=si,j=mid,k=si;
        while(i<=mid-1&&j<=ei){
            if(arr[i]<=arr[j]){
                sortedArr[k++]=arr[i++];
            }else{
                count+=mid-i;
                sortedArr[k++]=arr[j++];
            }
        }
        while(i<=mid-1) sortedArr[k++]=arr[i++];
        while(j<=ei) sortedArr[k++]=arr[j++];
        while(si<=ei) arr[si]=sortedArr[si++];
        return count;
    }
    public static long inversion(int arr[]){
        int n=arr.length;
        int sortedArr[]=new int[n];
        return inversionCount(arr,sortedArr,0,n-1);
    }

    // 875 leet
    public int minEatingSpeed(int[] piles, int H) {
        int si=1,ei=(int)1e9;
        while(si<ei){
            int eatingSpeed=(si+ei)/2;
            if(isPossibleToEat(piles,eatingSpeed,H))
                ei=eatingSpeed;
            else si=eatingSpeed+1;
        }
        return si;
    }
    public boolean isPossibleToEat(int arr[],int eatingSpeed,int H){
        int hour=0;
        for(int i=0;i<arr.length;i++){
            hour+=((arr[i]-1)/eatingSpeed)+1;
            if(hour>H) return false;
        }
        return true;
    }

    //leetcode 81
    public boolean search(int[] arr, int target) {
        int si=0,ei=arr.length-1;
        
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==target) return true;
            else if(arr[si]<arr[mid]){
                if(arr[si]<=target&&arr[mid]>target) ei=mid-1;
                else si=mid+1;
            }else if(arr[mid]<arr[si]){
                if(arr[mid]<target&&arr[ei]>=target) si=mid+1;
                else ei=mid-1;
            }else{
                si++;
            }
        }
        return false;
    }
    // leetcode 786

    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n=A.length;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
            return A[a[0]]*A[b[1]]-A[b[0]]*A[a[1]];
        });
        for(int i=1;i<n;i++) pq.add(new int[]{0,i});
        while(--K>0){
            int arr[]=pq.poll();
            arr[0]++;
            if(arr[0]<arr[1]) pq.add(arr);
        }
        int arr[]=pq.remove();
        return new int[]{A[arr[0]],A[arr[1]]};
    }

    // leetcode 4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m-nums2.length;
        if(n > m){
            return findMedianSortedArrays(nums2,nums1);
        }   
        int omid=(n + m + 1)/2;
        int si=0,ei=n;
        while(si<=ei){
            int sMid=(si+ei)/2;
            int lMid=omid - sMid;
            int sl=(sMid==0)?-(int)1e8:nums1[sMid-1];
            int sr=(sMid==n)?(int)1e8:nums1[sMid];

            int ll=(lMid==0)?-(int)1e8:nums2[lMid-1];
            int sl=(lMid==m)?(int)1e8:nums2[lMid];

            if(sl > lr){
                ei=sMid-1;
            }else if(ll>sr){
                si=sMid+1;
            }else{
                int boundaryOfLeft=Math.max(sl,ll);
                int boundaryOfRight=Math.min(sr,lr);

                if( (n + m) %2 !=0 ) return boundaryOfLeft;
                else return (boundaryOfRight+boundaryOfLeft)/2.0;
            }
        }
        return 0.0;
    }
}