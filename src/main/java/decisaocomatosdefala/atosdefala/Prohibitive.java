package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Prohibitive extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaProhibitive();
	}
	
    /*
    Prohibitives: (enjoin, forbid, prohibit, proscribe, restrict)
	In uttering e, S prohibits H from A-ing if S expresses:
	i. the belief that his utterance, in virtue of his authority over H, constitutes
	sufficient reason for H not to A, and
	ii. the intention that because of S's utterance H not do A.
     */
    public  Boolean atoDeFalaProhibitive() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] preposicao = {"IN"};
        
        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});;
		boolean achouProibicao = searchTags(tokens, tags, new String[]{"VB", "VBD", "VBG"});
		
		boolean achouPreposicao = false;
		 ext: for (int i = 0; i < tokens.length; i++) {
	            for (int o = 0; o < preposicao.length; o++) {
	                if (tags[i].equals(preposicao[o]) && tokens[i].equals("from")) {
	                    achouPreposicao = true;
	                    break ext;
	                }
	            }
	        }
		
        
        return (achouSujeito && achouVerbo && achouPessoa && achouProibicao && achouPreposicao);
    }


}
