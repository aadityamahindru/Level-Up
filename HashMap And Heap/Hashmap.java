import java.util.*;
public class Hashmap<K,V>{
    private class Node{
        K key;
        V val;
        Node(K key,V val){
            this.key=key;
            this.val=val;
        }
    }
    private int size=0;
    private int capacity=0;
    LinkedList<Node> bucket[];
    Hashmap(){
        assign(15);
    }
    private void assign(int capacity){
        this.capacity=capacity;
        this.size=0;
        bucket=new LinkedList[capacity];
        for(int i=0;i<capacity;i++){
            bucket[i]=new LinkedList<>();
        }
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<bucket.length;i++){
            int grpSize=bucket[i].size();
            LinkedList<Node> grp=bucket[i];
            while(grpSize-->0){
                Node node=grp.getFirst();
                sb.append("{"+node.key+" = "+node.val+"},");
                grp.addLast(grp.removeFirst());
            }
        }
        String str=sb.toString();
        if(str.length()!=0) str = str.substring(0,str.length()-1);
        return "[ "+str+" ]";
    } 

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size==0;
    }

    public V get(K key){
        Node node=findGroup(key);
        return node!=null?node.val:null;
    }

    public void put(K key,V val){
        Node node=findGroup(key);
        if(node!=null){
            node.val=val;
        }else{
            LinkedList<Node> grp=group(key);
            grp.addLast(new Node(key,val));
            this.size++;

            double lambda=grp.size()/(bucket.length * 1.0);
            if(lambda>=0.4) rehash();
        }
    }
    public boolean containsKey(K key){
        Node node=findGroup(key);
        return node!=null;
    }

    public V getOrDefault(K key,V defaultVal){
        Node node =findGroup(key);
        return node!=null?node.val:defaultVal;
    }

    public V remove(K key){
        Node node=findGroup(key);
        if(node==null) return null;

        LinkedList<Node> grp=group(key);
        this.size--;
        return grp.removeFirst().val;
    }

    public ArrayList<K> keySet(){
        ArrayList<K> keys=new ArrayList<>();
        for(int i=0;i<bucket.length;i++){
            int grpSize=bucket[i].size();
            LinkedList<Node> grp=bucket[i];
            while(grpSize-->0){
                keys.add(grp.getFirst().key);
                grp.addLast(grp.removeFirst());
            }
        }
        return keys;
    }

    private Node findGroup(K key){
        LinkedList<Node> grp=group(key);
        int grSize=grp.size();
        while(grSize-->0){
            if(grp.getFirst().key==key){
                return grp.getFirst();
            }
            grp.addLast(grp.removeFirst());
        }
        return null;
    }
    private void rehash(){
        LinkedList<Node> oldBucket[]=bucket;
        assign(2 * bucket.length);

        for(int i=0;i<capacity;i++){
            LinkedList<Node> grp=oldBucket[i];
            while(grp.size()!=0){
                Node node=grp.removeFirst();
                put(node.key,node.val);
            }
        }
    }
    private LinkedList<Node> group(K key){
        int gidx=hashing(key);
        return bucket[gidx];
    } 
    private int hashing(K key){
        int idx=key.hashCode();
        idx=Math.abs(idx) % bucket.length;
        return idx;
    }
}