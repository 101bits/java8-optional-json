import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    final ObjectMapper objectMapper = registerJdkModuleAndGetMapper();

    @Test
    public void testPersonWhenNoAddressOrPhone() throws Exception {
        Person person = new Person("john", "doe", 21);
        String jsonString = objectMapper.writeValueAsString(person);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        assertEquals(person.getFirstName(), jsonNode.get("firstName").asText());
        assertEquals(person.getLastName(), jsonNode.get("lastName").asText());
        assertTrue(jsonNode.get("address").isNull());
        assertTrue(jsonNode.get("phone").isNull());
    }

    @Test
    public void testPersonWhenHasAddressAndPhone() throws Exception {
        Address address = new Address("1 Infinite Loop", "Cupertino", "CA", 95014, "USA");
        String phone = "1-800-My-Apple";
        Person person = new Person("john", "doe", 21, Optional.of(address), Optional.of(phone));

        String jsonString = objectMapper.writeValueAsString(person);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        assertEquals(person.getFirstName(), jsonNode.get("firstName").asText());
        assertEquals(person.getLastName(), jsonNode.get("lastName").asText());
        assertEquals(address.getStreet(), jsonNode.get("address").get("street").asText());
        assertEquals(address.getCity(), jsonNode.get("address").get("city").asText());
        assertEquals(address.getState(), jsonNode.get("address").get("state").asText());
        assertEquals(address.getZip(), jsonNode.get("address").get("zip").asInt());
        assertEquals(address.getCountry(), jsonNode.get("address").get("country").asText());
    }

    private ObjectMapper registerJdkModuleAndGetMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        return objectMapper;
    }
}