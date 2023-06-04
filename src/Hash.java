import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Hash {
    public static void main(String[] args) {
        System.out.println("'' -> " + "".hashCode());
        System.out.println("'0' -> " + "0".hashCode());
        System.out.println("'1' -> " + "1".hashCode());
        System.out.println("'2' -> " + "2".hashCode());

        System.out.println("\na -> " + "a".hashCode());
        System.out.println("A -> " + "A".hashCode());
        System.out.println("AA -> " + "AA".hashCode());

        System.out.println("\n1 -> " + new Integer(1).hashCode());
        System.out.println("1000 -> " + new Long(1000).hashCode());
        System.out.println("1.12 -> " + new Float(1.12f).hashCode());
        System.out.println("2.22 -> " + new Double(2.22d).hashCode());

        System.out.println("\ntrue -> " + new Boolean(true).hashCode());
        System.out.println("false -> " + new Boolean(false).hashCode());

        timeIt();

        HashMap<Integer,String> a = new HashMap<>();

        a.put(10, "abc");
        a.get(10);
        a.clear();
        a.remove(10);
        a.containsKey(10);
        a.putIfAbsent()
     }

    public static void timeIt()
    {
        long start = System.currentTimeMillis();

        byte[] txt = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes();
        for(int i=0; i<10000000; i++)
            hashCode(txt);

        long end = System.currentTimeMillis();

        System.out.println("\nOptimised implementation :" + (end - start) + "ms");

        start = System.currentTimeMillis();

        for(int i=0; i<10000000; i++)
            new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").hashCode();

        end = System.currentTimeMillis();

        System.out.println("Inbuilt implementation :" + (end - start) + "ms");
    }


    public static int hashCode(byte @NotNull [] value) {
        int h = 0;

        for (byte v : value)
            h = (h << 5) + v - h;

        return h;
    }

}
