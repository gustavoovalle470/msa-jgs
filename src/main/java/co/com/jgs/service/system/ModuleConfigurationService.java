package co.com.jgs.service.system;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.system.Moduleconfigurations;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.system.ModuleConfigurationsDAO;

/**
 * Define el servicio de acceso a la base de datos para el service.
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
@RequestMapping("mConfiguration/")
public class ModuleConfigurationService{

	@Autowired
	private ModuleConfigurationsDAO mcDao;
	
	@GetMapping("getByName/{mcName}")
	public JGSOutput findByName(@PathVariable("mcName") String mcName) {
		JGSOutput response = new JGSOutput();
		response.setSuccessOperation(mcDao.findByName(mcName));
		return response;
	}

	@GetMapping("get/{mcId}")
	public JGSOutput findById(@PathVariable("mcId") Integer mcId) {
		JGSOutput response = new JGSOutput();
		Optional<Moduleconfigurations> responseDB = mcDao.findById(mcId);
		if(responseDB.isPresent()) {
			response.setSuccessOperation(responseDB.get());
		}
		return response;
	}
	
}
