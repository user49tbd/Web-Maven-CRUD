package model;

public class Produto {
	private int id;
	private String nome;
	private double preco;
	private int	idMarca;
	private Marca mark;
	
	public Marca getMark() {
		return mark;
	}
	public void setMark(Marca mark) {
		this.mark = mark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", idMarca=" + idMarca + "]";
	}
	
}
