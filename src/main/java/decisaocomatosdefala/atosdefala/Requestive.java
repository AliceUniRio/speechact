package decisaocomatosdefala.atosdefala;

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
		
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		
		return  (achouSujeito && achouVerbo && achouPessoa );
	}

}
