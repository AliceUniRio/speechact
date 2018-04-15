package decisaocomatosdefala.atosdefala;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import decisaocomatosdefala.util.FileUtil;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Advisory extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaAdvisories();
	}
	

    /*
    Advisories: (admonish, advise, caution, counsel, propose, recommend,
suggest, urge, warn)
In uttering e, S advises H to A if S expresses:
i. the belief that there is (sufficient) reason for H to A, and
ii. the intention thatH take S's belief as (sufficient) reason for him toA.
     */
    public  Boolean atoDeFalaAdvisories() {
        POSModel model = new POSModelLoader().load(FileUtil.loadFileFromResource("arquivos"+  File.separator +   "en-pos-maxent.bin" ));// /lib/en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model);
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] sujeito = {"PRP"};
        String[] verbo = {"VB", "VBD", "VBG", "VBZ"};
        String[] pessoa = {"NN", "NNS", "NNP", "NNPS", "PRP"};
        String[] conselho = {"VB", "VBD", "VBG"};
        Boolean achouRequestive = false;
        Boolean achouSujeito = false;
        Boolean achouVerbo = false;
        Boolean achouPessoa = false;
        Boolean achouConselho = false;
        List<Modelo> respostas = new ArrayList<>();
        Modelo modelo = null;
        int passaValor = 0;
        for (int i = 0; i < tokens.length; i++) {
            for (int s = 0; s < sujeito.length; s++) {
                if (tags[i].equals(sujeito[s])) {

                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    passaValor = i;
                    achouSujeito = true;
                    break;
                }
            }
            if (achouSujeito == true) {
                break;
            }
        }

        for (int i = passaValor; i < tokens.length; i++) {
            for (int v = 0; v < verbo.length; v++) {
                if (tags[i].equals(verbo[v])) {
                    for (int in = 0; in < verbos.length; in++) {
                        if (InfinitivoHelper.convertendoParaInfinitivo(tokens[i]).equals(InfinitivoHelper.convertendoParaInfinitivo(verbos[in]))) {
                            modelo = new Modelo();
                            modelo.setConteudo(tokens[i]);
                            modelo.setTag(tags[i]);
                            modelo.setPosicao(i);
                            respostas.add(modelo);
                            passaValor = i;
                            achouVerbo = true;
                            break;
                        }
                    }
                }
            }
            if (achouVerbo == true) {
                break;
            }
        }
        for (int i = passaValor; i < tokens.length; i++) {
            for (int o = 0; o < pessoa.length; o++) {
                if (tags[i].equals(pessoa[o])) {

                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    achouPessoa = true;
                    passaValor = i;
                    break;
                }
            }
            if (achouPessoa == true) {
                break;
            }
        }
        for (int i = passaValor; i < tokens.length; i++) {
            for (int o = 0; o < conselho.length; o++) {
                if (tags[i].equals(conselho[o])) {
                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    passaValor = i;
                    achouPessoa = true;
                    break;
                }
            }
            if (achouPessoa == true) {
                break;
            }
        }
        if (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouConselho == true) {
            achouRequestive = true;
        }
        return achouRequestive;
    }

	
	

}
