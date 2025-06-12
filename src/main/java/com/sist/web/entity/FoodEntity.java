package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_food")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodEntity {
	@Id
	private int fno;
	private String name;
	private String type;
	private String phone;
	private String address;
	private String theme;
	private String images;
	private String time;
	private String parking;
	private String content;
	private String price;
	private double score;
	private String poster;
}
