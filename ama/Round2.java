class Round2{
    // find in a sorted rotated array
    public static void main(String[] args) {
        int arr[]={ 8,1,2,3,4,5,6,7 };
        int key=8;
        System.out.println(search_02(0,arr.length-1,arr,key));
    }

    // using pivot
    public static int search(int arr[],int key){
        int pivot=findPivot(0,arr.length-1,arr);
        // System.out.println(pivot);
        if(pivot==-1)
            return binarySearch(0,arr.length-1,arr,key);
        if(arr[pivot]==key)
            return pivot;
        if(arr[0]<=key)
            return binarySearch(0,pivot-1,arr,key);
        return binarySearch(pivot+1,arr.length-1,arr,key);
    }
    public static int findPivot(int low,int high,int arr[]){
        if(low<=high){
            int mid=(low+high)/2;
            if(mid!=high&&arr[mid]>arr[mid+1]) return mid;
            if(mid!=low&&arr[mid-1]>arr[mid]) return mid-1;
            if(arr[low]<=arr[mid])
            return findPivot(mid+1,high,arr);
            else
            return findPivot(low,mid-1,arr);
        }
        return -1;
    }
    public static int binarySearch(int low,int high,int arr[],int key){
        if(low<=high){
            int mid=(low+high)/2;
            if(arr[mid]==key) return mid;
            if(key<arr[mid])
                return binarySearch(low,mid-1,arr,key);
            return binarySearch(mid+1,high,arr,key);
        }
        return -1;
    }
    public static int search_02(int low,int high,int arr[],int key){
        if(low<=high){
            int mid=(low+high)/2;
            if(arr[mid]==key) return mid;
            if(arr[low]<=arr[mid]){
                if(key>=arr[low]&&key<arr[mid])
                    return search_02(low,mid-1,arr,key);
                return search_02(mid+1,high,arr,key);
            }
            if(key>arr[mid]&&key<=arr[high])
                return search_02(mid+1,high,arr,key);
            return search_02(low,mid-1,arr,key);
        }
        return -1;
    }
}