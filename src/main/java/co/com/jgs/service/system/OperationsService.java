package co.com.jgs.service.system;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.bo.security.OperationsPK;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.system.OperationsDAO;

/**
 * Define el servicio de acceso a la base de datos para el Operations.
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
 * |   1.1   | 25/05/2022 |  OvalleGA   | Adicion de la busqueda por controlador.							 |
 * ***********************************************************************************************************
 */
@RestController
@CrossOrigin
@RequestMapping("operation/")
public class OperationsService{

	@Autowired
	private OperationsDAO operationDao;
	
	@GetMapping("getByController/{controllerName}")
	public JGSOutput findByController(@PathVariable("controllerName") String controllerName) {
		JGSOutput response = new JGSOutput();
		response.setSuccessOperation(operationDao.findByController(controllerName));
		return response;
		
	}

	@GetMapping("exist/{serviceId}/{operationId}")
	public JGSOutput existsById(@PathVariable("serviceId") Integer serviceId, @PathVariable("operationId") Integer operationId) {
		JGSOutput response = new JGSOutput();
		response.setSuccessOperation(operationDao.existsById(new OperationsPK(operationId, serviceId)));
		return response;
	}

	@GetMapping("get/{serviceId}/{operationId}")
	public JGSOutput findById(@PathVariable("serviceId") Integer serviceId, @PathVariable("operationId") Integer operationId) {
		JGSOutput response = new JGSOutput();
		Optional<Operations> responseDB = operationDao.findById(new OperationsPK(operationId, serviceId));
		if(responseDB.isPresent()) {
			response.setSuccessOperation(responseDB.get());
		}else {
			response.addMessage("La operacion "+operationId+" del servicio "+serviceId+" no pudo ser localizada.");
		}
		return response;
	}
		
}
