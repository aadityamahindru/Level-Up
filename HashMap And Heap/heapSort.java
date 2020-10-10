class heapSort{

    public static int compareTo(int i, int j,boolean isMaxHeap,int arr[]){
        if(isMaxHeap) return arr[i]-arr[j];
        else return arr[j]-arr[i];
    }
    public static void heapSort(int arr[]){
        boolean isMaxHeap=true;
        int n=arr.length-1;
        for(int i=arr.length-1;i>=0;i--){
            downHeapify(arr,i,n,isMaxHeap);
        }
        for(int i=0;i<=n;i++){
            swap(arr,0,n-i);
            downHeapify(arr,0,n-i-1,isMaxHeap);
        }

    }
    public static void downHeapify(int arr[],int pi,int n,boolean isMaxHeap){
        int maxidx=pi;
        int lci=(2*pi)+1;
        int rci=(2*pi)+2;
        if(lci<=n&&compareTo(lci,maxidx,isMaxHeap,arr)>0) maxidx=lci;
        if(rci<=n&&compareTo(rci,maxidx,isMaxHeap,arr)>0) maxidx=rci;
        if(maxidx!=pi){
            swap(arr,maxidx,pi);
            downHeapify(arr,maxidx,n,isMaxHeap);
        }
    }
    public static void swap(int arr[],int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        heapSort(arr);
        for(int val:arr){
            System.out.print(val+" ");
        }
    }
}