package com.ogolodali.app.parser;


import com.ogolodali.app.model.Difficulty;
import com.ogolodali.app.model.Recipe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FullRecipeParser {

    public static String siteURL = "http://ogoloda.li";

    public Recipe parseRecipe(Recipe recipe) throws IOException {
        Document doc = Jsoup.connect(siteURL + recipe.getRef()).get();
        Elements ingTags = doc.select("li.ingredient");
        List<String> ingredients = new ArrayList<String>();
        for (Element li : ingTags) {
            ingredients.add(li.select("span.value").first().ownText() + " " + li.select("span.amount").first().ownText());
        }
        recipe.setIngridients(ingredients);
        Elements insTags = doc.select("div.instructions").first().children().select("li");
        LinkedList<String> instructions = new LinkedList<String>();
        for (Element tag : insTags)
            instructions.add(tag.ownText());
        recipe.setInstructions(instructions);
        return recipe;
    }
}
