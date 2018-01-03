package controller;

import dto.OrderDTO;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OrderService;


@RestController
@RequestMapping(path = "order")
public class OrderController {


	@Autowired
	private OrderService orderService;


	@RequestMapping(method = RequestMethod.POST)
	public Order createOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.save(orderDTO.toEntity());
	}

	@RequestMapping(path = "/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity updateOrder(@PathVariable(name = "orderId", required = true) Integer id,
									  @RequestBody OrderDTO orderDTO) {
		if (!orderService.checkOnExist(id)) {
		}

		Order order = orderDTO.toEntity();
		order.setId(id);
		return ResponseEntity.ok(orderService.save(order));
	}

	@RequestMapping(path = "/{orderId}", method = RequestMethod.GET)
	public Order getById(@PathVariable(name = "orderId", required = true) Integer id) {
		return orderService.getById(id);
	}

	@RequestMapping(path = "/{orderId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteOrder(@PathVariable(name = "orderId", required = true) Integer id) {
		try {
			orderService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Throwable e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
