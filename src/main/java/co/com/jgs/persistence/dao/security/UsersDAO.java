package co.com.jgs.persistence.dao.security;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.security.Users;

/**
 * Define el DAO de acceso a la entidad usuario.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad sesiones de usuario.     |
 * ***********************************************************************************************************
 * |   1.1   | 19/01/2022 |  OvalleGA   | Adicion busqueda por estado de usuario.   						 |
 * ***********************************************************************************************************
 */
public interface UsersDAO extends CrudRepository<Users, String> {

	@Query("SELECT u FROM Users u WHERE u.userStatus.catalogsPK.itemId=:userStatus")
	public List<Users> findByStatus(@Param("userStatus") Integer userStatus);
}
