package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Suppositive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaSuppositive();
	}
	
    /*
    Suppositives: (assume, hypothesize, postulate, stipulate, suppose, theorize)
In uttering e, S supposes that P if S expresses:
i. the belief that it is worth considering the consequences of P, and
ii. the intention that H believe that it is worth considering the consequences
of P.
     */
    public  Boolean atoDeFalaSuppositive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
		
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true);
    }

	
	

}
