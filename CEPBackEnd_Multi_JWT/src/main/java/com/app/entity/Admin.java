package com.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * The persistent class for the admin_tbl database table.
 * 
 */
@Entity
@Table(name = "admin_tbl")
//@NoArgsConstructor
//@Getter
//@Setter
@ToString(exclude = "adminPassword")
@NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a")
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id", unique = true, nullable = false)
	private long adminId;

	@Column(name = "admin_firstname", nullable = false, length = 255)
	private String adminFirstname;

	@Column(name = "admin_lastname", nullable = false, length = 255)
	private String adminLastname;

	@Column(name = "admin_email", unique = true, nullable = false, length = 255)
	private String adminEmail;

	@Column(name = "admin_password", nullable = false, length = 255)
	private String adminPassword;

	final static public String role = "ROLE_ADMIN";

//	@Column( name = "admin_role", columnDefinition = "varchar(20) default 'ROLE_ADMIN'",nullable = false, updatable = false)
//	private String adminRole;

	// bi-directional many-to-one association to Master
	@OneToMany(mappedBy = "adminTbl", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("adminTbl")
//	@JsonBackReference
	private List<Master> masterTbls;

	@OneToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	private Login login;

	public Admin() {
	}

	public long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return this.adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminFirstname() {
		return this.adminFirstname;
	}

	public void setAdminFirstname(String adminFirstname) {
		this.adminFirstname = adminFirstname;
	}

	public String getAdminLastname() {
		return this.adminLastname;
	}

	public void setAdminLastname(String adminLastname) {
		this.adminLastname = adminLastname;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public List<Master> getMasterTbls() {
		return this.masterTbls;
	}

	public void setMasterTbls(List<Master> masterTbls) {
		this.masterTbls = masterTbls;
	}

	public Master addMasterTbl(Master masterTbl) {
		getMasterTbls().add(masterTbl);
		masterTbl.setAdminTbl(this);
		return masterTbl;
	}

	public Master removeMasterTbl(Master masterTbl) {
		getMasterTbls().remove(masterTbl);
		masterTbl.setAdminTbl(null);
		return masterTbl;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

//	public String getAdminRole() {
//		return adminRole;
//	}
//
//	public void setAdminRole(String adminRole) {
//		this.adminRole = adminRole;
//	}

}