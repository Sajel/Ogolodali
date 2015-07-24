package com.ogolodali.app.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Sajel
 */
public class Recipe_old {


	private String id;
	private String name;


	private Set<TagsCategory> categories;


	private String minTime;

	private String maxTime;

	private Difficulty difficulty;

	private int portionsCount;


	private List<String> requiredIngredients;

	private List<String> additionalIngredients;


	private List<String> instructions;


	private String source;


	private String imageUrl;


	private boolean compatible;

	public Recipe_old() {
		categories = new TreeSet<TagsCategory>();
		requiredIngredients = new LinkedList<String>();
		additionalIngredients = new LinkedList<String>();
		instructions = new LinkedList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TagsCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<TagsCategory> categories) {
		this.categories = categories;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public int getPortionsCount() {
		return portionsCount;
	}

	public void setPortionsCount(int portionsCount) {
		this.portionsCount = portionsCount;
	}

	public List<String> getRequiredIngredients() {
		return requiredIngredients;
	}

	public void setRequiredIngredients(List<String> requiredIngredients) {
		this.requiredIngredients = requiredIngredients;
	}

	public List<String> getAdditionalIngredients() {
		return additionalIngredients;
	}

	public void setAdditionalIngredients(List<String> additionalIngredients) {
		this.additionalIngredients = additionalIngredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isCompatible() {
		return compatible;
	}

	public void setCompatible(boolean compatible) {
		this.compatible = compatible;
	}
}
