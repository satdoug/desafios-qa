package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

@Tag("all")
public class ApiTest {

    private static String name;
    private static String number;
    private static String birthday;

    @BeforeAll
    public static void setUp() {
        Properties props = new Properties();
        try {
            props.load(ApiTest.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = props.getProperty("validName");
        number = props.getProperty("validNumber");
        birthday = props.getProperty("validBirthday");
    }

    @Test
    public void testProperties() {
        System.out.println(String.format("%s - %s - %s",name, number, birthday));
    }
}
