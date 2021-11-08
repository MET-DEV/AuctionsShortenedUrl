package tapu.auctionshortenedurl.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import tapu.auctionshortenedurl.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
