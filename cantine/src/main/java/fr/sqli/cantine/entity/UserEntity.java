package fr.sqli.cantine.entity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	private Date creationDate;

	@Column(nullable=false, precision=5, scale=2)
	private BigDecimal credit;

	@Column(length=255)
	private String email;

	@Column(nullable=false, length=300)
	private String password;

	@Column(length=16)
	private String phone;

	@Column(nullable=false, length=16)
	private String userfamilyname;

	@Column(nullable=false, length=16)
	private String username;

	//bi-directional many-to-one association to CommandeEntity
	@OneToMany(mappedBy="user")
	private List<CommandeEntity> commandes;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="user_has_role"
		, joinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_idrole", nullable=false)
			}
		)
	private List<RoleEntity> roles;

	//bi-directional many-to-one association to ImageEntity
	@ManyToOne
	@JoinColumn(name="image")
	private ImageEntity imageBean;

	public UserEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public BigDecimal getCredit() {
		return this.credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserfamilyname() {
		return this.userfamilyname;
	}

	public void setUserfamilyname(String userfamilyname) {
		this.userfamilyname = userfamilyname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CommandeEntity> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<CommandeEntity> commandes) {
		this.commandes = commandes;
	}

	public CommandeEntity addCommande(CommandeEntity commande) {
		getCommandes().add(commande);
		commande.setUser(this);

		return commande;
	}

	public CommandeEntity removeCommande(CommandeEntity commande) {
		getCommandes().remove(commande);
		commande.setUser(null);

		return commande;
	}

	public List<RoleEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public ImageEntity getImageBean() {
		return this.imageBean;
	}

	public void setImageBean(ImageEntity imageBean) {
		this.imageBean = imageBean;
	}

}