package bf.lonab.banqueback.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor
@DiscriminatorValue ("VST")
public class Versement extends Operation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
