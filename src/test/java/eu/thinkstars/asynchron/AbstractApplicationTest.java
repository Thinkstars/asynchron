package eu.thinkstars.asynchron;

import java.util.Properties;

public abstract class AbstractApplicationTest {

    private final Properties properties;

    public AbstractApplicationTest() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("i18n/labels_de.properties"));
        } catch (Exception e) {
            throw new IllegalStateException("");
        }
    }

    public String readPropertyAsString(String property) {
        return properties.getProperty(property);
    }
}
