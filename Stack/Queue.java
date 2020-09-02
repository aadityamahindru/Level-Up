class Queue{
    private int arr[];
    private int size;
    private int rear;
    private int maxSize;


    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size==0;
    }
    public void add_(int val){
        this.arr[this.rear++]=val;
        this.size++;
    }
    public void add(int val) throws Exception{
        if(this.rear==this.maxSize){
            throw new Exception("QueueISFull");
        }
        add_(val);
    }
    
}