package com.ogolodali.app.model;

/**
 * Created by Sajel on 28.06.2015.
 */
public class Tag implements Chooseable {

    /**
     */
    private String id;

    /**
     */
    private String name;

    private TagsCategory category;

    public TagsCategory getCategory() {
        return category;
    }

    public void setCategory(TagsCategory category) {
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

        Tag tag = (Tag) o;

        return id.equals(tag.id);

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
