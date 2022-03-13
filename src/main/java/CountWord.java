import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CountWord {
    String path;
    HashMap<String,Integer> map=new HashMap<>();
    public CountWord(String path) {
        this.path = path;
    }

    public HashMap<String,Integer> getCountWord(){
        try {
            BufferedReader read=new BufferedReader(new FileReader(path));
            String content;
            while((content= read.readLine())!=null){
                String[] words=content.split("\\W");
                for(String word:words){
                    word=word.toLowerCase();
                    if(word.length()>0)
                        map.compute(word,(k,v)->v==null?1:v+1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
