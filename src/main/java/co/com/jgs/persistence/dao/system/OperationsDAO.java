package co.com.jgs.persistence.dao.system;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.bo.security.OperationsPK;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Define el DAO de acceso a la entidad de operaciones.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.2
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad operaciones.             |
 * ***********************************************************************************************************
 * |   1.1   | 31/01/2022 |  OvalleGA   | Adicion de operacion que retorna el proximo ID de operacion.       |
 * |         |            |             | Adicion de operacion que busca operaciones dado un servicio.       |
 * ***********************************************************************************************************
 * |   1.2   | 25/05/2022 |  OvalleGA   | Adicion de la busqueda por controlador.							 |
 * *********************************************************************************************************** 
 */
public interface OperationsDAO extends CrudRepository<Operations, OperationsPK> {

	/**
	 * Retorna el proximo ID de operacion, 1 en caso de no existir ninguna operacion asociada al servicio.
	 * @param serviceId el servicio al que pertenecera la operacion.
	 * @return el proximo ID.
	 */
	@Query("SELECT MAX(o.operationsPK.id)+1 FROM Operations o WHERE o.operationsPK.serviceId=:serviceId")
	public Integer getNexOperationId(@Param("serviceId")Integer serviceId);
	
	/**
	 * Retorna todas las operaciones que pertenecen a un servicio.
	 * @param serviceId el servicio al que perteneceran las operaciones.
	 * @return el listado de todas las operaciones del servicio.
	 */
	@Query("SELECT o FROM Operations o WHERE o.operationsPK.serviceId=:serviceId")
	public List<Operations> findByServiceId(@Param("serviceId")Integer serviceId);
	
	/**
	 * Retorna todas las operaciones que pertenecen a un controlador.
	 * @param controller el controlador de la operacion.
	 * @return el listado de todas las operaciones del controlador.
	 */
	@Query("SELECT o FROM Operations o WHERE o.controller =:controller")
	public List<Operations> findByController(@Param("controller")String controller);
	
	
}
