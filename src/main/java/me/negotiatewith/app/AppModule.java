package me.negotiatewith.app;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.sun.jersey.api.client.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class AppModule extends AbstractModule {

    private final Properties properties = createProperties();
    private final String JPA_UNIT = "my-app";

    @Override
    protected void configure() {
        install(new JpaPersistModule(JPA_UNIT).properties(properties));
    }

    @Provides
    private Client providesJerseyClient() {
        return Client.create();
    }

    @Provides
    private Gson providesGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private Properties createProperties() {
        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost/db_negotiate_with_me?autoReconnect=true");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "");
        return properties;
    }
}
