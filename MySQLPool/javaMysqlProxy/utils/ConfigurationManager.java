package utils;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class ConfigurationManager {
    public static FileBasedConfiguration config;

    static {
        try {
            Parameters params =new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder=
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties().setFileName("commerce.properties"));
            config=builder.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        System.out.println(ConfigurationManager.config.getString(Constant.JDBC_URL));
    }
}
