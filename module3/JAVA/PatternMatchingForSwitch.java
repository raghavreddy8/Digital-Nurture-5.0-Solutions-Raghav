public class PatternMatchingForSwitch{
    static void acceptObjects(Object obj){
    switch(obj){
        case Integer i -> System.out.println(i+" - Integer.");
        case String s-> System.out.println(s+" - String.");
        case Double d-> System.out.println(d+" - Double.");
        case Float f->System.out.println(f+" - Float.");
        case Short ss-> System.out.println(ss+" - Short.");
        case Character c-> System.out.println(c+" - Character.");
        case null-> System.out.println("This a null value.");
        default -> System.out.println("Type Unknown");
        }
    } 
    public static void main(String[] args) {
        acceptObjects(10);
        acceptObjects("Hello World");
        acceptObjects(20.0f);
        acceptObjects(3434.3434);
        acceptObjects(null);
        acceptObjects((short)44);
        acceptObjects('c');
    }
}