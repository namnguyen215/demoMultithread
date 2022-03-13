import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.*;

public class main {
    public static void main(String[] args) {
        //Khoi tao
        ExecutorService threadpool= Executors.newFixedThreadPool(5);
        File directoryPath = new File("src/main/resources");
        File[] listFile = directoryPath.listFiles();
        HashMap<String,Integer> map=new HashMap<>();

        //Duyet qua cac file
        for (File file : listFile) {
            String path=file.getPath();
            System.out.println("Start to read file: "+path);
            Callable<HashMap<String,Integer>> callable=new Callable<HashMap<String, Integer>>() {
                @Override
                public HashMap<String,Integer> call() throws Exception {
                    CountWord c=new CountWord(path);
                    HashMap<String,Integer> thisMap=c.getCountWord();
                    return map;
                }
            };

            //Day vao threadpool
            Future future=threadpool.submit(callable);

            //Lay result
            try{
                HashMap<String,Integer> returnMap= (HashMap<String, Integer>) future.get();
                System.out.println(returnMap);
                map.forEach((key,value) -> returnMap.merge(key, value, (v1, v2) -> v1 + v2));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Complete to read file: "+path);
        }

        threadpool.shutdown();
        //Sap xep
        Comparator<String> valueComparator = (k1, k2) -> {

            int comp = map.get(k1).compareTo(map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        TreeMap<String,Integer> tree=new TreeMap<>(valueComparator);
        tree.putAll(map);

        //In ket qua
        System.out.println(tree.size());

    }
}
