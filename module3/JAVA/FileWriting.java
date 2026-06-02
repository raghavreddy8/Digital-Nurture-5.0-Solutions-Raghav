import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the text to be writte in File");
        String text=sc.nextLine();
        try {
            FileWriter fw=new FileWriter("output.txt");
            fw.write(text);
            fw.close();
            System.out.println("Data entered successfully");
        } catch(IOException e){
            System.out.println("Error writing to File.");
        }
    }
}