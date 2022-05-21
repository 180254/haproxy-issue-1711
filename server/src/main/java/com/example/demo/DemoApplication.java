package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  JettyServerCustomizer h2cJettyServerCustomizer() {
    return server -> {
      ServerConnector connector = (ServerConnector) server.getConnectors()[0];
      HttpConfiguration httpConfiguration = connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();

      HTTP2CServerConnectionFactory http2cConnectionFactory = new HTTP2CServerConnectionFactory(httpConfiguration);

      List<ConnectionFactory> connectionFactories = new ArrayList<>(connector.getConnectionFactories());
      connectionFactories.add(http2cConnectionFactory);
      connector.setConnectionFactories(connectionFactories);
    };
  }

  @GetMapping("/")
  public String index() {
    return "Hello!\n";
  }

  @GetMapping("/healthcheck")
  public String healthcheck() {
    return "OK\n";
  }
}
