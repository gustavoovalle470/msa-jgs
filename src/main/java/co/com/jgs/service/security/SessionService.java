package co.com.jgs.service.security;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.security.Sessions;
import co.com.jgs.bo.security.SessionsPK;
import co.com.jgs.bo.system.Messages;
import co.com.jgs.bo.system.Moduleconfigurations;
import co.com.jgs.business.service.enums.ServiceReturns;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.security.SessionsDAO;
import co.com.jgs.service.MSACoreService;
import co.com.jgs.service.system.MessageService;
import co.com.jgs.service.system.ModuleConfigurationService;

/**
 * Define el servicio de acceso a la base de datos para el Sessions.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 14/03/2022 |  OvalleGA   | Definicion del servicio de acceso a la base de datos que reemplaza |
 * |         |            |  		    | El llamado directo a los DAO que puede producir errores de null    |
 * ***********************************************************************************************************
 */
@RestController
@CrossOrigin
@RequestMapping("session/")
public class SessionService extends MSACoreService{

	@Autowired
	private SessionsDAO sessionDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ModuleConfigurationService mcService;
	
	@GetMapping("validateSession/{username}/{sessionId}")
	public JGSOutput validateSession2(@RequestHeader Map<String, String> headers, @PathVariable("username") String username, @PathVariable("sessionId") String sessionId) {
		JGSOutput response = new JGSOutput();
		if(validateInvoker(headers)) {
			JGSOutput responseFind = findById(headers, username, sessionId);
			if(responseFind.isSuccess()) {
				Sessions session = (Sessions)responseFind.getObjectToReturn();
				response.addMessage("Fecha acltual del sistema: "+Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00")).getTime().getTime());
				response.addMessage("Como estaba: "+Calendar.getInstance().getTime().getTime());
				response.addMessage("Fecha de inicio de sesion: "+session.getStartIn().getTime());
				if(Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00")).getTime().getTime()-session.getStartIn().getTime()
	               >= ((Moduleconfigurations)mcService.findById(1).getObjectToReturn()).longValue()) {
	                response.addMessage(ServiceReturns.SECURITY_ERROR.getMessageAndCode());
	                response.setMessageResponse((Messages)messageService.findById(10).getObjectToReturn());
	                delete(headers, username, sessionId);
	            }else {
	                session.setStartIn(Calendar.getInstance().getTime());
	                save(headers, session);
	                response.setSuccessOperation(true, (Messages)messageService.findById(3).getObjectToReturn());
	            }
			}else {
				response.addMessage(ServiceReturns.SECURITY_ERROR.getMessageAndCode());
	            response.setMessageResponse((Messages)messageService.findById(11).getObjectToReturn());
			}
		}else {
			failedInvokerValidation(response);
		}
		return response;
	}

	@PostMapping("/save")
	public JGSOutput save(@RequestHeader Map<String, String> headers, @RequestBody Sessions session) {
		JGSOutput response = new JGSOutput();
		if(validateInvoker(headers)) {
			try {
				response.setSuccessOperation(sessionDao.save(session));
			}catch (Exception e) {
				response.addMessage(e.getLocalizedMessage());
			}
		}else {
			failedInvokerValidation(response);
		}
		return response;
	}

	@DeleteMapping("/delete/{username}/{sessionId}")
	public void delete(@RequestHeader Map<String, String> headers, @PathVariable("username") String username, @PathVariable("sessionId") String sessionId) {
		JGSOutput response = findById(headers, username, sessionId);
		if(validateInvoker(headers)) {
			if(response.isSuccess()) {
				Sessions session = (Sessions)response.getObjectToReturn();
				sessionDao.deleteById(session.getSessionsPK());
			}
		}else {
			failedInvokerValidation(response);
		}
	}

	@GetMapping("getSession/{username}/{sessionId}")
	public JGSOutput findById(@RequestHeader Map<String, String> headers, @PathVariable("username") String username, @PathVariable("sessionId") String sessionId) {
		JGSOutput response = new JGSOutput();
		if(validateInvoker(headers)) {
			Optional<Sessions>responseDB = sessionDao.findById(new SessionsPK(username, sessionId));
			if(responseDB.isPresent()) {
				response.setSuccessOperation(responseDB.get());
			}	
		}else {
			failedInvokerValidation(response);
		}
			return response;
	}
}
