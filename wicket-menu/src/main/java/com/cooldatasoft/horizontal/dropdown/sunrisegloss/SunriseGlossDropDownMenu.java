package com.cooldatasoft.horizontal.dropdown.sunrisegloss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.cooldatasoft.common.DestinationType;
import com.cooldatasoft.common.MenuItem;
import com.cooldatasoft.common.StaticImage;

/**
 * http://www.cssmenumaker.com/builder/menu_info.php?menu=003
 * 
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 * 
 */
@Slf4j
public class SunriseGlossDropDownMenu extends Panel implements IHeaderContributor {

	private static final long serialVersionUID = 1L;

	private final static ResourceReference CSS_PATH = new CssResourceReference(SunriseGlossDropDownMenu.class,
			"css/SunriseGloss.css");
	private final static ResourceReference BG_LEFT_IMG = new PackageResourceReference(SunriseGlossDropDownMenu.class,
			"images/nav-bg-l.jpg");
	private final static ResourceReference BG_RIGHT_IMG = new PackageResourceReference(SunriseGlossDropDownMenu.class,
			"images/nav-bg-r.jpg");

	@Override
	public void renderHead(IHeaderResponse container) {
		container.render(CssHeaderItem.forReference(CSS_PATH));
	}

	public void processResponse(MenuItem menuItem) {
		switch (menuItem.getDestinationType()) {
		case EXTERNAL_LINK:
			// TODO forward to external link
			menuItem.getExternalLink();
			break;
		case WEB_PAGE_CLASS:
			setResponsePage(menuItem.getResponsePageClass());
			break;
		case WEB_PAGE_INSTANCE:
			setResponsePage(menuItem.getResponsePage());
			break;
		case AJAX_TARGET:
			menuItem.getAjaxLink().onClick();
			break;
		case NONE:
			break;
		default:
			// TODO Throw new exception
			throw new RuntimeException("Destination type not valid!");
		}
	}

