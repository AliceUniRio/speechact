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

public class Descriptive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaDescriptive();
	}
	
	
    /*
    Descriptives: (appraise, assess, call, categorize, characterize, classify,
date, describe, diagnose, evaluate, grade, identify, portray, rank)
In uttering e, S describes 0 as F if S expresses:
i. the belief that 0 is F, and
ii. the intention that H believe that 0 is F.
     */
    public  Boolean atoDeFalaDescriptive() {
        POSModel model = new POSModelLoader().load(FileUtil.loadFileFromResource("arquivos"+  File.separator +   "en-pos-maxent.bin" ));// /lib/en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model);
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] sujeito = {"PRP"};
        String[] verbo = {"VB", "VBD", "VBG", "VBZ"};
        String[] objeto = {"NN", "NNS", "NNP", "NNPS"};
        String[] preposicao = {"NN", "NNS", "NNP", "NNPS"};
        String[] adjetivo = {"JJ"};
        Boolean achouDescriptive = false;
        Boolean achouSujeito = false;
        Boolean achouObjeto = false;
        Boolean achouPreposicao = false;
        Boolean achouAdjetivo = false;
        Boolean achouVerbo = false;
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
            for (int v = 0; v < objeto.length; v++) {
                if (tags[i].equals(objeto[v])) {
                    for (int in = 0; in < verbos.length; in++) {
                        modelo = new Modelo();
                        modelo.setConteudo(tokens[i]);
                        modelo.setTag(tags[i]);
                        modelo.setPosicao(i);
                        respostas.add(modelo);
                        passaValor = i;
                        achouObjeto = true;
                        break;
                    }
                }
            }
            if (achouObjeto == true) {
                break;
            }
        }

        for (int i = passaValor; i < tokens.length; i++) {
            for (int o = 0; o < preposicao.length; o++) {
                if (tags[i].equals(preposicao[o])) {
                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    achouPreposicao = true;
                    break;
                }
            }
            if (achouPreposicao == true) {
                break;
            }
        }

        for (int i = passaValor; i < tokens.length; i++) {
            for (int o = 0; o < adjetivo.length; o++) {
                if (tags[i].equals(adjetivo[o])) {
                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    achouAdjetivo = true;
                    break;
                }
            }
            if (achouAdjetivo == true) {
                break;
            }
        }

        if (achouSujeito == true && achouVerbo == true && achouPreposicao == true && achouObjeto == true && achouAdjetivo == true) {
            achouDescriptive = true;
        }
        return achouDescriptive;
    }

	

}
