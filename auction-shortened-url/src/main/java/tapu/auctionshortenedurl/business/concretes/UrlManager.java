package tapu.auctionshortenedurl.business.concretes;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import tapu.auctionshortenedurl.business.abstracts.UrlService;
import tapu.auctionshortenedurl.dataAccess.UrlDao;

import tapu.auctionshortenedurl.entities.Url;
import tapu.auctionshortenedurl.entities.User;
import tapu.auctionshortenedurl.entities.Dtos.UrlAddDto;
import tapu.auctionshortenedurl.entities.Dtos.UrlOutDto;

@Service
public class UrlManager implements UrlService {

	private UrlDao urlDao;
	
	@Autowired
	public UrlManager(UrlDao urlDao) {
		super();
		this.urlDao = urlDao;
	}

	@Override
	public List<Url> getUrlsByUserId(int userId) {
		
		return urlDao.getByUser_Id(userId);
	}

	@Override
	public Url getByIdAndUserId(int id,int userId) {
		
		return urlDao.getByIdAndUser_Id(id, userId);
	}

	@Override
	public UrlOutDto addUrl(UrlAddDto urlDto, int userId) {
		
		if (CheckDuplicateUrl(urlDto.url, userId)) {
			var url=UrlDtoToUrl(urlDto,userId);
			var urlid=urlDao.save(url).getId();
			urlDao.save(url);
			return new UrlOutDto(urlid,url.getShortened());
		}
		return new UrlOutDto(0,"Daha önceden oluşturduğunuz link.");
		
		
	}

	@Override
	public void delete(Url url) {
		urlDao.delete(url);
		
	}
	
	private Url UrlDtoToUrl(UrlAddDto urlDto,int userId) {
		Url url=new Url();
		User user=new User();
		user.setId(userId);
		url.setShortened(generateShortUrl(urlDto.getUrl()));
		url.setUrl(urlDto.getUrl());
		url.user=user;
		return url;
	}
	
	
	private String generateShortUrl(String url) {
		url="http://localhost:8080/"+Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
		return url;
	}
	
	private boolean CheckDuplicateUrl(String url,int userId) {
		Url data=urlDao.getByUrlAndUser_Id(url, userId);
		if (data!=null) {
			return false;
		}else {
			return true;
		}
		
	}

}
