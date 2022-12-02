package co.com.jgs.persistence.dao.utils;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.utils.Cities;

/**
 * Define el DAO de acceso a la entidad de ciudades.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad ciudades.                |
 * ***********************************************************************************************************
 * |   1.1   | 22/04/2022 |  OvalleGA   | Definicion de busqueda por estado y nombre.						 |
 * ***********************************************************************************************************
 */
public interface CitiesDAO extends CrudRepository<Cities, String> {
	
	/**
	 * Busca una ciudad dado su estado.
	 * @param stateId id del estado
	 * @param countryId id del pais
	 * @return el listado de ciudades del estado.
	 */
	@Query("SELECT c FROM Cities c WHERE c.states.statesPK.id = :stateId AND c.states.statesPK.idCountry = :countryId")
	public List<Cities> findByState(@Param("stateId") Integer stateId, @Param("countryId") Integer countryId);
	
	/**
	 * Busca las ciudades dado su nombre.
	 * @param cityName el nombre de la ciudad.
	 * @return las ciudades con el nombre indicado.
	 */
	@Query("SELECT c FROM Cities c WHERE c.city LIKE %:cityName%")
	public List<Cities> findByName(@Param("cityName") String cityName);
}
