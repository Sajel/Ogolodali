package com.ogolodali.app.model;

import java.util.ArrayList;


@SuppressWarnings("serial")
public class TagsCategory extends ArrayList<Tag> {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TagsCategory(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean add(Tag object) {
		((Tag) object).setCategory(this);
		return super.add(object);
	}
}
