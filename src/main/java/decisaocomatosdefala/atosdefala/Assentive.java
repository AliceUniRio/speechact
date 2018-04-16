package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Assentive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaAssentive();
	}
	
	
    /*
    Assentives: (accept, agree, assent, concur)
In uttering e, S assents to the claim that P if S expresses:
i. the belief that P, as claimed by H (or as otherwise under discussion),
and
ii. the intention (perhaps already fulfilled) that H believe that P.

     */
    public  Boolean atoDeFalaAssentive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPreposicao = searchTags(tokens, tags, new String[]{"IN", "TO"});
      
		Boolean achouObjeto = true;

		
        return (achouSujeito && achouVerbo && achouPreposicao && achouObjeto );
    }

	

}
