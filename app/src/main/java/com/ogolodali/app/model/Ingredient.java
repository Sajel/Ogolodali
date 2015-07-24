package com.ogolodali.app.model;


public class Ingredient implements Chooseable {


	private String id;


	private String name;
	
	private IngredientsCategory category;

	public IngredientsCategory getCategory() {
		return category;
	}

	public void setCategory(IngredientsCategory category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Ingredient that = (Ingredient) o;

		return id.equals(that.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return getName();
	}
}
