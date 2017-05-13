package services;

import database.AuthTokenDAO;
import database.UserDAO;
import model.Email;
import model.User;
import services.input.Input;
import services.input.RegisterInput;
import services.response.RegisterResponse;
import services.response.Response;

public class RegisterService implements Service {

	@Override
	public Response fillService(Input input) {
		RegisterInput data = null;
		if (input instanceof RegisterInput)
			data = (RegisterInput) input;
		else
			return new RegisterResponse("bad input");
		RegisterResponse response = null;
		Email email = null;
		
		//check for valid data
		try {
			email = new Email(data.getEmail());
		}
		catch (IllegalArgumentException e) {
			return new RegisterResponse("bad email format");
		}
		
		//generating user
		User user = new User(data.getFirstName(), data.getLastName(), data.getUserName(), Security.hash(data.getPassword()), email, null);
		UserDAO userDriver = new UserDAO();
		
		//saving the user
		boolean result = userDriver.saveUser(user);
		if (result == false)
			return new RegisterResponse("Database error occured");
		
		//saving the authToken
		AuthTokenDAO authTokenDriver = new AuthTokenDAO();
		boolean authTokenResult = authTokenDriver.saveAuthToken(user.getToken());
		if (authTokenResult == false) {
			userDriver.deleteUser(user.getUserName());
			return new RegisterResponse("Database error occured when creating authToken");
		}
			
		String id = user.getID().toString();
		String authToken = user.getToken().getValue().toString();
		String userName = user.getUserName();
		response = new RegisterResponse(userName, authToken, id);
		return response;
	}
	
	public static void main(String[] args) {
		RegisterInput input = new RegisterInput("tucker", "wilkes", "twilkes", "password", "email@email.com");
		RegisterService service = new RegisterService();
		
		System.out.println(service.fillService(input).toString());
	}

}
