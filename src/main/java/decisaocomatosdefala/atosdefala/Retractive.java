package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Retractive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaRetractive();
	}
	
    /*
    Retractives: (abjure, correct, deny, disavow, disclaim, disown, recant,
renounce, repudiate, retract, take back, withdraw)
In uttering e, S retracts the claim that P if S expresses:
i. that he no longer believes that P, contrary to what he previously
indicated he believed, and
ii. the intention that H not believe that P.
     */
    public  Boolean atoDeFalaRetractive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);

        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;

        return (achouSujeito == true && achouVerbo == true && achouPessoa == true);
    }

	
	

}
