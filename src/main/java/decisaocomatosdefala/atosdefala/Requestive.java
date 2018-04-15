package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Requestive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaRequestive();
	}

	// requestives = In uttering e, S requests H to A if S expresses:
	// i. the desire that H do A, and
	// ii. the intention that H do A because (at least partly) of S's desire.
	public Boolean atoDeFalaRequestive() {
		String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
		String[] tags = tagger.tag(tokens);
		String[] sujeito = { "PRP" };
		String[] verbo = { "VB", "VBD", "VBG", "VBZ" };
		String[] pessoa = { "NN", "NNS", "NNP", "NNPS", "PRP" };
		Boolean achouRequestive = false;
		Boolean achouSujeito = false;
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

		for (int i = passaValor; i < tokens.length; i++) {
			for (int v = 0; v < verbo.length; v++) {
				if (tags[i].equals(verbo[v])) {
					for (int in = 0; in < verbos.length; in++) {
						if (InfinitivoHelper.convertendoParaInfinitivo(tokens[i])
								.equals(InfinitivoHelper.convertendoParaInfinitivo(verbos[in]))) {

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
		if (achouSujeito == true && achouVerbo == true && achouPessoa == true) {
			achouRequestive = true;
		}
		return achouRequestive;
	}

}
