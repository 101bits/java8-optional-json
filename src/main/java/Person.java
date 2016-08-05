import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;

public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Optional<Address> address;
    private final Optional<String> phone;


    public Person(final String firstName, final String lastName, final int age) {
        this(firstName, lastName, age, Optional.empty(), Optional.empty());
    }

    @JsonCreator
    public Person(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("age") int age,
            @JsonProperty("address") Optional<Address> address,
            @JsonProperty("phone") Optional<String> phone) {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(address, person.address) &&
                Objects.equals(phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, address, phone);
    }
}
