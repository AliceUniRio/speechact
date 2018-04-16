package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Offer extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaOffer();
	}

	// Offers: (offer , propose)
	// In uttering e, S offers A to H if S expresses:
	// i. the belief that S's utterance obligates him to A on condition that H
	// indicates he wants S to A,
	// ii. the intention to A on condition that H indicates he wants S to A, and
	// iii. the intention that H believe that S's utterance obligates S to A and
	// that S intends to A, on condition that H indicates he wants S to A.
	// volunteer: S offers his services.
	// bid: S offers to give something (in a certain amount) in exchange for
	// something.

	public Boolean atoDeFalaOffer() {
		String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
		String[] tags = tagger.tag(tokens);

		boolean achouSujeito = searchTags(tokens, tags, new String[] { "PRP" });
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[] { "VB", "VBD", "VBG", "VBZ" });
		boolean achouPessoa = searchTags(tokens, tags, new String[] { "NN", "NNS", "NNP", "NNPS", "PRP" });
		boolean achouObjetoPronome = searchTags(tokens, tags, new String[] { "NN", "NNS", "NNP", "NNPS", "PRP" });

		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouObjetoPronome == true);
	}

}
