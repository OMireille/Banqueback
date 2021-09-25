package bf.lonab.banqueback.entites;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor
public class Role extends AbstractEntity {
	@Enumerated(EnumType.STRING)
	private RoleName nomr;
}
