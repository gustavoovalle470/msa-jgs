package co.com.jgs.persistence.dao.subscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.subscription.Companies;

/**
 * Define el DAO de acceso a la entidad de compañias.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad compañias.               |
 * ***********************************************************************************************************
 */
public interface CompaniesDAO extends CrudRepository<Companies, Integer>{
	
	@Query("SELECT c FROM Companies c WHERE c.name =:companyName")
	public Companies findCompanyByName(@Param("companyName")String companyName);
}
