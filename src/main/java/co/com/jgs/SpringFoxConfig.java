package co.com.jgs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Define la configuracion de Swagger para los microservicios de seguridad.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 01/03/2022 |  OvalleGA   | Creacion de la configuracion de Swagger							 |
 * ***********************************************************************************************************
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	
    @Value("${JGS_ID_Service}")
    private int serviceId;
    
    @Value("${JGS_SERVICE_NAME}")
    private String serviceName;
	
    @Value("${JGS_SERVICE_VERSION}")
    private String version;
    
    @Value("${JGS_SERVICE_LAST_RELEASE}")
    private String lastRelease;
    
    
	/**
	 * Define la configuracion de Swagger.
	 * @return el Docket de configuracion
	 */
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("co.com.jgs"))              
          .paths(PathSelectors.any())     
          .build().apiInfo(apiInfo());                                           
    }
	
	/**
	 * Define la informacion de la API.
	 * @return la informacion.
	 */
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      serviceId+". "+serviceName, 
	      "Define los servicios de seguridad de JGS que le permite al sistema autenticar y auditar las operaciones.", 
	      version, 
	      "", 
	      "Gustavo Adolfo Ovalle Quintero",  
	      "El presente API se puede utilizar bajo licenciamiento previo. "
	      + "GoTaSystems © Todos los derechos reservados 2022. "
	      + "Fecha del ultimo lanzamiento "+lastRelease, 
	      "");
	}
}
