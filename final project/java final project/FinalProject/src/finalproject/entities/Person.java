package finalproject.entities;
/**
 * @author Barry
 * */
public class Person implements java.io.Serializable {

    private static final long serialVersionUID = 4190276780070819093L;

    // this is a person object that you will construct with data from the DB
    // table. The "sent" column is unnecessary. It's just a person with
    // a first name, last name, age, city, and ID.

    private String firstName;
    private String LastName;
    private int age;
    private String city;
    private int ID;

    Person(){}

    public Person(String firstName, String lastName, int age, String city, int ID) {
        this.firstName = firstName;
        LastName = lastName;
        this.age = age;
        this.city = city;
        this.ID = ID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", ID=" + ID +
                '}';
    }
}
