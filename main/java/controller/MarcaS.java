package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Marca;
import persistence.MarcaDao;

/**
 * Servlet implementation class MarcaS
 */
@WebServlet("/MarcaS")
public class MarcaS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String tx;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcaS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		tx="";
		String id = request.getParameter("fid");
		String nome = request.getParameter("fnome");
		String bt = request.getParameter("bt");
		Marca m = new Marca();
		MarcaDao mc = new MarcaDao();
		List<Marca> lm = new ArrayList();
		if(bt.equalsIgnoreCase("Atualizar") || bt.equalsIgnoreCase("Inserir")) {
			if(nome.equals("") || id.equals("")) {
				tx = "invalido";
			}
			else {
				m.setId(Integer.parseInt(id));
				m.setNome(nome);
				if(bt.equalsIgnoreCase("Atualizar")) {
					try {
						mc.AtualizarM(m);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tx = bt;
				}else {
					try {
						mc.InserirM(m);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tx = bt;
				}
			}
		}
		else {
			if(bt.equalsIgnoreCase("Excluir") || bt.equalsIgnoreCase("Buscar")) {
				if(id.equals("")) {
					tx = "invalido";
				}
				else {
					m.setId(Integer.parseInt(id));
					m.setNome(nome);
					if(bt.equalsIgnoreCase("Excluir")) {
						tx = bt;
						try {
							mc.ExcluirM(m);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							m = mc.BuscaM(m);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						tx = bt;
					}
				}
			}
			else {
				if(bt.equalsIgnoreCase("Listar")) {
					tx = bt;
					try {
						lm = mc.ListaM();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("marca.jsp");
		request.setAttribute("val", tx);
		request.setAttribute("lis", lm);
		request.setAttribute("marca", m.getNome());
		rd.forward(request, response);
	}
}
