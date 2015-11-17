package me.negotiatewith.app;

import com.google.inject.Stage;
import com.google.inject.persist.PersistFilter;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class MainApplication extends Application<AppConfiguration> {

    private GuiceBundle<AppConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
                        .addModule(new AppModule())
                        .enableAutoConfig("me.negotiatewith.app")
                        .setConfigClass(AppConfiguration.class).build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new AssetsBundle("/public", "/health", "appstatus.html", "health"));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        environment.servlets().addFilter("persistFilter", guiceBundle.getInjector()
                        .getInstance(PersistFilter.class))
                        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}
