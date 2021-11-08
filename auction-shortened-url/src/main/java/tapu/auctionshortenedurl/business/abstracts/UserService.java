package tapu.auctionshortenedurl.business.abstracts;

import java.util.List;

import tapu.auctionshortenedurl.entities.User;
import tapu.auctionshortenedurl.entities.Dtos.SignupDto;

public interface UserService {
	List<User> getUsers();
	int signup(SignupDto signupDto);
	
}
