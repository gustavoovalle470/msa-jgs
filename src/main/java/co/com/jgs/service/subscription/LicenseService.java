package co.com.jgs.service.subscription;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.subscription.Licenses;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.subscription.LicensesDAO;

/**
 * Define el servicio de acceso a la base de datos para el Licenses.
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
 * |   1.1   | 08/06/2022 |  OvalleGA   | Adicion de operacion de busqueda de la licencia mas reciente.		 |
 * ***********************************************************************************************************
 */
@RestController
@CrossOrigin
@RequestMapping("license/")
public class LicenseService{

	@Autowired
	private LicensesDAO licenseDao;
	
	@GetMapping("exist/{licenseId}")
	public JGSOutput existsById(@PathVariable("licenseId") String licenseId) {
		JGSOutput response = new JGSOutput();
		response.setSuccessOperation(licenseDao.existsById(licenseId));
		return response;
	}

	@GetMapping("get/{licenseId}")
	public JGSOutput findById(@PathVariable("licenseId") String licenseId) {
		JGSOutput response = new JGSOutput();
		Optional<Licenses>responseDB = licenseDao.findById(licenseId);
		if(responseDB.isPresent()) {
			response.setSuccessOperation(responseDB.get());
		}
		return response;
	}

}
