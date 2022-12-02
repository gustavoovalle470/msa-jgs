package co.com.jgs.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.business.service.enums.ServiceReturns;
import co.com.jgs.business.service.output.JGSOutput;
import co.com.jgs.service.system.OperationsService;

@Service
public class MSACoreService {
	
	@Autowired
	private OperationsService operationService;
	private static final String SERVICE_ID = "serviceid";
	private static final String OPERATION_ID = "operationid";
	
	public boolean validateInvoker(Map<String, String> headers) {
		boolean isValidInvocation = false;
		if(headers.containsKey(SERVICE_ID) && headers.containsKey(OPERATION_ID)) {
			try {
				JGSOutput response = operationService.findById(Integer.parseInt(headers.get(SERVICE_ID)), 
						Integer.parseInt(headers.get(OPERATION_ID)));
				if(response.isSuccess() && ((Operations)response.getObjectToReturn()).getActive() == 1) { 
					isValidInvocation = true;
				}else {
					for(String error : response.getErrorMessages()) {
						System.err.println(error);
					}
				}
			}catch (Exception e) {
				System.err.println("Ha ocurrido un error al tratar de leer los encabezados...");
				System.err.println(e.getLocalizedMessage());
			}
		}
		return isValidInvocation;
	}
	
	public void failedInvokerValidation(JGSOutput response) {
		response.addMessage(ServiceReturns.SECURITY_ERROR.getMessage());
		response.addMessage("La peticion recibida no fue realizada por un canal valido.");
	}
}
