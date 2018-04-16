package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Assertive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaAssertive();
	}
	
	
    /*
    Assertives (simple): (affirm, allege, assert, aver, avow, claim, declare,
deny (assert ... not), indicate, maintain, propound, say, state, submit)
In uttering e, S asserts that P if S expresses:
i. the belief that P, and
ii. the intention that H believe that P.
     */
    public  Boolean atoDeFalaAssertive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true);
    }

	

}
