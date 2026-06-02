import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListDemo{
    public static void main(String[] args){
        List<String> names=new ArrayList<>(5);
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the names:");
        boolean ans=true;
        do {
            names.add(sc.next());
            System.out.print("Do you want to enter more(y/n):");
            String answer=sc.next();
            ans = answer.equals("y");
            } while(ans);
        System.out.println(names);
        sc.close();
    }
}