package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Advisory extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaAdvisories();
	}
	

    /*
    Advisories: (admonish, advise, caution, counsel, propose, recommend,
	suggest, urge, warn)
	In uttering e, S advises H to A if S expresses:
	i. the belief that there is (sufficient) reason for H to A, and
	ii. the intention thatH take S's belief as (sufficient) reason for him toA.
     */
    public  Boolean atoDeFalaAdvisories() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		boolean achouConselho = searchTags(tokens, tags, new String[]{"VB", "VBD", "VBG"});
        
        
        return (achouSujeito && achouVerbo && achouPessoa && achouConselho );
    }

	
	

}
