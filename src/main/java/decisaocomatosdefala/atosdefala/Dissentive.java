package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Dissentive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaDissentive();
	}
	
	
    /*
    Dissentives: (differ, disagree, dissent, reject)
In uttering e, S dissents from the claim that P if S expresses:
i. the disbelief that P, contrary to what was claimed by H (or was
otherwise under discussion), and
ii. the intention that H disbelieve that P.
     */
    public  Boolean atoDeFalaDissentive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouPreposicao = searchTags(tokens, tags, new String[]{"IN"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
		
        return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouPreposicao == true);
    }

	

}
