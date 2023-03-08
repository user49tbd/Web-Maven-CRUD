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
import model.Produto;
import persistence.DaoC;
import persistence.MarcaDao;
import persistence.ProdutoDao;

/**
 * Servlet implementation class ProdutoS
 */
@WebServlet("/ProdutoS")
public class ProdutoS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProdutoS() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoC dc= new DaoC();
		MarcaDao mc = new MarcaDao();
		List<Marca>lm = new ArrayList();
		
		try {
			lm = mc.ListaM();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		RequestDispatcher rd =  request.getRequestDispatcher("produto.jsp");
		request.setAttribute("marcas", lm);
		rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("fid");
		String nome = request.getParameter("fnome");
		String preco = request.getParameter("fpro");
		String marca = request.getParameter("marca");
		String bt = request.getParameter("bt");
		
		String tx ="";
		
		Produto p = new Produto();
		Marca m= new Marca();
		
		DaoC dc= new DaoC();
		ProdutoDao pd = new ProdutoDao();
		MarcaDao mc = new MarcaDao();
		List<Produto> lp = new ArrayList();
		List<Marca>lm = new ArrayList();
		
		if(bt.equalsIgnoreCase("Atualizar") || bt.equalsIgnoreCase("Inserir")) {
			if(nome.equals("") || id.equals("") || preco.equals("") || marca.equals("0")) {
				tx = "invalido";
			}
			else {
				m.setId(Integer.parseInt(marca));
				p.setId(Integer.parseInt(id));
				p.setNome(nome);
				p.setPreco(Double.parseDouble(preco));
				p.setIdMarca(m.getId());
				if(bt.equalsIgnoreCase("Atualizar")) {
					try {
						pd.AtualizarP(p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tx = bt;
				}else {
					try {
						pd.InserirP(p);
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
					m.setId(Integer.parseInt(marca));
					p.setId(Integer.parseInt(id));
					p.setIdMarca(m.getId());
					if(bt.equalsIgnoreCase("Excluir")) {
						tx = bt;
						try {
							pd.Excluir(p);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							p = pd.BuscaP(p);
							m = p.getMark();
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
						lp = pd.ListaP();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
		try {
			lm = mc.ListaM();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		RequestDispatcher rd =  request.getRequestDispatcher("produto.jsp");
		request.setAttribute("marcas", lm);
		request.setAttribute("produtos", lp);
		request.setAttribute("produto", p);
		request.setAttribute("marca", m);
		request.setAttribute("val", tx);
		rd.forward(request, response);
		}
	}
}
