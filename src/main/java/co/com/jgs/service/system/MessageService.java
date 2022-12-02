package co.com.jgs.service.system;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.system.Messages;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.system.MessagesDAO;

/**
 * Define el servicio de acceso a la base de datos para el Messages.
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
@RequestMapping("message/")
public class MessageService{

	@Autowired
	private MessagesDAO messageDao;
	
	@GetMapping("get/{messageId}")
	public JGSOutput findById(@PathVariable("messageId") Integer messageId) {
		JGSOutput response = new JGSOutput();
		Optional<Messages> responseDB = messageDao.findById(messageId);
		if(responseDB.isPresent()) {
			response.setSuccessOperation(responseDB.get());
		}
		return response;
	}
	
}
