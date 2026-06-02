import java.util.Scanner;
public class RecursiveFibonacci{
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter the number you want Fibonacci for: ");
        int number=sc.nextInt();
        sc.close();
        
        System.out.println("The fibonacci of the number is "+fibonacci(number));
    }
    private static int fibonacci(int num){
        if (num<=0) return 0;
        else if (num==1) return 1;
        else return fibonacci(num-2)+fibonacci(num-1); 
        
    }
}