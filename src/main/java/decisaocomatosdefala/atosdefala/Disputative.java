package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Disputative extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaDisputative();
	}
	
	
    /*
    Disputatives: (demur, dispute, object, protest, question)
In uttering e, S disputes the claim that P if S expresses:
i. the belief that there is reason not to believe that P, contrary to what
was claimed by H (or was otherwise under discussion), and
ii. the intention that H believe that there is reason not to believe that P.
     */
    public  Boolean atoDeFalaDisputative() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouObjeto = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
        
		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouObjeto == true);
    }

	

}
