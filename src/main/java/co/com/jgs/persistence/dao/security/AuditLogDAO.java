package co.com.jgs.persistence.dao.security;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.jgs.bo.security.AuditLogs;

/**
 * Define el DAO de acceso a la entidad logs de auditoria.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.1
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |                   COMENTARIOS					     				 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion del DAO de acceso a la entidad logs de auditoria.       |
 * ***********************************************************************************************************
 * |   1.1   | 12/05/2022 |  OvalleGA   | Definicion operaciones de recuperacion de logs de auditoria.       |
 * ***********************************************************************************************************
 */
public interface AuditLogDAO extends CrudRepository<AuditLogs, Integer> {
	
	/**
	 * Retorna todos los logs de auditoria para un usuario.
	 * @param username el nombre de usuario.
	 * @return los logs de auditoria.
	 */
	@Query("SELECT audit FROM AuditLogs audit WHERE audit.username.username = :username")
	public List<AuditLogs> getAuditLogByUser(@Param("username") String username);
    
	/**
	 * Retorna todos los logs de auditoria para un WS.
	 * @param wsName el nombre del WS.
	 * @return los logs de auditoria.
	 */
	@Query("SELECT audit FROM AuditLogs audit WHERE audit.wsinvoked = :wsName")
    public List<AuditLogs> getAuditLogByWs(@Param("wsName") String wsName);
    
	/**
	 * Reotrna todos los logs de auditoria para una operacion especifica de un WS.
	 * @param wsName el nombre del WS.
	 * @param operation el nombre de la operacion.
	 * @return los logs de auditoria.
	 */
	@Query("SELECT audit FROM AuditLogs audit WHERE audit.wsinvoked =:wsName AND audit.methodInvoked =:operation")
    public List<AuditLogs> getAuditLogByWsAndOperation(@Param("wsName") String wsName, @Param("operation") String operation);
    
	/**
	 * Retorna todos los logs de auditoria dada una fecha de operacion.
	 * @param operationDate la fecha de operacion.
	 * @return los logs de auditoria.
	 */
	@Query("SELECT audit FROM AuditLogs audit WHERE DATE_FORMAT(audit.operationDate, '%Y%m%d') = :operationDate")
    public List<AuditLogs> getAuditLogByDate(@Param("operationDate") String operationDate);
}
