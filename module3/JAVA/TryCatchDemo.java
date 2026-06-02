
import java.util.Scanner;

public class TryCatchDemo{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the first number:");
        int num1=sc.nextInt();
        System.out.print("Enter the second number:");
        int num2=sc.nextInt();
        int result=0;
        try {
            result=num1/num2;
            System.out.println(result);
        } catch (ArithmeticException e) {System.out.println("Cannot divide by zero");}
        finally{
            System.out.println(result);
        }
    }
}