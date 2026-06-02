import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame{
    public static void main(String[] args){
        Random rn=new Random();
        Scanner sc=new Scanner(System.in);
        int diff;
        boolean flag=true;
        int number = rn.nextInt(1,101);
        while(flag){
            System.out.println("Enter your guess (1-100): ");
            int guess=sc.nextInt();
            diff=Math.abs(number-guess);
        
            if (diff==0){
                System.out.println("The guess is right.");
                flag=false;
            }     
            else if (diff<5){
                System.out.println("The guess is extremely right");
            }
            else if (diff<15){
                System.out.println("The guess is almost wrong");
            }
            else if(diff<25) {
                System.out.println("The guess is slightly wrong");
            } 
            else if(diff<50){
                System.out.println("The guess is moderately wrong");
            }
            else if(diff<75){
                System.out.println("The guess is highly wrong");
            }
            else{
                System.out.println("The guess is extremely wrong");
            }
        }
    }
}