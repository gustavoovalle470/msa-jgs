package co.com.jgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * Define la aplicacion SpringBoot a desplegar.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion la aplicacion de seguridad.							     |
 * ***********************************************************************************************************
 */
@SpringBootApplication
public class MsaJgsApplication {
	
	/**
	 * Inicia los servicios de seguridad.
	 * @param args los argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MsaJgsApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter("msajgs.pid"));
		springApplication.run(args);
	}
}
