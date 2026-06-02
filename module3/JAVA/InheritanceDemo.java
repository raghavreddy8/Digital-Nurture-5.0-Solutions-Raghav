class Animal{
    void makeSound(){
        System.out.println("Bark");
    }
}

class Dog extends Animal{
    @Override
    void makeSound(){
        System.out.println("Bark");
    }
}

public class InheritanceDemo{
    public static void main(String[] args) {
        //Animal class object creation 
        Animal a=new Animal();
        //Dog class object creation
        Dog b=new Dog();
        // calling method from animal
        a.makeSound();
        // calling method from Dog
        b.makeSound();
    }
}

