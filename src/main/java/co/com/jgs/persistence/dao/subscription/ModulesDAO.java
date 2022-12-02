package co.com.jgs.persistence.dao.subscription;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.subscription.Modules;

/**
 * Define el DAO de acceso a la entidad de modulos..
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad modulos.                 |
 * ***********************************************************************************************************
 */
public interface ModulesDAO extends CrudRepository<Modules, String> {

}
