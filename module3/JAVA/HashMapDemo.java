import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapDemo{
    public static void main(String[] args){
        Map<Integer,String> maps=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        boolean more=true;
         while (more) {
            System.out.print("Enter Student ID: ");
            int key = sc.nextInt();

            System.out.print("Enter Student Name: ");
            String value = sc.next();

            maps.put(key, value);

            System.out.print("Add another entry (y/n): ");
            more = sc.next().equalsIgnoreCase("y");
        }
        sc.close();

        System.out.println("Enter ID to search");
        int request=sc.nextInt();
        System.out.println(maps.get(request));
    }
}