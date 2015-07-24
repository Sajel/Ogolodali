package com.ogolodali.app.model;

import java.util.ArrayList;

/**
 * ????????? ????????????
 */
@SuppressWarnings("serial")
public class IngredientsCategory extends ArrayList<Ingredient> {

    /**
     * ???????? ?????????
     */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IngredientsCategory(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean add(Ingredient object) {
		((Ingredient) object).setCategory(this);
		return super.add(object);
	}
}
