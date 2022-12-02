package co.com.jgs.persistence.dao.utils;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.utils.Countries;

/**
 * Define el DAO de acceso a la entidad de paises.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.2
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad paises.                  |
 * ***********************************************************************************************************
 * |   1.1   | 22/04/2022 |  OvalleGA   | Adicion de operacion de busqueda por nombre de pais, codigo 		 |
 * |		 |			  |				| telefonico y generacion del proximo ID	 						 |
 * ***********************************************************************************************************
 * |   1.2   | 24/08/2022 |  OvalleGA   | Adicion de operacion de busqueda por ISO2 e ISO3			 		 |
 * ***********************************************************************************************************
 */
public interface CountriesDAO extends CrudRepository<Countries, Integer> {

	/**
	 * Retorna un pais dado su nombre.
	 * @param countryName el nombre del pais.
	 * @return el pais encontrado por su nombre, o null en caso contrario.
	 */
	@Query("SELECT country FROM Countries country WHERE country.country = :countryName")
	public Countries findByName(@Param("countryName") String countryName);
	
	/**
	 * Retorna un pais dado su codigo telefonico.
	 * @param phoneCode el codigo telefonico del pais.
	 * @return el pais encontrado por su codigo telefonico, o null en caso contrario.
	 */
	@Query("SELECT country FROM Countries country WHERE country.phoneCode = :phoneCode")
	public Countries findByPhoneCode(@Param("phoneCode") String phoneCode);
	
	/**
	 * Retorna un pais dado su codigo ISO2.
	 * @param iso2 el codigo ISO2 del pais.
	 * @return el pais encontrado por su codigo ISO2, o null en caso contrario.
	 */
	@Query("SELECT country FROM Countries country WHERE country.iso2 = :iso2")
	public Countries findByISO2(@Param("iso2") String iso2);
	
	/**
	 * Retorna un pais dado su codigo ISO3.
	 * @param iso3 el codigo ISO3 del pais.
	 * @return el pais encontrado por su codigo ISO3, o null en caso contrario.
	 */
	@Query("SELECT country FROM Countries country WHERE country.iso3 = :iso3")
	public Countries findByISO3(@Param("iso3") String iso3);
	
	/**
	 * Retorna el proximo ID de pais.
	 * @return el proximo ID de pais.
	 */
	@Query("SELECT  MAX(country.countryId)+1 FROM Countries country")
	public Integer getNextId();
}
