package decisaocomatosdefala.atosdefala;

import java.io.File;

import decisaocomatosdefala.util.FileUtil;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public abstract class AtoDeFala implements IAtoDeFala {

    protected String mensagem;
    protected String[] verbos;
    protected static POSTaggerME tagger;
    
    static {
    		POSModel model = new POSModelLoader().load(FileUtil.loadFileFromResource("arquivos"+  File.separator +   "en-pos-maxent.bin" ));// /lib/en-pos-maxent.bin"));
        tagger = new POSTaggerME(model);
    }
    
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