	public SunriseGlossDropDownMenu(String id, List<MenuItem> menuItemList) {
		super(id);

		String bgLeftImgPath = RequestCycle.get().urlFor(BG_LEFT_IMG, null).toString();
		String bgRightImgPath = RequestCycle.get().urlFor(BG_RIGHT_IMG, null).toString();

		log.debug("bgLeftImg : {} ", bgLeftImgPath);
		log.debug("bgRightImg : {} ", bgRightImgPath);

		StaticImage bgLeftImage = new StaticImage("bgLeft", new Model<>(bgLeftImgPath));
		StaticImage bgRightImage = new StaticImage("bgRight", new Model<>(bgRightImgPath));

		bgLeftImage.add(new AttributeModifier("class", new Model<>("float-left")));
		bgRightImage.add(new AttributeModifier("class", new Model<>("float-right")));

		add(bgLeftImage);
		add(bgRightImage);

		ListView<MenuItem> primaryMenuListView = new ListView<MenuItem>("menuItem", menuItemList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<MenuItem> item) {
				final MenuItem menuItem = item.getModelObject();

				Link<Void> link;
				log.info("Menu : {} , Type : {} ", menuItem.getMenuText(), menuItem.getDestinationType());
				if (DestinationType.AJAX_TARGET == menuItem.getDestinationType()) {
					if ("menuLink".equals(menuItem.getAjaxLink().getId())) {
						throw new RuntimeException("MenuLink needs to have menuLink as wicket id!");
					}
					link = menuItem.getAjaxLink();
				} else {
					link = new Link<Void>("menuLink") {
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							processResponse(menuItem);
						}
					};
				}

<<<<<<< HEAD
				Label separator = new Label("menuSeparator");
				separator.add(new AttributeModifier("class", new Model<>("divider divider-vert")));
=======
				Label seperator = new Label("menuSeperator");
				seperator.add(new AttributeModifier("class", new Model<>("divider divider-vert")));
>>>>>>> f6c5b69... Removing explicit type param

				Label linkText = new Label("linkText");
				link.add(new AttributeModifier("class", new Model<>("item-primary")));

<<<<<<< HEAD
				if (menuItem.getMenuText() != null && !menuItem.isSeparator()) {
=======
				if (menuItem != null && menuItem.getMenuText() != null && !menuItem.isSeperator()) {
>>>>>>> f6c5b69... Removing explicit type param
					linkText.setDefaultModel(new Model<>(menuItem.getMenuText()));
					linkText.setRenderBodyOnly(true);
				}
				link.add(linkText);
				if (menuItem.isSeparator()) {
					link.setVisible(false);
				} else {
					separator.setVisible(false);
				}

				WebMarkupContainer subMenuListContainer = new WebMarkupContainer("subMenuListContainer");
				List<MenuItem> subMenuList = new ArrayList<>();
				if (menuItem.getSubMenuItemList() != null) {
					subMenuList = menuItem.getSubMenuItemList();
				}
				ListView<MenuItem> subMenuListView = new ListView<MenuItem>("subMenuItem", subMenuList) {
					private static final long serialVersionUID = 1L;

					@Override
					protected void populateItem(ListItem<MenuItem> item) {
						final MenuItem subMenuItem = item.getModelObject();

						Link<Void> subMenuLink;
						log.info("Submenu : {} , Type : {} ", subMenuItem.getMenuText(), subMenuItem.getDestinationType());
						if (DestinationType.AJAX_TARGET == subMenuItem.getDestinationType()) {
							if (!"subMenuLink".equals(subMenuItem.getAjaxLink().getId())) {
								throw new RuntimeException("Needs to have id as subMenuLink");
							}
							subMenuLink = subMenuItem.getAjaxLink();
						} else {
							subMenuLink = new Link<Void>("subMenuLink") {
								private static final long serialVersionUID = 1L;

								@Override
								public void onClick() {
									processResponse(subMenuItem);
								}
							};
						}

						Label subMenuSeparatorOrSecondaryTitle = new Label("subMenuSeparatorOrSecondaryTitle");

<<<<<<< HEAD
						if (subMenuItem.isSeparator()) {
							subMenuSeparatorOrSecondaryTitle.add(new AttributeModifier("class", new Model<>(
									"divider divider-horiz")));
						} else if (subMenuItem.isSubmenuTitle()) {
							subMenuSeparatorOrSecondaryTitle.add(new AttributeModifier("class", new Model<>(
									"item-secondary-title")));
							subMenuSeparatorOrSecondaryTitle.setDefaultModel(new Model<>(subMenuItem.getMenuText()));
						}

						Label subMenuLinkText = new Label("subMenuLinkText");
						if (subMenuItem.getMenuText() != null && !subMenuItem.isSeparator()) {
=======
						if (subMenuItem.isSeperator()) {
							subMenuSeperatorOrSecondaryTitle.add(new AttributeModifier("class", new Model<>(
									"divider divider-horiz")));
						} else if (subMenuItem.isSubmenuTitle()) {
							subMenuSeperatorOrSecondaryTitle.add(new AttributeModifier("class", new Model<>(
									"item-secondary-title")));
							subMenuSeperatorOrSecondaryTitle.setDefaultModel(new Model<>(subMenuItem.getMenuText()));
						}

						Label subMenuLinkText = new Label("subMenuLinkText");
						if (subMenuItem != null && subMenuItem.getMenuText() != null && !subMenuItem.isSeperator()) {
>>>>>>> f6c5b69... Removing explicit type param
							subMenuLinkText.setDefaultModel(new Model<>(subMenuItem.getMenuText()));
							subMenuLinkText.setRenderBodyOnly(true);
						}

						Iterator<Component> iterator = subMenuLink.iterator();
						boolean found = false;
						while (iterator.hasNext()) {
							Component next = iterator.next();
							if (subMenuLinkText.getId().equals(next.getId())) {
								found = true;
								break;
							}
						}
						if (!found) {
							subMenuLink.add(subMenuLinkText);
						}

						if (subMenuItem.isSeparator() || subMenuItem.isSubmenuTitle()) {
							subMenuLink.setVisible(false);
						} else {
							subMenuSeparatorOrSecondaryTitle.setVisible(false);
						}

						item.add(subMenuLink);
						item.add(subMenuSeparatorOrSecondaryTitle);
						item.add(subMenuSeparatorOrSecondaryTitle);
					}
				};
				subMenuListContainer.add(subMenuListView);
				if (menuItem.getSubMenuItemList() != null && menuItem.getSubMenuItemList().size() == 0) {
					subMenuListContainer.setVisible(false);
				}
				item.add(link);
				item.add(separator);
				item.add(subMenuListContainer);
			}
		};
		// primaryMenuListView.setReuseItems(true);
		add(primaryMenuListView);
	}
}
