package tapu.auctionshortenedurl.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tapu.auctionshortenedurl.entities.Url;

public interface UrlDao extends JpaRepository<Url, Integer>{
	List<Url> getByUser_Id(int userId);
	Url getByIdAndUser_Id(int id,int userId);
	Url getByUrlAndUser_Id(String url,int userId);
	Url getByShortened(String shortenedUrl);
}
