package co.com.jgs.service.security;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.security.AuditLogs;
import co.com.jgs.bo.system.Messages;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.security.AuditLogDAO;
import co.com.jgs.security.auditlog.model.JGSAuditData;
import co.com.jgs.service.system.MessageService;

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
@RequestMapping("auditlog/")
public class AuditLogService{

	@Autowired
	private MessageService messageService;
	@Autowired
	private AuditLogDAO auditlogDao;
	
	@PostMapping("registerAudit")
	public JGSOutput registerAudit(@RequestBody JGSAuditData auditData) {
		JGSOutput response = new JGSOutput();
        if(auditData.getOperation() != null && auditData.getInput() != null 
        && auditData.getOutput() != null && auditData.getUser() != null) {
        	AuditLogs auditLog = new AuditLogs();
            auditLog.setMethodInvoked(auditData.getOperation().getName());
            auditLog.setOperationDate(Calendar.getInstance().getTime());
            auditLog.setParametersInvoked(auditData.getInput()!=null?auditData.getInput():"Sin datos de entrada.");
            auditLog.setResponseMessage(auditData.getOutput().getMessageResponse()!=null?auditData.getOutput().getMessageResponse().getMessage():"Sin mensaje de respuesta.");
            auditLog.setSuccesOperation(""+auditData.getOutput().isSuccess());
            auditLog.setUsername(auditData.getUser());
            auditLog.setWsinvoked(auditData.getOperation().getServices().getName());
            auditlogDao.save(auditLog);
            response.setSuccessOperation(auditLog, (Messages)messageService.findById(13).getObjectToReturn());
        }else {
        	response.addMessage("Los datos de auditoria no son validos, no se generó registro de audita para esta operacion.");
        }
        return response;
	}

}
