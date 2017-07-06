# Spring-Cloud Template

This project provides a template for the basics of Spring-Cloud, a wrapper around
Netflix OSS for distributed, cloud-native systems.  Like with all Spring-Boot projects
any properties can be overriden with JVM parameters.

## Eureka 

Euraka is Netflix's service discovery tool. It can be started like so:

```bash
cd *eureka
mvn spring-boot:run
```

The Eureka dashboard can be viewed at http://localhost:8761

Eureka is also Amazon aware.  However that is not covered in this demo.

## Config Server

The Config server allows for a centralized place for application configuration.
The config server can store configuration via a git cloneable repository, svn, or 
natively on the fliesystem(using the "native" Spring profile).  The config server
can be started like so:

```bash
cd *config
mvn spring-boot:run
```

Applications using the config server client can refresh their configuration with
a POST to http://localhost:PORT/refresh

Note: Configuration injected into code on application start via `@Value` and `@Bean` as opposed
to Spring-Boot's `ConfigurationProperties` can only be refreshed if configuration
classes are annotated with `@RefreshScope`

## Zuul

Zuul is Netflix's API gateway and reverse proxy.  It can be use for a number of things including
authentication.  This can be shown in the `AuthFilter` class.  Zuul can be started like so:

```bash
cd *zuul
mvn spring-boot:run
```

Please refer to the Spring-Cloud link in Further Reading to learn about how Netflix uses Zuul.

## Hystrix

Hystrix is Netflix's implementation of the Circuit Breaker Pattern.  Used properly Hystrix
monitors dependent systems and adapts accordingly.  In addition, Hystrix
provides a monitoring dashboard for consuming Hystrix monitoring streams.  The Hystrix
dashboard can be started like so:

```bash
cd *hystrix
```

The Hystrix dashboard can be viewed at http://localhost:9000/hystrix

Applications that utilize Hystrix typically host their event streams at http://localhost:PORT/hystrix.stream


## Turbine

Turbine aggregates multiple hystrix streams into a single stream to be viewed on the Hystrix dashboard
Turbine can be started like so:

```bash
cd *turbine
mvn spring-boot:run
```

The turbine stream that can be used with the Hystrix dashboard can be found at http://localhost:9001/turbine.stream

## Applications (deployable demonstration applications)

The following are example application that demonstrate how to use different parts of Spring-Cloud

### CRUD
A simple web application that demonstrates a couple ways of consuming a Config Server.
It can be started like so.

```bash
cd *crud
mvn spring-boot:run
```

### Service
A web application the demonstrates how to use the `EurekaClient` to resolve application
dependencies for use with the `RestTemplate`. In addition, this service demonstrates how
to use Hystrix, exposing a Hystrix stream for use with the dashboard. For demonstration
purposes it is suggested that you start two instances of this service so that the
following application will take full use of client-side load-balance via its 
`@LoadBalanced` `RestTemplate` and Ribbon. This service can be started like so:

```bash
cd *service
mvn spring-boot:run -Dserver.port=<port> -Dservice.instance.name=<instance-name>
```

### App
This web appplication demonstrates how to utilized client-side load-balancing via a
`@LoadBalanced` `@RestTemplate`.  This done by either using Eureka service-discovery,
or by using Standalone Ribbon (Which Netflix OSS uses frequently under the hood).
The Ribbon implementation is current commented out.  In addition, this application
utilizes Hystrix like the service above and demonstrates how to hide an application
from service discovery (via `register-with-eureka=false`).  This application can be started like so:
 
```bash
cd *app
mvn spring-boot:run
```

Note:  If hidden from service discovery, the application will not be available from the
Zuul API gateway (using the configuration in this template)

## Further reading

[Spring Cloud Netflix](https://cloud.spring.io/spring-cloud-netflix/)

[Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html)

