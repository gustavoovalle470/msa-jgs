package co.com.jgs.persistence.dao.security;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.security.Sessions;
import co.com.jgs.bo.security.SessionsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Define el DAO de acceso a la entidad de sesiones de usuario.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad de usuario.              |
 * ***********************************************************************************************************
 */
public interface SessionsDAO extends CrudRepository<Sessions, SessionsPK> {
    /**
     * Retorna la sesion de usuario asociada a un id.
     * @param sessionId String el id de sesion.
     * @return Sessions la sesion de usuario.
     */
    @Query("SELECT s FROM Sessions s WHERE s.sessionsPK.sessionId=:sessionId")
    public Sessions findBySessionId(@Param("sessionId")String sessionId);
    
    /**
     * Retorna la sesion de usuario asociada a un usuario.
     * @param username String el nombre de usuario.
     * @return Sessions la sesion de usuario.
     */
    @Query("SELECT s FROM Sessions s WHERE s.sessionsPK.username=:username")
    public Sessions findByUsername(@Param("username")String username);
}
