package com.cooldatasoft.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * Represents a MenuItem in a Menu
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 * 
 */

@Data
@Slf4j
public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Menu text that will be shown to the user
	 */
	private String menuText;

	/**
	 * If the result of the menu is set to a class, this property will be holding the value.
	 */
	private Class<? extends WebPage> responsePageClass;

	/**
	 * If the result of the menu is set to a WebPage instance, this property will be holding the value.
	 */
	private WebPage responsePage;

	/**
	 * If the result of the menu is set to an external link, this property will be holding the value.
	 */
	private String externalLink;

	/**
	 * If the result of the menu is set to an ajax link, this property will be holding the value.
	 */
	private Link<Void> ajaxLink;

	/**
	 * Holds the destination type of this menu item.
	 */
	private DestinationType destinationType;

	/**
	 * Holds the submenu list of this menu item, if any/applies
	 */
	private List<MenuItem> subMenuItemList = new ArrayList<MenuItem>();

	/**
	 * Marks this menu items as seperator if true.
	 */
	private boolean seperator = false;

	/**
	 * Marks this menu item, if it is a submenu title
	 */
	private boolean submenuTitle = false;

	/**
	 * Used to create a seperator inside the menu
	 * @param seperator true if this menu item is a separator.
	 */
	public MenuItem(boolean seperator) {
		if(!seperator){
			throw new IllegalArgumentException("This constuctor is for menu seperators only!");
		}
		log.trace("Creating MenuItem with seperator = {} ", seperator);
		setSeperator(true);
		setDestinationType(DestinationType.NONE);
	}

	/**
	 * Creates a menu item which executes the given ajaxLink when clicked.
	 * @param ajaxLink ajaxLink to be executed when the menu item is clicked.
	 */
	public MenuItem(Link<Void> ajaxLink) {
		setAjaxLink(ajaxLink);
		setDestinationType(DestinationType.AJAX_TARGET);
	}

	/**
	 * Creates a menu item which executes the given ajaxLink when clicked with the given menu text.
	 * @param menuText menu text that is going to be rendered.
	 * @param ajaxLink ajaxLink to be executed when the menu item is clicked.
	 */
	public MenuItem(String menuText, Link<Void> ajaxLink) {
		setMenuText(menuText);
		setAjaxLink(ajaxLink);
		setDestinationType(DestinationType.AJAX_TARGET);
	}

	/**
	 * Creates a menu item which updates the  given components via ajax.
	 * @param componentsToUpdate components to update when menu item is clickec
	 */
	public MenuItem(final Component... componentsToUpdate) {

		// TODO id needs to be on html
		setAjaxLink(new AjaxFallbackLink<Void>("menuLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				target.add(componentsToUpdate);
			}
		});
		setDestinationType(DestinationType.AJAX_TARGET);
	}

	/**
	 * Creates a Menu item that is used as parent for submenu items.
	 * @param submenuTitle title of this menu item
	 */
	public MenuItem(String submenuTitle) {
		setSubmenuTitle(true);
		setMenuText(submenuTitle);
		setDestinationType(DestinationType.NONE);
	}

	/**
	 * Creates a menu items with given menu text and destination page.
	 * @param menuText menu text
	 * @param destinationPage destination page
	 * @param <T> A WebPage or sublass of WebPage instance
	 */
	public <T extends WebPage> MenuItem(String menuText, T destinationPage) {
		setMenuText(menuText);
		setResponsePage(destinationPage);
		setSubMenuItemList(new ArrayList<MenuItem>());
		setDestinationType(DestinationType.WEB_PAGE_INSTANCE);
	}

	/**
	 * Creates menu item with given menu text and destination class.
	 * @param menuText menu text
	 * @param destinationPageClass destination page class
	 */
	public MenuItem(String menuText, Class<? extends WebPage> destinationPageClass) {
		setMenuText(menuText);
		setResponsePageClass(destinationPageClass);
		setSubMenuItemList(new ArrayList<MenuItem>());
		setDestinationType(DestinationType.WEB_PAGE_CLASS);
	}

	/**
	 * Creates menu item with given menu text and destination web page class along with submenus passed
	 * @param menuText menu text
	 * @param destinationWebPage destination page class
	 * @param subMenuItemList submenu item list
	 * @throws InstantiationException thrown if the destination page class cannot be instantiated
	 * @throws IllegalAccessException thrown if the destination page class access is denied
	 */
	public MenuItem(String menuText, Class<? extends WebPage> destinationWebPage, List<MenuItem> subMenuItemList)
			throws InstantiationException, IllegalAccessException {
		this(menuText, destinationWebPage.newInstance(), subMenuItemList);
		setDestinationType(DestinationType.WEB_PAGE_CLASS);
	}

	/**
	 * Creates menu item with given menu text and destination web page instance along with submenus passed
	 * @param menuText menu text
	 * @param destinationPage destination page instace
	 * @param subMenuItemList submenu list
	 * @param <T> instance of WebPage or instance of subclass of WebPage
	 */
	public <T extends WebPage> MenuItem(String menuText, T destinationPage, List<MenuItem> subMenuItemList) {
		setMenuText(menuText);
		setResponsePage(destinationPage);
		setSubMenuItemList(subMenuItemList);
		setDestinationType(DestinationType.WEB_PAGE_INSTANCE);
	}

	/**
	 * Utility method that returns a menu seperator
	 * @return seperator menu item
	 */
	public static MenuItem getMenuSeperator() {
		return new MenuItem(true);
	}
}
