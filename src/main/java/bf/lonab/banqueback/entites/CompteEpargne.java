package bf.lonab.banqueback.entites;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private double taux;
    
    
}
