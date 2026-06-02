public class MethodOverloading{
    public static void main(String[] args){
        MethodOverloading mo=new MethodOverloading();
        int intNum1=10;
        int intNum2=20;
        int intNum3=30;
        double doubNum1=33;
        double doubNum2=54;

        //calling method with two integers
        int first_result=mo.add(intNum1,intNum2);
        //calling method with three integers
        int second_result=mo.add(intNum1,intNum2,intNum3);
        //calling method with two doubles
        double doub_result=mo.add(doubNum1,doubNum2);
        
        System.out.println("The output for two integers addition is "+first_result);
        System.out.println("The output for three integers addition is "+second_result);
        System.out.println("The output for two doubles addition is "+doub_result);
    }
    public int add(int a,int b){
        return a+b;
    }
    public double add(double a,double b){
        return a+b;
    }
    public int add(int a,int b,int c){
        return a+b+c;
    }
}