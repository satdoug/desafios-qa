package utilities;

import java.io.IOException;
import java.util.Properties;

public enum ProjectProperties {
    VALID_NAME("validName"),
    VALID_NUMBER("validNumber"),
    VALID_BIRTHDAY("validBirthday"),
    VALID_COMPANY_NUMBER("validCompanyNumber"),
    BASE_URL("base.url"),
    TOKEN("token");

    private final String value;

    ProjectProperties(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    public static String getProperty(ProjectProperties property) {
        try {
            Properties props = new Properties();
            props.load(ProjectProperties.class.getClassLoader().getResourceAsStream("application.properties"));
            return props.getProperty(property.getValue());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
