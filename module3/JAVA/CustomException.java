
import java.util.Scanner;

class InvalidAgeException extends Exception{
    public InvalidAgeException(String s){
        super(s);
    }
}

public class CustomException{
    public static void ageCheck(int num) throws InvalidAgeException{
        if (num<18){
            throw new InvalidAgeException("Age is below 18");
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the age");
        int age=sc.nextInt();
        try{
            ageCheck(age);
        } catch(InvalidAgeException e){System.out.println(e);}
    }
}