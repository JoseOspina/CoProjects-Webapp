package org.collectiveone.modules.contexts.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.collectiveone.modules.users.AppUser;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "commits")
public class Commit {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator",
		parameters = { @Parameter( name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CommitGroup group;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AppUser author;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String message;
	
	@OneToMany
	private List<Commit> parents = new ArrayList<Commit>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 	
	private List<StagedElement> stagedElements = new ArrayList<StagedElement>();
	
	
	public Commit() {
		super();
	}
	
	public Commit(AppUser author) {
		super();
		this.author = author;
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public AppUser getAuthor() {
		return author;
	}

	public void setAuthor(AppUser author) {
		this.author = author;
	}
	
	public CommitGroup getGroup() {
		return group;
	}

	public void setGroup(CommitGroup group) {
		this.group = group;
	}

	public List<Commit> getParents() {
		return parents;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setParents(List<Commit> parents) {
		this.parents = parents;
	}

	public List<StagedElement> getStagedElements() {
		return stagedElements;
	}

	public void setStagedElements(List<StagedElement> stagedElements) {
		this.stagedElements = stagedElements;
	}
	
}