package bf.lonab.banqueback.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bf.lonab.banqueback.entites.Employe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter @NoArgsConstructor @AllArgsConstructor
public class UserPrincipal implements UserDetails{

	
	private static final long serialVersionUID = 1L;

	private Long id;
    private String nom;
    private String prenom;
    private String nomComplet;
    
    @JsonIgnore
    private String adresseMail;

    @JsonIgnore
    private String pwd;

    private Collection<? extends GrantedAuthority> authorities;
    
    public static UserPrincipal create(Employe e) {
        List<GrantedAuthority> authorities = e.getRoles()
        		.stream().map(role ->
                new SimpleGrantedAuthority(role.getNomr().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
        		e.getId(),
        		e.getNom(), 
        		e.getPrenom(),
        		e.getNomComplet(),
        		e.getEmail(), 
        		e.getPwd(), 
        		authorities);
    }

    @Override
    public String getUsername() {
        return adresseMail;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
