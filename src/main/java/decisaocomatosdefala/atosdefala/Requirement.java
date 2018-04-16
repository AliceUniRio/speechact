package decisaocomatosdefala.atosdefala;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.model.Modelo;
import decisaocomatosdefala.nlp.InfinitivoHelper;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Requirement extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaRequirements();
	}
	
	
    /*
    Requirements: (bid, charge, command, demand, dictate, direct, enjoin,
instruct, order, prescribe, require)
In uttering e, S requires H to A if S expresses:
i. the belief that his utterance, in virtue of his authority over H, constitutes
sufficient reason for H to A, and
ii. the intention that H do A because of S's utterance.
     */
    public  Boolean atoDeFalaRequirements() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] preposicao = {"TO"};

		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
        boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		boolean achouObjeto = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS"});
		boolean achouPreposicao = searchTags(tokens, tags, new String[]{"TO"});

        ext: for (int i = 0; i < tokens.length; i++) {
            for (int o = 0; o < preposicao.length; o++) {
                if (tags[i].equals(preposicao[o]) && tokens[i].toLowerCase().equals("to")) {
                    achouPreposicao = true;
                    break ext;
                }
            }
        }
        return (achouSujeito && achouVerbo && achouPessoa && achouObjeto && achouPreposicao );
    }

	

}
