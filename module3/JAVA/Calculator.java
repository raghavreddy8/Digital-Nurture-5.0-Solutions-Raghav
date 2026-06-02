import java.util.Scanner;

public class Calculator{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the arithmetic operation: 1.addition 2.subtraction 3.multiplication 4.division");
        int choice=sc.nextInt();
        System.out.println("Enter the first number");
        int a=sc.nextInt();
        System.out.println("Enter the second number");
        int b=sc.nextInt();
        int result=0;

        switch (choice) {
            case 1:
                result=a+b;
                break;
            case 2:
                result=a-b;
                break;
            case 3:
                result=a*b;
                break;
            case 4:
                result=a/b;
                break;
            default:
                break;
        }
        
        System.out.println("The result is "+result);
    }
}