package it.polito.tdp.corsi.model;

public class Studente {
	
	private Integer matricola;
	private String nome;
	private String cognome;
	private String corsoDiStudio;
	
	/**
	 * 
	 * @param matricola
	 * @param nome
	 * @param cognome
	 * @param corsoDiStudio
	 */
	public Studente(Integer matricola, String nome, String cognome, String corsoDiStudio) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.corsoDiStudio = corsoDiStudio;
	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCorsoDiStudio() {
		return corsoDiStudio;
	}

	public void setCorsoDiStudio(String corsoDiStudio) {
		this.corsoDiStudio = corsoDiStudio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
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
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return matricola + " - " + nome + " " + cognome;
	}

	
	
	
}
