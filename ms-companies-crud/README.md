
## Netflix Eureka: 
Es un servidor de descubrimiento de servicios desarrollado por Netflix. Se utiliza en microservicios y arquitecturas distribuidas para facilitar la localización y gestión de servicios. Aquí tienes una descripción del cliente Eureka (Netflix Eureka Client):

## ¿Qué es Netflix Eureka Client?
Netflix Eureka Client es una biblioteca que permite que las aplicaciones Spring Boot se registren en un servidor de Eureka. 
Esto es útil en arquitecturas de microservicios para que cada servicio pueda descubrir y comunicarse con otros servicios sin necesidad de conocer sus ubicaciones exactas.

## Características Principales:

- Registro de Servicios: Las aplicaciones se registran en un servidor Eureka con su propia información de ubicación y estado.
- Descubrimiento de Servicios: Las aplicaciones pueden obtener una lista de instancias de otros servicios registrados en el servidor Eureka.
- Balanceo de Carga: Facilita el balanceo de carga entre instancias de servicios disponibles.
- Tolerancia a Fallos: Permite configuraciones que mejoran la resiliencia del sistema, como instancias de reserva en caso de fallos.

### Su pom.xml es:
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

| Spring Boot Generation | Release Train        |
|------------------------|----------------------|
| 3.3.x, 3.2.x           | 2023.0.x aka Leyton  |

### Es necesario su dependencyManagement cuando se usa spring cloud
```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-dependencies</artifactId>
      <version>${spring-cloud.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```
### configuración en el ymal.
```yaml
eureka:
  instance:
    instance-id: "${spring.application.name}:${random.value}"
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```
### configuración de la clase Main
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CompaniesCrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(CompaniesCrudApplication.class, args);
  }
}
```

