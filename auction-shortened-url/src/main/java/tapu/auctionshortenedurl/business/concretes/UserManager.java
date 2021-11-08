package tapu.auctionshortenedurl.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tapu.auctionshortenedurl.business.abstracts.UserService;
import tapu.auctionshortenedurl.dataAccess.UserDao;
import tapu.auctionshortenedurl.entities.User;
import tapu.auctionshortenedurl.entities.Dtos.SignupDto;

@Service
public class UserManager implements UserService{
	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public List<User> getUsers() {
		
		return userDao.findAll();
	}

	@Override
	public int signup(SignupDto signupDto) {
		User user=SignupDtoToUser(signupDto);
		var id=userDao.save(user).getId();
		return id;
	}
	
	
	private User SignupDtoToUser(SignupDto signupDto) {
		User user=new User();
		user.setPassword(signupDto.getPassword());
		user.setUserName(signupDto.getPassword());
		return user;
	}
	
	
}
