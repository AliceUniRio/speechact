package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Confirmative extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaConfirmative();
	}
	
	
    /*
    Confirmatives: (appraise, assess, bear witness, certify, conclude, confirm,
corroborate, diagnose, find, judge, substantiate, testify, validate,
verify, vouch for)
    In uttering e, S confirms (the claim) that P if S expresses:
i. the belief that P, based on some truth-seeking procedure, and
ii. the intention that H believe that P because S has support for P.
     */
    public  Boolean atoDeFalaConfirmative() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"PRP"});

        return (achouSujeito == true && achouVerbo == true && achouPessoa == true);
    }

	

}
