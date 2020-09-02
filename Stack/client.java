class client{
    public static void main(String[] args) throws Exception {
        exp1();
    }
    public static void exp1() throws Exception{
        Stack st=new Stack();
        for(int i=1;i<=10;i++){
            st.push((int)(Math.random()*100));
        }
        System.out.println(st);
        st.pop();
        System.out.println(st);
        int arr[]={10,20,30,40,50};
        DynamicStack dst=new DynamicStack();
        for(int i=0;i<100;i++){
            dst.push((int)(Math.random()*100));
        }
        System.out.println(dst);
    }
}