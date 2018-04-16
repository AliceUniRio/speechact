package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Suggestive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaSuggestives();
	}
	
	
    /*
    Suggestives: (conjecture, guess, hypothesize, speculate, suggest)
In uttering e, S suggests that P if S expresses
    i. the belief that there is reason, but not sufficient reason, to believe
that P, and
ii. the intention that H believe that there is reason, but not sufficient
reason, to believe that P.
     */
    public  Boolean atoDeFalaSuggestives() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		boolean achouSuggestive = searchTags(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});

        return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouSuggestive == true);
    }

	

}
