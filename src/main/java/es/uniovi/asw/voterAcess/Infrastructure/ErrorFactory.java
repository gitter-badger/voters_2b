package es.uniovi.asw.voterAcess.Infrastructure;

import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.InvalidPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.PasswordsDontMatchErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredUserErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UnknownErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UserNotFoundErrorResponse;


/**
 * Crea un error diferente según la causa que lo haya provocado
 *
 */
public class ErrorFactory
{
	public static enum Errors {USER_NOT_FOUND, INVALID_PASSWORD, PASSWORDS_DONT_MATCH, REQUIRED_USER, REQUIRED_PASSWORD, UNKNOWN_ERROR};
	
	
	/**
	 * No tiene sentido que se creen instancias de la factoría. Su función
	 * sólo es devolver el error apropiado para la causa que se le indique.
	 * 
	 */
	private ErrorFactory()
	{
		
	}
	
	
	public static ErrorResponse getErrorResponse(Errors causaError)
	{
		switch (causaError)
		{
			case USER_NOT_FOUND:
				return new UserNotFoundErrorResponse();
			
			case INVALID_PASSWORD:
				return new InvalidPasswordErrorResponse();
			
			case PASSWORDS_DONT_MATCH:
				return new PasswordsDontMatchErrorResponse();
			
			case REQUIRED_USER:
				return new RequiredUserErrorResponse();
			
			case REQUIRED_PASSWORD:
				return new RequiredPasswordErrorResponse();
			
			default:	// UNKNOWN_ERROR (Se desconoce la causa del error)
				return new UnknownErrorResponse();
		}
	}
}