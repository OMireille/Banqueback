package bf.lonab.banqueback.entites;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import bf.lonab.banqueback.audit.DateAudit;
import lombok.Getter;

@MappedSuperclass
@Getter

public class AbstractEntity extends DateAudit	 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Version
	protected Long version;
	
}
