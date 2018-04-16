package decisaocomatosdefala.atosdefala;

import java.io.File;

import decisaocomatosdefala.nlp.InfinitivoHelper;
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

	

	public Boolean searchTags(String tokens[], String[] tags, String[] target) {
		 for (int i = 0; i < tokens.length; i++) {
	            for (int s = 0; s < target.length; s++) {
	                if (tags[i].equals(target[s])) {
	                    return true;
	                }
	            }
	        }
		return false;
	}
	
	public Boolean searchTagsWithVerb(String tokens[], String[] tags, String[] target) {
		int passaValor = 0;
		  for (int i = passaValor; i < tokens.length; i++) {
	            for (int v = 0; v < target.length; v++) {
	                if (tags[i].equals(target[v])) {
	                    for (int in = 0; in < verbos.length; in++) {
	                        if (InfinitivoHelper.convertendoParaInfinitivo(tokens[i]).equals(InfinitivoHelper.convertendoParaInfinitivo(verbos[in]))) {
	                            return true;
	                        }
	                    }
	                }
	            }
	        }
		return false;
	}
	
	
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
