package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Note extends BaseEntity{
	private String title;
	private String description;

	@UpdateTimestamp
	private LocalDate updateDate;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	@JsonProperty(access = Access.READ_ONLY)
	private User user;

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public LocalDate getUpdateDate() {
//		return updateDate;
//	}
//
//	public void setUpdateDate(LocalDate updateDate) {
//		this.updateDate = updateDate;
//	}
//
//	@Override
//	public String toString() {
//		return "Note [title=" + title + ", description=" + description + ", updateDate=" + updateDate + "]";
//	}
}