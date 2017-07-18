package study_dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import study_dropwizard.health.TemplateHealthCheck;
import study_dropwizard.resources.HelloWorldResource;

public class DwApplication extends Application<DwConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DwApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard_01";
    }

    @Override
    public void initialize(final Bootstrap<DwConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DwConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
