package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Offer extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaOffer();
	}
	
	

//  Offers: (offer , propose)
//In uttering e, S offers A to H if S expresses:
//i. the belief that S's utterance obligates him to A on condition that H
//indicates he wants S to A,
//ii. the intention to A on condition that H indicates he wants S to A, and
//iii. the intention that H believe that S's utterance obligates S to A and
//that S intends to A, on condition that H indicates he wants S to A.
//volunteer: S offers his services.
//bid: S offers to give something (in a certain amount) in exchange for
//something.

public  Boolean atoDeFalaOffer() {
  String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
  String[] tags = tagger.tag(tokens);
  String[] sujeito = {"PRP"};
  String[] verbo = {"VB", "VBD", "VBG", "VBZ"};
  String[] pessoa = {"NN", "NNS", "NNP", "NNPS", "PRP"};
  String[] pronome = {"NN", "NNS", "NNP", "NNPS", "PRP"};
  Boolean achouOffer = false;
  Boolean achouSujeito = false;
  Boolean achouVerbo = false;
  Boolean achouPessoa = false;
  Boolean achouObjetoPronome = false;
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
      for (int o = 0; o < pronome.length; o++) {
          if (tags[i].equals(pronome[o])) {
              modelo = new Modelo();
              modelo.setConteudo(tokens[i]);
              modelo.setTag(tags[i]);
              modelo.setPosicao(i);
              respostas.add(modelo);
              achouObjetoPronome = true;
              passaValor = i;
              break;
          }
      }
      if (achouPessoa == true) {
          break;
      }
  }
  if (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouObjetoPronome == true) {
      achouOffer = true;
  }
  return achouOffer;
}

	
	

}
