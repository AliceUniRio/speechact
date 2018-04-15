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

public class Concessive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaConcessive();
	}
	
    /*
    Concessives: (acknowledge, admit, agree, allow, assent, concede, concur,
confess, grant, own)
In uttering e, S concedes that P if S expresses:
i. the belief that P, contrary to what he would like to believe or contrary
to what he previously believed or avowed, and
ii. the intention that H believe that P.
     */
    public  Boolean atoDeFalaConcessive() {
        POSModel model = new POSModelLoader().load(FileUtil.loadFileFromResource("arquivos"+  File.separator +   "en-pos-maxent.bin" ));// /lib/en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model);
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] sujeito = {"PRP"};
        String[] verbo = {"VB", "VBD", "VBG", "VBZ"};
        String[] pessoa = {"NN", "NNS", "NNP", "NNPS", "PRP"};
        String[] concessao = {"VB", "VBD", "VBG", "VBZ"};
        Boolean achouConcessive = false;
        Boolean achouSujeito = false;
        Boolean achouVerbo = false;
        Boolean achouPessoa = false;
        Boolean achouConcessao = false;
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
                    passaValor = i;
                    achouPessoa = true;
                    break;
                }
            }
            if (achouPessoa == true) {
                break;
            }
        }
        for (int i = passaValor; i < tokens.length; i++) {
            for (int o = 0; o < concessao.length; o++) {
                if (tags[i].equals(concessao[o])) {
                    modelo = new Modelo();
                    modelo.setConteudo(tokens[i]);
                    modelo.setTag(tags[i]);
                    modelo.setPosicao(i);
                    respostas.add(modelo);
                    passaValor = i;
                    achouConcessao = true;
                    break;
                }
            }
            if (achouConcessao == true) {
                break;
            }
        }
        if (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouConcessao == true) {
            achouConcessive = true;
        }
        return achouConcessive;
    }


}
