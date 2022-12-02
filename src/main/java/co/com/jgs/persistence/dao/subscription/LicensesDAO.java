package co.com.jgs.persistence.dao.subscription;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.subscription.Licenses;

/**
 * Define el DAO de acceso a la entidad de licencias.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad licencias.               |
 * ***********************************************************************************************************
 * |   1.1   | 08/06/2022 |  OvalleGA   | Adicion de operacion de busqueda de la licencia mas reciente.		 |
 * ***********************************************************************************************************
 */
public interface LicensesDAO extends CrudRepository<Licenses, String> {

	/**
	 * Retorna las licencias de una empresa.
	 * @param companyId el id de la empresa
	 * @return el listado de licencias de la empresa.
	 */
	@Query("SELECT l FROM Licenses l WHERE l.company.id=:companyId")
	public List<Licenses> findByCompanyId(@Param("companyId")Integer companyId);
	
	/**
	 * Retorna licencia mas reciente de una empresa.
	 * @param companyId el id de la empresa
	 * @return la licencia mas reciente.
	 */
	@Query("SELECT l FROM Licenses l WHERE l.company.id=:companyId AND l.start =(SELECT max(l2.start) FROM Licenses l2 where l2.company.id = l.company.id)")
	public Licenses findByCompanyIdMostRecent(@Param("companyId")Integer companyId);
}
