class basics{
    public static int binarySearch(int arr[],int ele){
        int si=0,ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==ele) return mid;
            else if(arr[mid]<ele) si=mid+1;
            else ei=mid-1;
        }
        return -1;
    }
    public static int firstOccr(int arr[],int ele){
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
    public static int lastOccr(int arr[],int ele){
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
    public static int closestElement(int arr[],int ele){
        int si=0,ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid]==ele) return mid;
            else if(arr[mid]<ele) si=mid+1;
            else ei=mid-1;
        }
        if(ei<0) return 0;
        else if(si>=arr.length) return arr.length-1;
        else return ele-arr[ei]<arr[si]-ele?ei:si;
    }
    public static int findPossibleIndex(int arr[],int ele){
        int si=0,ei=arr.length;
        while(si<ei){
            int mid=(si+ei)/2;
            if(arr[mid]<ele) si=mid+1;
            else ei=mid;
        }
        return ei;
    }
    public static void main(String[] args) {
        int arr[]={10,10,10,10,20,20,20,20,30,30,30,30,30,30,30,30,30,30,34,34,34,34,39,42,42};
        // int arr[]={1,7,10,12,25,35,55,60};
        System.out.println(binarySearch(arr,20));
        System.out.println(firstOccr(arr,20));
        System.out.println(lastOccr(arr,20));
        System.out.println(closestElement(arr,65));
        // System.out.println(findIndex(arr,57));
    }
}