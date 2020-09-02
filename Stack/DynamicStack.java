class DynamicStack extends Stack{
    public DynamicStack(){
        super();
    }
    public DynamicStack(int n){
        super(n);
    }
    public DynamicStack(int arr[]) throws Exception{
        super(2*arr.length);
        for(int val:arr){
            super.push(val);
        }
    }
    public void push(int val) throws Exception{
        if(super.size()==super.capacity()){
            int arr[]=new int[super.size()];
            int i=arr.length-1;
            while(super.size()!=0) arr[i--]=super.pop();
            super.setValues(2*super.capacity());
            for(int value:arr){
                super.push(value);
            }
        }
        super.push(val);
    }
}