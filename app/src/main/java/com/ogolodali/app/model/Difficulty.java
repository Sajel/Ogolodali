package com.ogolodali.app.model;

/**
 * 
 * @author Sajel
 * Перечисление сложностей рецепта
 */
public enum Difficulty {

	ELEMENTARY("элементарно"), EASY("легко"), NOT_SO_EASY("не так уж легко"),
    HAVE_TO_SWEAT("придется попотеть"), HARD("сложно"), INCREDIBLE("запредельно");

    private final String name;

    Difficulty(String name){
        this.name = name;
    }

    public String getName() {
		return name;
	}
}
