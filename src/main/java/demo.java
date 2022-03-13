import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class demo {

    public static void main(String[] args) {
        CountWord c=new CountWord("src/main/resources/1.txt");
        HashMap<String,Integer> map=c.getCountWord();
        Comparator<String> valueComparator = (k1, k2) -> {

            int comp = map.get(k1).compareTo(map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        TreeMap<String,Integer> tree=new TreeMap<>(valueComparator);
        tree.putAll(map);
        for(Map.Entry<String,Integer> e:tree.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }
}
