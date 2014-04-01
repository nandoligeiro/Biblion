package br.com.biblion;


public class Livros {
	
	private int ref;
	private String abreviatura;
	private int testamento;
	private int classe;
	private String curto;
	private String genero;
	private String comprido;
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livros other = (Livros) obj;
		if (abreviatura == null) {
			if (other.abreviatura != null)
				return false;
		} else if (!abreviatura.equals(other.abreviatura))
			return false;
		if (classe != other.classe)
			return false;
		if (comprido == null) {
			if (other.comprido != null)
				return false;
		} else if (!comprido.equals(other.comprido))
			return false;
		if (curto == null) {
			if (other.curto != null)
				return false;
		} else if (!curto.equals(other.curto))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (ref != other.ref)
			return false;
		if (testamento != other.testamento)
			return false;
		return true;
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abreviatura == null) ? 0 : abreviatura.hashCode());
		result = prime * result + classe;
		result = prime * result
				+ ((comprido == null) ? 0 : comprido.hashCode());
		result = prime * result + ((curto == null) ? 0 : curto.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ref;
		result = prime * result + testamento;
		return result;
	}
	@Override
	public String toString() {
		return "Livros [ref=" + ref + ", abreviatura=" + abreviatura
				+ ", testamento=" + testamento + ", classe=" + classe
				+ ", curto=" + curto + ", genero=" + genero + ", comprido="
				+ comprido + "]";
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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
	public String getCurto() {
		return curto;
	}
	public void setCurto(String curto) {
		this.curto = curto;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getComprido() {
		return comprido;
	}
	public void setComprido(String comprido) {
		this.comprido = comprido;
	}
	public Livros(int ref, String abreviatura, int testamento, int classe,
			String curto, String genero, String comprido) {
		super();
		this.ref = ref;
		this.abreviatura = abreviatura;
		this.testamento = testamento;
		this.classe = classe;
		this.curto = curto;
		this.genero = genero;
		this.comprido = comprido;
	}
	public Livros() {
		super();
		// TODO Auto-generated constructor stub
	}

}
