package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Concessive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaConcessive();
	}
	
    /*
    Concessives: (acknowledge, admit, agree, allow, assent, concede, concur,
confess, grant, own)
In uttering e, S concedes that P if S expresses:
i. the belief that P, contrary to what he would like to believe or contrary
to what he previously believed or avowed, and
ii. the intention that H believe that P.
     */
    public  Boolean atoDeFalaConcessive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		boolean achouConcessao = searchTags(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});

		return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouConcessao == true);
    }


}