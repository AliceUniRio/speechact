package decisaocomatosdefala.atosdefala;

public abstract class AtoDeFala implements IAtoDeFala {

    protected String mensagem;
    protected String[] verbos;
	
	public AtoDeFala() {
	}

	public AtoDeFala(String mensagem, String[] verbos) {
		super();
		this.mensagem = mensagem;
		this.verbos = verbos;
	}

	public abstract Boolean isMessageAtoDeFala();

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String[] getVerbos() {
		return verbos;
	}

	public void setVerbos(String[] verbos) {
		this.verbos = verbos;
	}
	
	
}
