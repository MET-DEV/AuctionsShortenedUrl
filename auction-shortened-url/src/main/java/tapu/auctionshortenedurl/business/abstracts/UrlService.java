package tapu.auctionshortenedurl.business.abstracts;

import java.util.List;

import tapu.auctionshortenedurl.entities.Url;
import tapu.auctionshortenedurl.entities.Dtos.UrlAddDto;
import tapu.auctionshortenedurl.entities.Dtos.UrlOutDto;

public interface UrlService {
	List<Url> getUrlsByUserId(int userId);
	Url getByIdAndUserId(int id,int userId);
	UrlOutDto addUrl(UrlAddDto urlDto, int userId);
	void delete(Url url);
}
