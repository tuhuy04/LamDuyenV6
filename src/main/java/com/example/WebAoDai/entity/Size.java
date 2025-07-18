package com.example.WebAoDai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "size")
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "size_name", columnDefinition = "nvarchar(1111)")
	private String sizeName;

	@Column(name = "description", columnDefinition = "nvarchar(11111)")
	private String description;


	@Column(name = "created_At")
	private Date created_At;

	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonManagedReference
	private List<CategorySize> categorySizes;

	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonManagedReference

	private List<Order_Item> order_items;

	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonManagedReference

	private List<Cart> carts;
}
