package bf.lonab.banqueback.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("RTR")
public class Retrait extends Operation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
