package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Descriptive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaDescriptive();
	}
	
	
    /*
    Descriptives: (appraise, assess, call, categorize, characterize, classify,
	date, describe, diagnose, evaluate, grade, identify, portray, rank)
	In uttering e, S describes 0 as F if S expresses:
	i. the belief that 0 is F, and
	ii. the intention that H believe that 0 is F.
     */
    public  Boolean atoDeFalaDescriptive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouObjeto = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS"});
		boolean achouPreposicao = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS"});
		boolean achouAdjetivo = searchTags(tokens, tags, new String[]{"JJ"});

        return (achouSujeito && achouVerbo && achouPreposicao && achouObjeto && achouAdjetivo );
    }

	

}
