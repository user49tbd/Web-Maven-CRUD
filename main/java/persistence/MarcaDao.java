package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Marca;

public class MarcaDao {
	private Connection c;
	public MarcaDao() {
		DaoC dc = new DaoC();
		
		try {
			c = dc.getC();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void InserirM(Marca m) throws SQLException {
		String sql = "INSERT INTO MARCA VALUES (?,?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getId());
		ps.setString(2, m.getNome());
		ps.execute();
		ps.close();
	}
	public void AtualizarM(Marca m) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("UPDATE MARCA ");
		sb1.append("SET MARCA.NOME = ? ");
		sb1.append("WHERE MARCA.ID = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1,m.getNome());
		ps.setInt(2, m.getId());
		ps.execute();
		ps.close();
	}
	public void ExcluirM(Marca m) throws SQLException {
		EXP(m);
		String sql ="DELETE MARCA WHERE MARCA.ID = ?  ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getId());
		ps.execute();
		ps.close();
	}
	public Marca BuscaM(Marca m) throws SQLException {
		String sql ="SELECT M.ID,M.NOME FROM MARCA M WHERE M.ID = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			m.setNome(rs.getString("NOME"));
		}
		return m;
	}
	public List<Marca> ListaM() throws SQLException{
		String sql = "SELECT M.ID,M.NOME FROM MARCA M";
		List<Marca> lm = new ArrayList();
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Marca m = new Marca();
			m.setId(rs.getInt("ID"));
			m.setNome(rs.getString("NOME"));
			lm.add(m);
		}
		return lm;
	}
	public void EXP(Marca m) throws SQLException {
		String str = "DELETE PRODUTO WHERE PRODUTO.ID_MARCA = ? ";
		PreparedStatement ps = c.prepareStatement(str);
		ps.setInt(1, m.getId());
		ps.execute();
		ps.close();
	}
}
