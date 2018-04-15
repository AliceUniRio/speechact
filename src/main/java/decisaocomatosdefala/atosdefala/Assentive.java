package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Assentive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaAssentive();
	}
	
	
    /*
    Assentives: (accept, agree, assent, concur)
In uttering e, S assents to the claim that P if S expresses:
i. the belief that P, as claimed by H (or as otherwise under discussion),
and
ii. the intention (perhaps already fulfilled) that H believe that P.

     */
    public  Boolean atoDeFalaAssentive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] sujeito = {"PRP"};
        String[] verbo = {"VB", "VBD", "VBG", "VBZ"};
        String[] preposicao = {"TO", "IN"};
        String[] objeto = {"NN", "NNS", "NNP", "NNPS"};
        Boolean achouAssentive = false;
        Boolean achouSujeito = false;
        Boolean achouVerbo = false;
        Boolean achouPreposicao = false;
        Boolean achouObjeto = false;
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
            for (int o = 0; o < preposicao.length; o++) {
                if (tags[i].equals(preposicao[o])) {
                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    achouPreposicao = true;
                    passaValor = i;
                    break;
                }
            }
            if (achouPreposicao == true) {
                break;
            }
        }
        if (achouSujeito == true && achouVerbo == true && achouPreposicao == true && achouObjeto == true) {
            achouAssentive = true;
        }
        return achouAssentive;
    }

	

}
