package ui.main;

import com.vaadin.annotations.*;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ui.view.MainView;

/**
 * Created by Vladimir on 23.12.2017.
 */
@SpringUI(path = MainUI.APPLICATION_PATH)
@Theme(MainUI.APPLICATION_NAME)
@Widgetset(MainUI.APPLICATION_NAME)
@PreserveOnRefresh
@Push
public class MainUI extends UI implements ViewChangeListener {

	public static final String APPLICATION_NAME = "testApp";
	public static final String APPLICATION_PATH = "admin";

	private final MenuBar adminMainMenu = new MenuBar();
	private final MenuBar managerMainMenu = new MenuBar();

	@Autowired
	private SpringViewProvider springViewProvider;
	@Autowired
	private SpringNavigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(ValoTheme.UI_WITH_MENU);

		final Panel display = new Panel();
		display.setSizeFull();

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(false);
		content.addComponents(adminMainMenu, managerMainMenu);
		content.addComponent(display);
		content.setExpandRatio(display, 1f);
		setContent(content);
		navigator.init(this, display);
		navigator.addProvider(springViewProvider);
		getUI().getNavigator().addViewChangeListener(this);
		getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
	}

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		adminMainMenu.setVisible(true);
		managerMainMenu.setVisible(true);
		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {

	}
}
