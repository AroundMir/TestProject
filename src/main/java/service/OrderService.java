package service;

import DAO.BookDAO;
import DAO.OrderDAO;
import entity.Book;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private BookDAO bookDAO;


	@Transactional
	public void delete(Integer id) {
		orderDAO.delete(id);
	}

	@Transactional
	public Order save(Order order) {
		List<Book> books = new ArrayList<>();
		if (!CollectionUtils.isEmpty(order.getBooks())) {
			books = bookDAO.findByIds(order.getBooks().stream()
					.map(Book::getId)
					.collect(Collectors.toList()));
		}
		order.setBooks(books);
		return orderDAO.save(order);
	}

	public List<Order> findPersonOrders(Integer personId) {
		List<Order> allOrders = orderDAO.findAll();
		return allOrders.stream().filter(s -> s.getPerson().getId() == personId).collect(Collectors.toList());
	}

	public Order getById(Integer id) {
		return orderDAO.findOne(id);
	}

	public List<Order> showAll() {
		return orderDAO.findAll();
	}

	public boolean checkOnExist(Integer id) {
		return orderDAO.exists(id);
	}
}
