package br.com.sistemasvendas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemasvendas.dao.UserDAO;
import br.com.sistemasvendas.model.User;
import br.com.sistemasvendas.util.Constants;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1l;

	private UserDAO userDAO;

	public void init() {
		userDAO = UserDAO.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request, response);

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String action = request.getParameter(Constants.ACTION_KEY);
		try {
			if(action == null || action.isEmpty()) {
				list(request, response);
			}
			else if (action.equalsIgnoreCase(Constants.NEW_ACTION)) {
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
			throws SQLException, IOException, ServletException{
		List<User> users = userDAO.get();
		request.setAttribute("users", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException,IOException, ServletException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		User selectedUser = userDAO.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", selectedUser);
		dispatcher.forward(request, response);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter(Constants.NAME_COL_NAME);
		String cpfOrCnpj = request.getParameter(Constants.CPF_OR_CNPJ_COL_NAME);
		
		User newUser = new User(name, cpfOrCnpj);
		
		userDAO.save(newUser);
		response.sendRedirect(request.getContextPath());
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		String name = request.getParameter(Constants.NAME_COL_NAME);
		String cpfOrCnpj = request.getParameter(Constants.CPF_OR_CNPJ_COL_NAME);
		
		User user = new User(id, name, cpfOrCnpj);
		userDAO.update(user);
		response.sendRedirect(request.getContextPath());
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		userDAO.delete(id);
		response.sendRedirect(request.getContextPath());
	}
}
