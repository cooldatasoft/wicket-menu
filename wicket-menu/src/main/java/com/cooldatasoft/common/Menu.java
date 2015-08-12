package com.cooldatasoft.common;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Represents an abstract menu.
 *
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 * 
 */
public abstract class Menu extends Panel implements IHeaderContributor {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for abstract Menu class.
	 * @param id wicket:id of the menu.
	 */
	public Menu(String id) {
		super(id);
	}

}
