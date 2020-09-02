class Stack{
    private int tos;
    private int[] arr;
    private int size;
    private int maxSize;

    public Stack(){
        setValues(10);
    }

    public Stack(int n){
        setValues(n);
    }

    protected void setValues(int n){
        this.tos=-1;
        this.arr=new int[n];
        this.size=0;
        this.maxSize=n;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=this.tos;i>=0;i--){
            sb.append(this.arr[i]);
            if(i!=0) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    public int capacity(){
        return this.maxSize;
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size==0;
    }
    private int top_(){
        return this.arr[this.tos];
    }
    public int top() throws Exception{
        if(this.size==0){
            throw new Exception("StackIsEmpty");
        }
        return top_();
    }
    private void push_(int val){
        this.arr[++tos]=val;
        this.size++;
    }
    public void push(int val) throws Exception{
        if(this.size==this.maxSize){
            throw new Exception("StackIsFull");
        }
        push_(val);
    }
    private int pop_(){
        int rv=top_();
        this.arr[this.tos--]=0;
        this.size--;
        return rv;
    }
    public int pop() throws Exception{
        if(this.size==0){
            throw new Exception("StackISEmpty");
        }
        return pop_();
    }
}