package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Permissive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaPermissive();
	}
	
    /*
    Permissives: (agree to, allow, authorize, bless, consent to, dismiss,
excuse, exempt, forgive, grant, license, pardon, release, sanction)
In uttering e, S permits H to A if S expresses:
i. the belief that his utterance, in virtue of his authority over H, entitles
H to A, and
ii. the intention that H believe that S's utterance entitles him to A.
     */
    public  Boolean atoDeFalaPermissive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
		boolean achouPermissao = searchTags(tokens, tags, new String[]{"VB", "VBD", "VBG"});
		
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouPermissao == true);
    }

	
	

}
