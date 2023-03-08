package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Marca;
import model.Produto;

public class ProdutoDao {
	private Connection c;
	public ProdutoDao() {
		DaoC dc = new DaoC();
		try {
			c = dc.getC();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void InserirP(Produto p) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("INSERT INTO PRODUTO VALUES ");
		sb1.append("(?,?,?,?)");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, p.getId());
		ps.setString(2, p.getNome());
		ps.setDouble(3, p.getPreco());
		ps.setInt(4, p.getIdMarca());
		ps.execute();
		ps.close();
	}
	public void AtualizarP(Produto p) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("UPDATE PRODUTO ");
		sb1.append("SET PRODUTO.NOME = ?,PRODUTO.PRECO=?,PRODUTO.ID_MARCA=? ");
		sb1.append("WHERE PRODUTO.ID = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, p.getNome());
		ps.setDouble(2, p.getPreco());
		ps.setInt(3, p.getIdMarca());
		ps.setInt(4, p.getId());
		ps.execute();
		ps.close();
	}
	public void Excluir(Produto p) throws SQLException {
		String sql ="DELETE PRODUTO WHERE PRODUTO.ID = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.execute();
		ps.close();
	}
	public Produto BuscaP(Produto p) throws SQLException {
		//String sql ="SELECT P.ID,P.NOME,P.PRECO,P.ID_MARCA FROM PRODUTO P WHERE P.ID = ?";
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT P.ID,P.NOME,P.PRECO,P.ID_MARCA,M.NOME AS MARCA_NOME FROM PRODUTO P ");
		sb1.append("INNER JOIN MARCA M ON M.ID = P.ID_MARCA WHERE P.ID = ?");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, p.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Marca m = new Marca();
			
			p.setNome(rs.getString("NOME"));
			p.setPreco(rs.getDouble("PRECO"));
			p.setIdMarca(rs.getInt("ID_MARCA"));
			
			m.setId(rs.getInt("ID_MARCA"));
			m.setNome(rs.getString("MARCA_NOME"));
			p.setMark(m);
		}
		System.out.println(p.getIdMarca()+" - "+p.getPreco());
		return p;
	}
	public List<Produto> ListaP() throws SQLException{
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT P.ID,P.NOME,P.PRECO,P.ID_MARCA,M.NOME AS MARCA_NOME FROM PRODUTO P ");
		sb1.append("INNER JOIN MARCA M ON M.ID = P.ID_MARCA");
		List<Produto> lp = new ArrayList();
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Produto p = new Produto();
			Marca m = new Marca();
			
			p.setId(rs.getInt("ID"));
			p.setNome(rs.getString("NOME"));
			p.setPreco(rs.getDouble("PRECO"));
			p.setIdMarca(rs.getInt("ID_MARCA"));
			
			m.setId(rs.getInt("ID_MARCA"));
			m.setNome(rs.getString("MARCA_NOME"));
			p.setMark(m);
			
			lp.add(p);
		}
		return lp;
	}
}
