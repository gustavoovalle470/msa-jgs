package co.com.jgs.persistence.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.system.Catalogs;
import co.com.jgs.bo.system.CatalogsPK;

/**
 * Define el DAO de acceso a la entidad de catalogos.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad catalogos.               |
 * ***********************************************************************************************************
 * |   1.1   | 19/04/2022 |  OvalleGA   | Definicion de la operacion de busqueda por catalogo.				 |
 * ***********************************************************************************************************
 */
public interface CatalogsDAO extends CrudRepository<Catalogs, CatalogsPK> {
	
	/**
	 * Retorna todos los items asociados a un catalogo.
	 * @param catalogId el id del catalogo.
	 * @return todos los items asociados a un catalogo.
	 */
	@Query("SELECT cat FROM Catalogs cat WHERE cat.status = 1 AND cat.catalogsPK.catalog = :catalogId")
	public List<Catalogs> findByCatalogId(@Param("catalogId") Integer catalogId);
}
