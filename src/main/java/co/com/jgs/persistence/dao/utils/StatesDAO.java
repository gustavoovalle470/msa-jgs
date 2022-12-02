package co.com.jgs.persistence.dao.utils;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.utils.States;
import co.com.jgs.bo.utils.StatesPK;

/**
 * Define el DAO de acceso a la entidad de estados.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad estados.                 |
 * ***********************************************************************************************************
 * |   1.1   | 22/04/2022 |  OvalleGA   | Definicion de busqueda por nombre y pais.							 |
 * ***********************************************************************************************************
 */
public interface StatesDAO extends CrudRepository<States, StatesPK> {
	
	/**
	 * Regresa todos los estado con un nombre.
	 * @param stateName el nombre del estado.
	 * @return los estados que tienen ese nombre.
	 */
	@Query("SELECT s FROM States s WHERE s.stateName = :stateName")
	public List<States> findBySateName(@Param("stateName") String stateName);
	
	/**
	 * Retorna todos los estados dado un pais.
	 * @param countryId el id del pais.
	 * @return todos los estados.
	 */
	@Query("SELECT s FROM States s WHERE s.statesPK.idCountry = :countryId")
	public List<States> findByCountry(@Param("countryId") Integer countryId);
}
