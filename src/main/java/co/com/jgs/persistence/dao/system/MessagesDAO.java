package co.com.jgs.persistence.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.system.Messages;

/**
 * Define el DAO de acceso a la entidad de mensajes.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad mensajes.                |
 * ***********************************************************************************************************
 * |   1.1   | 01/02/2022 |  OvalleGA   | Definicion de operacion que obtiene el siguiente ID.				 |
 * |         |            |             | Definicion de operacion de busqueda por tipo						 |
 * ***********************************************************************************************************
 */
public interface MessagesDAO extends CrudRepository<Messages, Integer> {

	/**
	 * Devuelve el proximo ID de la tabla.
	 * @return el proximo ID.
	 */
	@Query("SELECT MAX(m.id)+1 FROM Messages m")
	public Integer getNextId();

	/**
	 * Retorna todos los mensajes que se encuentran bajo un tipo.
	 * @param messageType el tipo de mensaje a buscar.
	 * @return el listado de mensajes bajo el tipo.
	 */
	@Query("SELECT m FROM Messages m WHERE m.type=:messageType")
	public List<Messages> findByMessageType(@Param("messageType")String messageType);
}
