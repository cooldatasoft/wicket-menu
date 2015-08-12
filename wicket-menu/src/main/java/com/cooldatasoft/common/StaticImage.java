package com.cooldatasoft.common;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

/**
 * Represents a static image used by the menu
 *
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 * 
 */
public class StaticImage extends WebComponent {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor for StaticImage
	 * @param id wicket id
	 * @param model model
	 */
	public StaticImage(String id, IModel<String> model) {
		super(id, model);
	}

	/**
	 * Defines what to do on component tag
	 * @param tag tag
	 */
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		checkComponentTag(tag, "img");
		tag.put("src", getDefaultModelObjectAsString());
	}

}
