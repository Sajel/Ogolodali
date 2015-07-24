package com.ogolodali.app.parser;

import com.ogolodali.app.model.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class SearchParser {

    private List<IngredientsCategory> ingredientsCategories;

    private List<TagsCategory> tagsCategories;

    public static SearchParser parse() throws IOException {
        SearchParser instanse = new SearchParser();
        instanse.setIngredientsCategories(new LinkedList<IngredientsCategory>());
        instanse.setTagsCategories(new LinkedList<TagsCategory>());

        Document doc = Jsoup.connect("http://ogoloda.li/search/mobile").get();
        Elements elements;
        Document tmpDoc;

        Elements ingredientsTab = doc.select("div#ingredients-tab");
        tmpDoc = Jsoup.parse(ingredientsTab.html());
        elements = tmpDoc.select("a.category-button");
        for(Element element : elements) {
            instanse.getIngredientsCategories().add(new IngredientsCategory(element.text()));
        }
        elements = tmpDoc.select("div.well");
        int i = 0;
        for(Element element : elements) {
            Elements ingredientsElements = Jsoup.parse(element.html()).select("li");
            for (Element ingredientsElem : ingredientsElements) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientsElem.text());
                String id = Jsoup.parse(ingredientsElem.html()).select("a").first().attr("data-ingredient-id");
                ingredient.setId(id);
                instanse.getIngredientsCategories().get(i).add(ingredient);
            }
            i++;
        }

        i = 0;

        Elements tagsTab = doc.select("div#tags-tab");
        tmpDoc = Jsoup.parse(tagsTab.html());
        elements = Jsoup.parse(tagsTab.html()).select("a.category-button");
        for(Element element : elements) {
            instanse.getTagsCategories().add(new TagsCategory(element.text()));
        }
        elements = tmpDoc.select("div.well");
        for(Element element : elements) {
            Elements tagsElements = Jsoup.parse(element.html()).select("li");
            for (Element tagsElem : tagsElements) {
                Tag tag = new Tag();
                tag.setName(tagsElem.text());
                String id = Jsoup.parse(tagsElem.html()).select("a").first().attr("data-tag-id");
                tag.setId(id);
                instanse.getTagsCategories().get(i).add(tag);
            }
            i++;
        }

        return instanse;
    }

    public List<IngredientsCategory> getIngredientsCategories() {
        return ingredientsCategories;
    }

    public void setIngredientsCategories(List<IngredientsCategory> ingredientsCategories) {
        this.ingredientsCategories = ingredientsCategories;
    }

    public List<TagsCategory> getTagsCategories() {
        return tagsCategories;
    }

    public void setTagsCategories(List<TagsCategory> tagsCategories) {
        this.tagsCategories = tagsCategories;
    }
}
