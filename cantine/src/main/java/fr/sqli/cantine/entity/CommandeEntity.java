package fr.sqli.cantine.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@Table(name="commande")

public class CommandeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer idcommande;

	@Temporal(TemporalType.DATE)
	private Date datecreation;

	private Time heurecreation;

	@Column(nullable=false)
	private Integer statut;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private UserEntity user;

	//bi-directional many-to-many association to Menu
	@ManyToMany(mappedBy="commandes")
	private List<MenuEntity> menus;

	//bi-directional many-to-many association to Quantite
	@ManyToMany(mappedBy="commandes")
	private List<QuantiteEntity> quantites;

	public CommandeEntity() {
	}

	public Integer getIdcommande() {
		return this.idcommande;
	}

	public void setIdcommande(Integer idcommande) {
		this.idcommande = idcommande;
	}

	public Date getDatecreation() {
		return this.datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public Time getHeurecreation() {
		return this.heurecreation;
	}

	public void setHeurecreation(Time heurecreation) {
		this.heurecreation = heurecreation;
	}

	public Integer getStatut() {
		return this.statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<MenuEntity> getMenus() {
		return this.menus;
	}

	public void setMenus(List<MenuEntity> menus) {
		this.menus = menus;
	}

	public List<QuantiteEntity> getQuantites() {
		return this.quantites;
	}

	public void setQuantites(List<QuantiteEntity> quantites) {
		this.quantites = quantites;
	}

}