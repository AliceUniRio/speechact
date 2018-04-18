package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Predictive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaPredictive();
	}

	/*
	 * Predictives: (forecast, predict, prophesy) In uttering e, S predicts that P
	 * if S expresses: i. the belief that it will be the case that P, and ii. the
	 * intention that H believe that it will be the case that P.
	 */
	public Boolean atoDeFalaPredictive() {
		String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
		String[] tags = tagger.tag(tokens);
		String[] preposicao = { "IN", "TO" };
		
		boolean achouSujeito = searchTags(tokens, tags, new String[] { "PRP" });
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[] { "VB", "VBD", "VBG", "VBZ" });
		boolean achouPessoa = searchTags(tokens, tags, new String[] { "NN", "NNS", "NNP", "NNPS", "PRP" });

		boolean achouPreposicao = false;
		ext: for (int i = 0; i < tokens.length; i++) {
            for (int o = 0; o < preposicao.length; o++) {
                if (tags[i].equals(preposicao[o]) && tokens[i].equals("that")) {
                    achouPreposicao = true;
                    break ext;
                }
            }
        }
		
		
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouPreposicao == true);
	}

}
