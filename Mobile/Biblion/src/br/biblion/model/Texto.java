package br.biblion.model;


public class Texto {
	
	private int testamento;
	private int classe;
	private int livro;
	private int capitulo;
	private int versiculo;
	private String texto;
	private String curto;
	/*public Texto(String curto, int capitulo, int versiculo, String texto) {
		
		super();
		this.setCurto(curto);
		this.capitulo = capitulo;
		this.versiculo = versiculo;
		this.texto = texto;
		// TODO Auto-generated constructor stub
	}*/
	/*public Texto(int testamento, int classe, int livro, int capitulo,
			int versiculo, String texto) {
		super();
		this.testamento = testamento;
		this.classe = classe;
		this.livro = livro;
		this.capitulo = capitulo;
		this.versiculo = versiculo;
		this.texto = texto;
	}*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capitulo;
		result = prime * result + classe;
		result = prime * result + livro;
		result = prime * result + testamento;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + versiculo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Texto other = (Texto) obj;
		if (capitulo != other.capitulo)
			return false;
		if (classe != other.classe)
			return false;
		if (livro != other.livro)
			return false;
		if (testamento != other.testamento)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (versiculo != other.versiculo)
			return false;
		return true;
	}
	public int getTestamento() {
		return testamento;
	}
	public void setTestamento(int testamento) {
		this.testamento = testamento;
	}
	public int getClasse() {
		return classe;
	}
	public void setClasse(int classe) {
		this.classe = classe;
	}
	public int getLivro() {
		return livro;
	}
	public void setLivro(int livro) {
		this.livro = livro;
	}
	public int getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}
	public int getVersiculo() {
		return versiculo;
	}
	public void setVersiculo(int versiculo) {
		this.versiculo = versiculo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@Override
	public String toString() {
		return "Texto [testamento=" + testamento + ", classe=" + classe
				+ ", livro=" + livro + ", capitulo=" + capitulo
				+ ", versiculo=" + versiculo + ", texto=" + texto + "]";
	}
	public void setCurto(String curto) {
		this.curto = curto;
	}
	public String getCurto() {
		return curto;
	}
	
	

}
