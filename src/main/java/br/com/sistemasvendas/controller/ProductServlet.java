package br.com.sistemasvendas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.pagestore.db.SpatialTreeIndex;

import br.com.sistemasvendas.dao.ProductDAO;
import br.com.sistemasvendas.dao.UserDAO;
import br.com.sistemasvendas.model.Product;
import br.com.sistemasvendas.model.User;
import br.com.sistemasvendas.util.Constants;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;

	private UserDAO userDAO;

	public void init() {
		userDAO = UserDAO.getInstance();
		productDAO = ProductDAO.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter(Constants.ACTION_KEY);
		try {
			if (action == null || action.isEmpty()) {
				list(request, response);
			} else if (action.equalsIgnoreCase(Constants.NEW_ACTION)) {
				showNewForm(request, response);
			} else if (action.equalsIgnoreCase(Constants.INSERT_ACTION)) {
				insert(request, response);
			} else if (action.equalsIgnoreCase(Constants.DELETE_ACTION)) {
				delete(request, response);
			} else if (action.equalsIgnoreCase(Constants.EDIT_ACTION)) {
				showEditForm(request, response);
			} else if (action.equalsIgnoreCase(Constants.UPDATE_ACTION)) {
				update(request, response);
			} else {
				list(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		User user = userDAO.get(userId);
		List<Product> products = productDAO.getByUserId(userId);

		request.setAttribute("products", products);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		User user = userDAO.get(userId);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));

		Product selectedProduct = productDAO.get(id);
		User selectedUser = userDAO.get(userId);

		request.setAttribute("user", selectedUser);
		request.setAttribute("product", selectedProduct);

		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String descricao = request.getParameter(Constants.DESCRICAO_COL_NAME);
		Double preco = Double.parseDouble(request.getParameter(Constants.PRECO_COL_NAME));

		int userId = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));

		User user = userDAO.get(userId);
		Product newProduct = new Product(descricao, preco);

		productDAO.save(newProduct);
		response.sendRedirect(request.getContextPath() + "/product?action=list&user_id=" + userId);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));

		String descricao = request.getParameter(Constants.DESCRICAO_COL_NAME);
		Double preco = Double.parseDouble(request.getParameter(Constants.PRECO_COL_NAME));

		Product productDB = productDAO.get(id);

		productDB.setDescricao(descricao);
		productDB.setPreco(preco);
		productDAO.update(productDB);
		response.sendRedirect(request.getContextPath() + "/product?action=list&user_id=" + userId);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));

		productDAO.delete(id);
		response.sendRedirect(request.getContextPath() + "/phone?action=list&user_id=" + userId);
	}

}
