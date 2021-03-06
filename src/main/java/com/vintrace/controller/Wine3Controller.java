package com.vintrace.controller;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.vintrace.model.Composition;
import com.vintrace.model.Wine;
import com.vintrace.util.CompositionUtil;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Scope(value = "session")
@Component(value = "wine3Controller")
@ELBeanName(value = "wine3Controller")
@Join(path = "/wine3", to = "/product/wine3-form.jsf")
public class Wine3Controller {

	private Wine wine;
	private Composition composition;


	public Wine getWine() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		wine = gson.fromJson(new FileReader("src/main/resources/json/11YVCHAR002.json"), Wine.class);
		return wine;
	}

	public Composition getComposition() {

		composition = new Composition();
		composition.setYearBreakdownComposition(CompositionUtil.printYearBreakdown(wine));
		composition.setRegionBreakdownComposition(CompositionUtil.printRegionBreakdown(wine));
		composition.setYearAndVarietyBreakdownComposition(CompositionUtil.printYearAndVarietyBreakdown(wine));
		composition.setVarietyBreakdownComposition(CompositionUtil.printVarietyBreakdown(wine));
		return composition;
	}
}
