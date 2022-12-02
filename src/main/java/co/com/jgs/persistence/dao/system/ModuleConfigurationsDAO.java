package co.com.jgs.persistence.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.system.Moduleconfigurations;

/**
 * Define el DAO de acceso a la entidad de configuraciones de modulo.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.2
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad configuraciones de modulo|
 * ***********************************************************************************************************
 * |   1.1   | 31/01/2022 |  OvalleGA   | Adicion busqueda por nombre.										 |
 * ***********************************************************************************************************
 * |   1.2   | 11/02/2022 |  OvalleGA   | Adicion busqueda por modulo										 |
 * ***********************************************************************************************************
 */
public interface ModuleConfigurationsDAO extends CrudRepository<Moduleconfigurations, Integer> {
	
	@Query("SELECT m FROM Moduleconfigurations m WHERE m.name=:moduleName")
	public Moduleconfigurations findByName(@Param("moduleName")String moduleName);
	
	@Query("SELECT m FROM Moduleconfigurations m WHERE m.module=:module")
	public List<Moduleconfigurations> findByModule(@Param("module")String module);
}
