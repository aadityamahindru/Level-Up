import java.util.*;

public class Main {

    public static void main(String[] args) {

        // write your code here.
        HashMap < String, ArrayList < String >> user = new HashMap < > ();
        HashMap < String, ArrayList < String >> gen = new HashMap < > ();
        HashMap < String, ArrayList < String >> ans = new HashMap < > ();
        ArrayList < String > arr = new ArrayList < > ();
        arr.add("mass");
        arr.add("world");
        arr.add("stress");
        user.put("Fred", arr);

        arr = new ArrayList < > ();
        arr.add("happy");
        arr.add("pride");
        user.put("Jenie", arr);

        arr = new ArrayList < > ();
        arr.add("alexender");
        user.put("Rone", arr);
        // System.out.println(user);

        arr = new ArrayList < > ();
        arr.add("mass");
        arr.add("stress");
        gen.put("Horror", arr);

        arr = new ArrayList < > ();
        arr.add("happy");
        gen.put("Comedy", arr);

        arr = new ArrayList < > ();
        arr.add("world");
        arr.add("alexender");
        arr.add("pride");
        gen.put("Romance", arr);

        // System.out.println(gen);
        HashMap < String, String > map = new HashMap < > ();
        for (String key: user.keySet()) {
            for (String str: user.get(key)) {
                map.put(str, " ");
            }
        }
        for (String key: gen.keySet()) {
            for (String str: gen.get(key)) {
                if (map.containsKey(str)) {
                    map.put(str, key);
                }
            }
        }
        // System.out.println(map);
        for (String key: user.keySet()) {
            HashMap < String, Integer > hs = new HashMap < > ();
            ArrayList < String > a = new ArrayList < > ();
            for (String str: user.get(key)) {
                String k = map.get(str);
                if (hs.containsKey(k)) {
                    hs.put(k, hs.get(k) + 1);
                } else {
                    hs.put(k, 1);
                }
            }
            int max = 0;
            for (String str: hs.keySet()) {
                if (hs.get(str) > max) max = hs.get(str);
            }
            for (String str: hs.keySet()) {
                if (hs.get(str) == max) a.add(str);
            }
            Collections.sort(a);
            ans.put(key, a);
        }
        System.out.println(ans);
    }
}