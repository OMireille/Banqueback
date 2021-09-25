package bf.lonab.banqueback.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
(name = "Type_Operation", discriminatorType = DiscriminatorType.STRING, length=3)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@Type(name = "VST", value = Versement.class),
	@Type(name = "RTR", value = Retrait.class)
})
public class Operation extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Date dateOperation;
	private double montant;
	@Column(name = "Type_Operation", insertable = false, updatable = false)
	private String type;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCompte")
	private Compte compte;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEmploye")
	private Employe employe;
}
