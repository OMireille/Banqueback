package bf.lonab.banqueback.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bf.lonab.banqueback.dao.EmployeRepository;
import bf.lonab.banqueback.entites.Employe;




@Service
public class EmployeDetailsService implements UserDetailsService{
	

	    @Autowired
	    EmployeRepository employeRepository;

	    @Override
	    @Transactional
	    public UserDetails loadUserByUsername(String email)
	            throws UsernameNotFoundException {
	        // Let people login with either username or email
	        Employe e = employeRepository.findByEmail(email)
	                .orElseThrow(() -> 
	                        new UsernameNotFoundException("Utilisateur non disponible : " + email)
	        );

	        return UserPrincipal.create(e);
	    }

	    // This method is used by JWTAuthenticationFilter
	    @Transactional
	    public UserDetails loadUserById(Long id) {
	        Employe e = employeRepository.findById(id).orElseThrow(
	            () -> new UsernameNotFoundException("Utilisateur non disponible avec id : " + id)
	        );

	        return UserPrincipal.create(e);
	    }
}
