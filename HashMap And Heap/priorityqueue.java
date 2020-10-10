import java.util.ArrayList;
public class priorityqueue{
    private ArrayList<Integer> pq;
    private boolean isMaxHeap;
    public priorityqueue(int arr[], boolean isMax){
        initialize(isMax);
        for(int val:arr){
            pq.add(val);
        }
        constructHeap();
    }
    public priorityqueue(){
        initialize(true);
    }
    private void initialize(boolean isMax){
        pq=new ArrayList<>();
        isMaxHeap=isMax;
    }
    private void constructHeap(){
        for(int i=pq.size()-1;i>=0;i--){
            downHeapify(i);
        }
    }
    private int compareTo(int i, int j){
        if(isMaxHeap) return pq.get(i)-pq.get(j);
        else return pq.get(j)-pq.get(i);
    }
    private void swap(int i,int j){
        int v1=pq.get(i);
        int v2=pq.get(j);

        pq.set(i,v2);
        pq.set(j,v1);
    }
    private void upHeapify(int ci){
        int pi=(ci-1)/2;
        if(pi>=0&&compareTo(ci,pi)>0){
            swap(ci,pi);
            upHeapify(pi);
        }
    }
    private void downHeapify(int pi){
        int maxidx=pi;
        int lci=(2*pi)+1;
        int rci=(2*pi)+2;
        if(lci<pq.size()&&compareTo(lci,maxidx)>0) maxidx=lci;
        if(rci<pq.size()&&compareTo(rci,maxidx)>0) maxidx=rci;
        if(maxidx!=pi){
            swap(maxidx,pi);
            downHeapify(maxidx);
        }
    }
    public int size(){
        return pq.size();
    }
    public boolean isEmpty(){
        return pq.size()==0;
    }
    public int peek() throws Exception{
        if(pq.size()==0){
            throw new Exception("Null Pointer Exception");
        }
        return pq.get(0);
    }
    public void add(int val){
        pq.add(val);
        upHeapify(pq.size()-1);
    }
    public int remove() throws Exception{
        if(pq.size()==0){
            throw new Exception("Null Pointer Exception");
        }
        int rv=pq.get(0);
        swap(0,pq.size()-1);
        pq.remove(pq.size()-1);
        downHeapify(0);
        return rv;
    }

}