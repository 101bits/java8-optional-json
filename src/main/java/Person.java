import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Optional<Address> address;
    private Optional<String> phone;

    public Person(String firstName, String lastName, int age) {
        this(firstName, lastName, age, Optional.empty(), Optional.empty());
    }

    public Person(String firstName, String lastName, int age,
                  Optional<Address> address, Optional<String> phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @JsonIgnore
    public Optional<Address> getAddress() {
        return address;
    }

    @JsonIgnore
    public Optional<String> getPhone() {
        return phone;
    }

    @JsonProperty("address")
    private Address getAddressForJson(){
        return address.orElse(null);
    }

    @JsonProperty("phone")
    private String getPhoneForJson() {
        return phone.orElse(null);
    }
}
