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

public class Predictive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaPredictive();
	}
	
	

    /*
    Predictives: (forecast, predict, prophesy)
In uttering e, S predicts that P if S expresses:
i. the belief that it will be the case that P, and
ii. the intention that H believe that it will be the case that P.
     */
    public  Boolean atoDeFalaPredictive() {
        POSModel model = new POSModelLoader().load(FileUtil.loadFileFromResource("arquivos"+  File.separator +   "en-pos-maxent.bin" ));// /lib/en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model);
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] sujeito = {"PRP"};
        String[] preposicao = {"IN", "TO"};
        String[] verbo = {"VB", "VBD", "VBG", "VBZ"};
        String[] pessoa = {"NN", "NNS", "NNP", "NNPS", "PRP"};
        Boolean achouPredictive = false;
        Boolean achouSujeito = false;
        Boolean achouPreposicao = false;
        Boolean achouVerbo = false;
        Boolean achouPessoa = false;
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
        for (int i = 0; i < tokens.length; i++) {
            for (int s = 0; s < preposicao.length; s++) {
                if (tags[i].equals(preposicao[s]) && tokens[i].equals("that")) {
                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    passaValor = i;
                    achouPreposicao = true;
                    break;
                }
            }
            if (achouPreposicao == true) {
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
                    break;
                }
            }
            if (achouPessoa == true) {
                break;
            }
        }
        if (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouPreposicao == true) {
            achouPredictive = true;
        }
        return achouPredictive;
    }

	

}