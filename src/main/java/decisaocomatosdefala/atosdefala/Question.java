package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Question extends AtoDeFala {

	@Override
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaQuestions();
	}
	
	
    /*
    Questions: (ask, inquire, interrogate, query, question, quiz)
In uttering e, S questions H as to whether or not P if S expresses:
i. the desire that H tell S whether or not P, and
ii. the intention that H tell S whether or not P because of S's desire.
     */
    public  Boolean atoDeFalaQuestions() {
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);
        String[] pessoa = {"NN", "NNS", "NNP", "NNPS", "PRP"};
        
		boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouPessoa = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
        
        Boolean achouInterrogacao = false;
        int passaValor = 0;
        
        for (int i = passaValor; i < tokens.length; i++) {
            for (int o = 0; o < pessoa.length; o++) {
                if (tokens[i].equals("?")) {
                	achouInterrogacao = true;
                    break;
                }
            }
        }
        return (achouSujeito == true && achouVerbo == true && achouPessoa == true && achouInterrogacao== true);
    }

	

}
