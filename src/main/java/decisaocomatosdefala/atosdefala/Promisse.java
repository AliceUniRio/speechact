package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Promisse extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaPromisse();
	}
	
	

    /*
    Promises: (promise, swear, vow)
In uttering e, S promises H to A if S expresses:
i. the belief that his utterance obligates him to A,
ii. the intention to A, and
iii. the intention that H believe that S's utterance obligates S to A and
that S intends to A.
     */
    public  Boolean atoDeFalaPromisse() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
		
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true);
    }

	

}
