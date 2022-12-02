package co.com.jgs.service.security;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.security.Users;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.persistence.dao.security.UsersDAO;
import co.com.jgs.service.MSACoreService;

/**
 * Define el servicio de acceso a la base de datos para el Users.
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
@RequestMapping("user/")
public class UserService extends MSACoreService{

	@Autowired
	private UsersDAO userDao;
	
	@GetMapping("getUser/{username}")
	public JGSOutput findById(@RequestHeader Map<String, String> headers, @PathVariable("username") String username) {
		JGSOutput response = new JGSOutput();
		if(validateInvoker(headers)) {
			Optional<Users>responseDB = userDao.findById(username);
			if(responseDB.isPresent()) {
				response.setSuccessOperation(responseDB.get());
			}
		}else {
			failedInvokerValidation(response);
		}
		return response;
	}
}
