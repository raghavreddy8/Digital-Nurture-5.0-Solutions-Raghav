import java.util.Scanner;
public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int sum=0;
        System.out.println("Enter the size of the array:");
        int size=sc.nextInt();

        int[] array=new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i=0;i<size;i++)
        {
            array[i]=sc.nextInt();
        }
        //sum of array elements
        for (int i=0;i<size;i++)
        {
            sum=sum+array[i];
        }

        System.out.println("The sum of array elements is: "+sum);
        System.out.println("The average of array elements is: "+sum/size);
    }
}