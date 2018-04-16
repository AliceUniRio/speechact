package decisaocomatosdefala.atosdefala;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class Informative extends AtoDeFala {
	
	public Boolean isMessageAtoDeFala() {
		return atoDeFalaInformative();
	}
	
	//Manager Fernanda informs that fiscal printer s box is not binding , led completely off 
	//, probably burned source . Necessary that you check and make relocation because there is another 
	// fiscal printer in the kitchen 
	//. André Cardoso Field Operations Team | [ 1 ] www . techmaster . com . br 21 2517 6000 - | [ 2 ] 
	
	public Boolean atoDeFalaInformative() {
		String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(mensagem);
        String[] tags = tagger.tag(tokens);

        boolean achouSujeito =  searchTags(tokens, tags, new String[]{"PRP"});
		boolean achouVerbo = searchTagsWithVerb(tokens, tags, new String[]{"VB", "VBD", "VBG", "VBZ"});
		boolean achouObjeto = searchTags(tokens, tags, new String[]{"NN", "NNS", "NNP", "NNPS", "PRP"});
		boolean achouPronome =  searchTags(tokens, tags, new String[]{"NNP", "NNPS"});
		
		return (achouSujeito && achouVerbo && achouObjeto && achouPronome);
	}
	
	
}
