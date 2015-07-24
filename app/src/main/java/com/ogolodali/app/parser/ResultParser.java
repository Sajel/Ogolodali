package com.ogolodali.app.parser;

import com.ogolodali.app.AsynkTasks.ParseListAsyncTask;
import com.ogolodali.app.model.Difficulty;
import com.ogolodali.app.model.Recipe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultParser {

    private ParseListAsyncTask asyncTask;

    private List<Recipe> compList;
    private List<Recipe> uncompList;

    public ResultParser(ParseListAsyncTask asyncTask){
        this.asyncTask = asyncTask;
    }

    public void parseList(String url) throws IOException {
        Document doc  = Jsoup.connect(url).get();
        Elements compatible = doc.getElementsByClass("compatible");
        Elements uncompatible = doc.getElementsByClass("uncompatible");
        Recipe recipe;
        compList = new ArrayList<Recipe>();
        for(Element el: compatible){
            recipe = parseRecipe(el);
            recipe.setCompatible(true);
            compList.add(recipe);
        }
        asyncTask.comeElement((Recipe[])compList.toArray());
        uncompList = new ArrayList<Recipe>();
        for(Element el: uncompatible){
            recipe = parseRecipe(el);
            recipe.setCompatible(false);
            uncompList.add(recipe);
        }
    }

    public Recipe parseRecipe(Element element){
        Recipe recipe = new Recipe();
        Element nameEl = element.select("h4").select("a[href]").first();
        String name = nameEl.ownText();
        recipe.setName(name);
        String ref = nameEl.attr("href");
        recipe.setRef(ref);
        String time = element.select("span").select(".time").first().ownText();
        recipe.setCookTime(time);
        String dif = element.select("span").select(".difficulty").first().ownText();
        Difficulty difficulty = null;
        switch (dif){
            case "элементарно":
                difficulty = Difficulty.ELEMENTARY;
                break;
            case "легко":
                difficulty = Difficulty.EASY;
                break;
            case "не так уж и легко":
                difficulty = Difficulty.NOT_SO_EASY;
                break;
            case "придется попотеть":
                difficulty = Difficulty.HAVE_TO_SWEAT;
                break;
            case "сложно":
                difficulty = Difficulty.HARD;
                break;
            case "запредельно":
                difficulty = Difficulty.INCREDIBLE;
                break;
        }
        recipe.setDifficulty(difficulty);
        String portion = element.select("h5").select(":containsOwn(Ингредиенты)").first().ownText();
        int count = -1;
        if(portion.contains("одну"))
            count = 1;
        else
            count = Integer.parseInt(portion.split(" ")[2]);
        recipe.setPortionCount(count);
        String ingrid = element.select("h5").select(":containsOwn(Ингредиенты)").first().nextElementSibling().ownText();
        String [] Ingridients = ingrid.split(", ");
        ArrayList<String> Ingrid = new ArrayList<String>(Ingridients.length);
        for(String el : Ingridients)
            Ingrid.add(el);
        recipe.setIngridients(Ingrid);
        String missingStr = element.select("p").select(".compact-view-hide").first().ownText();
        String [] Missing = missingStr.split(", ");
        ArrayList<String> missing = new ArrayList<String>(Missing.length);
        for(String el : Missing)
            missing.add(el);
        recipe.setMissing(missing);
        Elements additional = element.select("h5").select(":containsOwn(Можно добавить)");
        if(!additional.isEmpty()) {
            String canAdd = additional.first().nextElementSibling().ownText();
            String[] CanAdd = canAdd.replaceAll(",", "").split(" ");
            ArrayList<String> canadd = new ArrayList<String>(Ingridients.length);
            for (String el : CanAdd)
                canadd.add(el);
            recipe.setCanAdd(canadd);
        }
        // парсінг тегів на всякий случай
        //Elements tags = element.select("span").select(".tags").first().children().select("a[href]");
        return recipe;
    }
}
