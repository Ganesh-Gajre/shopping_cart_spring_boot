package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Apparal")
public class Apparal extends Product {

	@Column(name = "apparel_Type")
	private String apparelType;

	@Column(name = "apparel_Brand")
	private String apparelBrand;

	@Column(name = "apparel_Design")
	private String apparelDesign;

	public String getApparelType() {
		return apparelType;
	}

	public void setApparelType(String apparelType) {
		this.apparelType = apparelType;
	}

	public String getApparelBrand() {
		return apparelBrand;
	}

	public void setApparelBrand(String apparelBrand) {
		this.apparelBrand = apparelBrand;
	}

	public String getApparelDesign() {
		return apparelDesign;
	}

	public void setApparelDesign(String apparelDesign) {
		this.apparelDesign = apparelDesign;
	}

}
