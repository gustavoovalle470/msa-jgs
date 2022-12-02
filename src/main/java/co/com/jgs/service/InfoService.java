package co.com.jgs.service;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.security.auditlog.model.JGSAuditData;

/**
 * Define el servicio de acceso a la base de datos para el AuditLog.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 14/03/2022 |  OvalleGA   | Definicion del servicio de acceso a la base de datos que reemplaza |
 * |         |            |  		    | El llamado directo a los DAO que puede producir errores de null    |
 * ***********************************************************************************************************
 * |   1.1   | 30/09/2022 |  OvalleGA   | Correccion de RequestBody ya que estaba siempre llegando null.	 |
 * ***********************************************************************************************************
 */
@RestController
@CrossOrigin
@RequestMapping
public class InfoService{
	
	@GetMapping("/info")
	public JGSOutput info() {
		
		TimeZone timeZone = TimeZone.getTimeZone("UTC-05:00");
		Calendar calendar = Calendar.getInstance(timeZone);
		SimpleDateFormat simpleDateFormat = 
		       new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		simpleDateFormat.setTimeZone(timeZone);

		System.out.println("Time zone: " + timeZone.getID());
		System.out.println("default time zone: " + TimeZone.getDefault().getID());
		System.out.println();

		System.out.println("UTC:     " + simpleDateFormat.format(calendar.getTime()));
		System.out.println("Default: " + calendar.getTime());
		
		System.out.println("Long: " + calendar.getTime().getTime());
		
		JGSOutput response = new JGSOutput();
		
        response.setSuccessOperation("Microservicio central del aplicativo ALFRED");
        return response;
	}

}
