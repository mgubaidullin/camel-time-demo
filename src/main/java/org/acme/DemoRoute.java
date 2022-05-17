package org.acme;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DemoRoute extends EndpointRouteBuilder {

    @ConfigProperty(name = "timer.period", defaultValue = "1000")
    String period;

    Integer counter = 0;

    @Override

    public void configure() throws Exception {
        fromF("timer:foo?period=%s", period)
                .setBody(exchange -> "Incremented the counter: " + counter++)
                .to("log:cdi-example?showExchangePattern=false&showBodyType=false");
    }
}
