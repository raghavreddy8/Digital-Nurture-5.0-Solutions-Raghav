import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//using records keyword
record Person(String name,int age){}

public class RecordsDemo{
    public static void main(String[] args) {
        //Adding people's name and age into a List.
        List<Person> people=new ArrayList<>();
        people.add(new Person("Raghav",22));
        people.add(new Person("Vijay",23));
        people.add(new Person("Chandra Sai",15));
        people.add(new Person("Gouri Shankar",10));
        people.add(new Person("Jagan",33));
        people.add(new Person("Rajiv",18));
        
        System.out.println();

        //Printing List data 
        for(Person n:people){
            System.out.println("Person's Name: "+n.name()+"Age: "+n.age());
        }

        // filtering by age above eighteen using stream
        List<Person> aboveEighteen=people.stream()
                                         .filter(person->person.age()>=18)
                                         .collect(Collectors.toList());

        // Printing the filtered list
        System.err.println("Filtered by Age above 18 :");
        for(Person n:aboveEighteen){
            System.err.println("Name: "+n.name()+" Age: "+n.age());
            }
    }
}