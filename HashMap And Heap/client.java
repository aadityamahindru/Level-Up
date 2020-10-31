class client{
    public static void main(String[] args) throws Exception {
        // int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        // priorityqueue pq=new priorityqueue(arr,false);
        // while(pq.size()>0){
        //     System.out.print(pq.remove()+" ");
        // }
      Hashmap<Integer,Integer> map=new Hashmap<>();
      map.put(1,10);
      map.put(5,560);
      map.put(3,106);
      map.put(2,99); 
      System.out.println(map);
      map.put(3,1);
      System.out.println(map.getOrDefault(13,100));
    }
}