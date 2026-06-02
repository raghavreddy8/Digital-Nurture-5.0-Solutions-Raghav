
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIDemo{
    public static void main(String[] args) {
        List<Integer> list=Arrays.asList(24,19,6,51,17,20,36);
        List<Integer> evenNumbers=list.stream()
                                .filter(n->n%2==0)
                                .collect(Collectors.toList());
                                
        evenNumbers.forEach(s->System.err.println(s));
    }
}