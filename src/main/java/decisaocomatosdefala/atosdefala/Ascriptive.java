package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Ascriptive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaAscriptives();
	}
	
	
    /*
    Ascriptives: (ascribe, attribute, predicate)
In uttering e, S ascribes F to 0 if S expresses:
i. the belief that F applies to 0, and
ii. the intention that H believe that F applies to o.
     */
    public  Boolean atoDeFalaAscriptives() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		boolean achouPreposicao = searchTags(tokens, tags, new String[]{"IN", "TO"});
		boolean achouPessoaObjeto = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
        
        
        return (achouSujeito && achouVerbo && achouPessoa && achouPreposicao && achouPessoaObjeto );
    }

	

}
