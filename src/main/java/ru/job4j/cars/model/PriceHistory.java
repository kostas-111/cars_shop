package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "price_history")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class PriceHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private int id;

	@Column(name = "before", nullable = false)
	private Long before;

	@Column(name = "after")
	private Long after;

	@Column(name = "created", insertable = false, updatable = false)
	private LocalDateTime created;

	@OneToMany
	@JoinColumn(name = "post_id")
	private List<Post> posts = new ArrayList<>();
}
