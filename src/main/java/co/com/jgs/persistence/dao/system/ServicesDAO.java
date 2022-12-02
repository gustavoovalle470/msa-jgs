package co.com.jgs.persistence.dao.system;

import co.com.jgs.bo.security.Services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Define el DAO de acceso a la entidad de servicios.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad servicios.               |
 * ***********************************************************************************************************
 * |   1.1   | 25/01/2022 |  OvalleGA   | Operacion para buscar servicios por modulo.   					 |
 * ***********************************************************************************************************
 */
public interface ServicesDAO extends CrudRepository<Services, Integer> {

	@Query("SELECT s FROM Services s WHERE s.moduleId.id=:moduleId")
	public List<Services> findByModule(@Param("moduleId")String moduleId);
}
