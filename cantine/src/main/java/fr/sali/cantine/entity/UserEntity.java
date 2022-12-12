package fr.sali.cantine.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The persistent class for the users database table.
 */
import java.util.List;
@Entity
@Table(name="users")

public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	@Column(nullable=false)
	private LocalDate birthday;
	@Column(name="creation_date")
	private LocalDate creationDate;
	@Column(precision=5, scale=2)
	private BigDecimal credit;
	@Column(nullable=false, length=255)
	private String email;
	@Column(nullable=false, length=300)
	private String password;
	@Column(length=16)
	private String phone;
	private Integer status;
	@Column(nullable=false, length=20)
	private String userfname;
	@Column(nullable=false, length=16)
	private String username;
	@OneToMany(mappedBy="user")
	private List<OrderEntity> commandes;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_has_role",joinColumns={ @JoinColumn(name="user_id", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="role_idrole", nullable=false)}
		)
	private List<RoleEntity> roles;

	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="userimage")
	private ImageEntity image;
	public UserEntity() {
	}
    public UserEntity(String username ,  String userfname ,  String  email , LocalDate birthday,  String password ){
		this.userfname = userfname;
		this.username = username ;
		this.email =  email ;
		this.birthday = birthday;
		this.password =  password ;
	}
	/******  Les Getters  Et Setters ***************/
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getBirthday() {
		return this.birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserfname() {
		return this.userfname;
	}

	public void setUserfname(String userfname) {
		this.userfname = userfname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<OrderEntity> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<OrderEntity> commandes) {
		this.commandes = commandes;
	}

	public OrderEntity addCommande(OrderEntity commande) {
		getCommandes().add(commande);
		commande.setUser(this);

		return commande;
	}

	public OrderEntity removeCommande(OrderEntity commande) {
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

	public ImageEntity getImage() {
		return this.image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	}

}