package bf.lonab.banqueback;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

import bf.lonab.banqueback.dao.CompteRepository;
import bf.lonab.banqueback.entites.Compte;
import bf.lonab.banqueback.entites.CompteCourant;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		BanqueBackApplication.class,
		Jsr310Converters.class
})
public class BanqueBackApplication implements CommandLineRunner{
@PostConstruct
void init() {
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
}
@Autowired
private CompteRepository compteRepository;
	public static void main(String[] args) {
		SpringApplication.run(BanqueBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	 /**	Compte c = new Compte(null, null, new Date(),25000d, "CE");
		this.compteRepository.save(c);*/
	}

}
