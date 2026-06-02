
import java.util.Scanner;

public class StringReversal{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the string:");
        StringBuilder sb=new StringBuilder(sc.next());
        //printing reversed string
        System.out.print("The reversed string is: "+sb.reverse());
    }
}
