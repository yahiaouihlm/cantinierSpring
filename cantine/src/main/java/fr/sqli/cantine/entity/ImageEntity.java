package fr.sqli.cantine.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@Table(name="image")
@NamedQuery(name="ImageEntity.findAll", query="SELECT i FROM ImageEntity i")
public class ImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer idimage;

	@Column(nullable=false)
	private byte[] image;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="image")
	private List<MenuEntity> menus;

	//bi-directional many-to-one association to Plat
	@OneToMany(mappedBy="image")
	private List<PlatEntity> plats;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="imageBean")
	private List<UserEntity> users;

	public ImageEntity() {
	}

	public Integer getIdimage() {
		return this.idimage;
	}

	public void setIdimage(Integer idimage) {
		this.idimage = idimage;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<MenuEntity> getMenus() {
		return this.menus;
	}

	public void setMenus(List<MenuEntity> menus) {
		this.menus = menus;
	}

	public MenuEntity addMenus(MenuEntity menus) {
		getMenus().add(menus);
		menus.setImage(this);

		return menus;
	}

	public MenuEntity removeMenus(MenuEntity menus) {
		getMenus().remove(menus);
		menus.setImage(null);

		return menus;
	}

	public List<PlatEntity> getPlats() {
		return this.plats;
	}

	public void setPlats(List<PlatEntity> plats) {
		this.plats = plats;
	}

	public PlatEntity addPlat(PlatEntity plat) {
		getPlats().add(plat);
		plat.setImage(this);

		return plat;
	}

	public PlatEntity removePlat(PlatEntity plat) {
		getPlats().remove(plat);
		plat.setImage(null);

		return plat;
	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public UserEntity addUser(UserEntity user) {
		getUsers().add(user);
		user.setImageBean(this);

		return user;
	}

	public UserEntity removeUser(UserEntity user) {
		getUsers().remove(user);
		user.setImageBean(null);

		return user;
	}

}