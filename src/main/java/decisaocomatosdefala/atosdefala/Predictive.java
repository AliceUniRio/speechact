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

		boolean achouSujeito = searchTags(tokens, tags, new String[] { "PRP" });
		boolean achouPreposicao = searchTags(tokens, tags, new String[] { "IN", "TO" });
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[] { "VB", "VBD", "VBG", "VBZ" });
		boolean achouPessoa = searchTags(tokens, tags, new String[] { "NN", "NNS", "NNP", "NNPS", "PRP" });

		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouPreposicao == true);
	}

}
