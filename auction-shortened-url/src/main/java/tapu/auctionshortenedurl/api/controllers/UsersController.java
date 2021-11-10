package tapu.auctionshortenedurl.api.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import tapu.auctionshortenedurl.business.abstracts.UrlService;
import tapu.auctionshortenedurl.business.abstracts.UserService;
import tapu.auctionshortenedurl.entities.Url;
import tapu.auctionshortenedurl.entities.User;
import tapu.auctionshortenedurl.entities.Dtos.IdOutDto;
import tapu.auctionshortenedurl.entities.Dtos.ReturnRealUrlDto;
import tapu.auctionshortenedurl.entities.Dtos.SignupDto;
import tapu.auctionshortenedurl.entities.Dtos.UrlAddDto;
import tapu.auctionshortenedurl.entities.Dtos.UrlOutDto;

@RestController
@RequestMapping("/")
public class UsersController {

	private UserService userService;
	private UrlService urlService;
	@Autowired
	public UsersController(UserService userService,UrlService urlService) {
		super();
		this.userService = userService;
		this.urlService=urlService;
	}

	@GetMapping("user/getusers")
	public List<User> getAllUsers(){
		return userService.getUsers();
	}
	@PostMapping("user/signup")
	public IdOutDto signup(@RequestBody SignupDto signupDto) {
		return new IdOutDto(userService.signup(signupDto));
	}
	
	
	@RequestMapping(value = "user/{userid}/url/create",method = RequestMethod.POST)
	public UrlOutDto addUrl(@PathVariable("userid") String userId, @RequestBody UrlAddDto urlDto) {
		int userIdentity=Integer.parseInt(userId);
		return urlService.addUrl(urlDto,userIdentity);
	}
	@RequestMapping(value = "user/{userid}/url/list",method = RequestMethod.GET)
	public List<Url> getAllUrls(@PathVariable("userid")int userId){
		return urlService.getUrlsByUserId(userId);
	}
	@RequestMapping(value = "user/{userid}/url/detail/{urlid}",method = RequestMethod.GET)
	public Url getUrl (@PathVariable("userid") int userId,@PathVariable("urlid") int id){
		return urlService.getByIdAndUserId(id,userId);
	}
	@RequestMapping(value = "s/{shorturl}",method = RequestMethod.GET)
	public ReturnRealUrlDto getRealUrl(@PathVariable("shorturl") String shortUrl) {
		return urlService.getRealUrl(shortUrl);
	}
	@RequestMapping(value = "user/{userid}/url/detail/{urlid}",method = RequestMethod.DELETE)
	public void delete (@PathVariable("userid") int userId,@PathVariable("urlid") int id){
		Url url= urlService.getByIdAndUserId(id,userId);
		urlService.delete(url);
	}
	
}
