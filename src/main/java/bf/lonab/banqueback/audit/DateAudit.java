package bf.lonab.banqueback.audit;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //permet de remplir automatiquement les classses
@JsonIgnoreProperties(
		value = {"dateCreation","dateModification"})
public abstract class DateAudit implements Serializable {
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Instant dateCreation;
	@LastModifiedDate
	@Column(nullable = false)
	private Instant dateModification;

}
