import java.util.Scanner;

class Car{
    String make;
    String model;
    int year;
    Car(String make,String model,int year){
        this.make=make;
        this.model=model;
        this.year=year;
    }

    public void displayDetails(){
        System.out.println("The car make is: "+make+"\n model is: "+model+"\n manufactured in:"+year);
    }
}

public class ClassAndObject{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //taking user input 
        System.out.println("Enter the make:");
        String make=sc.next();

        System.out.println("Enter the model:");
        String model=sc.next();

        System.out.println("Enter the year:");
        int year=sc.nextInt();
        
        // creating object and passing arguments
        Car c=new Car(make,model,year);
        //calling method from class Car
        c.displayDetails();
    }
}