package ui.view;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;
import service.BookService;

import javax.annotation.PostConstruct;

/**
 * Created by Vladimir on 23.12.2017.
 */
@UIScope
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends MVerticalLayout implements View {

	public static final String VIEW_NAME = "main";

	@Autowired
	private BookService bookService;

	/**
	 * Список книг
	 */
	private MGrid<Book> bookGrid = new MGrid<>(Book.class);


	@PostConstruct
	public void init() {
		withFullHeight();
		withFullWidth();
		add(new Header("Стартовая страница bitch").setHeaderLevel(2));

		add(bookGrid);
		setExpandRatio(bookGrid, 1f);
	}

	private void initBookGrid() {
		bookGrid.setSizeFull();
		bookGrid.setCaption("Книги");
		bookGrid.setContainerDataSource(new BeanItemContainer<>(Book.class));
		bookGrid.withProperties("id", "name", "author");

		bookGrid.getColumn("id")
				.setHeaderCaption("id");
		bookGrid.getColumn("name")
				.setHeaderCaption("Название");
		bookGrid.getColumn("author")
				.setHeaderCaption("Автор");

		bookGrid.addItemClickListener(event -> {
			if (event.isDoubleClick()) {
				//click listner
			}
		});
	}

	private void refresh() {
		bookGrid.setRows(bookService.showAll());
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		refresh();
	}
}
