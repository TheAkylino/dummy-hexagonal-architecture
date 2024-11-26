
## Netflix Eureka: 
Es un servidor de descubrimiento de servicios desarrollado por Netflix. Se utiliza en microservicios y arquitecturas distribuidas para facilitar la localización y gestión de servicios. Aquí tienes una descripción del cliente Eureka (Netflix Eureka Client):

## ¿Qué es Netflix Eureka Server ?
Netflix Eureka Server es una herramienta poderosa para la gestión y descubrimiento de servicios en entornos de microservicios, ayudando a mantener sistemas flexibles, escalables y robustos.

## Beneficios:

- Desacoplamiento: Los servicios no necesitan conocer las ubicaciones exactas de otros servicios
- Escalabilidad: Facilita la adición y eliminación de instancias de servicios en tiempo de ejecución.
- Resiliencia: Mejora la tolerancia a fallos mediante el uso de múltiples instancias de servicios.

### Su pom.xml es:
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
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
spring:
  application:
    name: registry-server
server:
  port: 8761
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```
### configuración de la clase Main
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class RegistryServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RegistryServerApplication.class, args);
  }
}
```

