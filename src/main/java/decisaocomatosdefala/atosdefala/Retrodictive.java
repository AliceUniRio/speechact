package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Retrodictive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaRetrodictive();
	}
	
	

    /*
    Retrodictives: (recount, report)
In uttering e, S retrodicts that P if S expresses:
i. the belief that it was the case that P, and
ii. the intention that H believe that it was the case that P.
     */
    public  Boolean atoDeFalaRetrodictive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouPreposicao = searchTags(tokens, tags, new String[]{"IN", "TO"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouPreposicao == true);
    }

	

}
