import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class LambdaExpressionDemo{
    public static void main(String[] args) {
        List<String> list=new ArrayList<>(5);
        list.add("Raghav");
        list.add("Venkat");
        list.add("Khaja");
        list.add("Praveen");

        Collections.sort(list, (s1,s2)-> s1.compareTo(s2));

        System.out.println(list);
    }
}