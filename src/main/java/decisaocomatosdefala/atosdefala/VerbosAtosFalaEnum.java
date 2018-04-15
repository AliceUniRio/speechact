package decisaocomatosdefala.atosdefala;

public enum VerbosAtosFalaEnum {
		ASSERTIVE("assertive"  ,  new Assertive() ,new String[]{"affirm", "allege", "assert", "aver", "avow", "claim", "declare", "indicate", "maintain", "propound", "say", "state", "submit"} )
	   ,PREDICTIVE ("predictive" , new Predictive()  ,new String[]{"forecast", "predict", "prophesy"})
	   ,RETRODICTIVES ("retrodictives",new Retrodictive(), new String[] {"recount"})
	   ,DESCRIPTIVES ("descriptives", new Descriptive() , new String[] {"call", "categorize", "characterize", "classify", "date", "describe", "diagnose", "evaluate", "grade", "identify", "portray", "rank"})
	   ,ASCRIPTIVES ("ascriptives", new Ascriptive() ,new String[] {"ascribe", "attribute", "predicate"})
	   ,INFORMATIVES ("informatives",new Informative() , new String[] {"announce", "apprise", "disclose", "inform", "insist", "notify", "point out", "report", "reveal", "tell"})
	   , CONFIRMATIVES ("confirmatives", new Confirmative() ,new String[] {"appraise", "assess", "bear witness", "certify", "conclude", "confirm", "corroborate", "find", "judge", "substantiate", "testif", "validate", "verif", "vouch for"})
	   , CONCESSIVES ("concessives" , new Concessive() ,new String[] {"acknowledge", "admit", "agree", "concede", "concur"})
	   , RETRACTIVES("retractives", new Retractive() ,new String[] {"abjure", "correct", "deny", "disavow", "disclaim", "disown", "recant", "renounce", "repudiate", "retract", "take back", "withdraw"})
	   , ASSENTIVES ("assentives", new Assentive() ,new String[] {"accept", "assent", "concur"})
	   , DISSENTIVES ("dissentives", new Descriptive() ,new String[] {"differ", "disagree", "dissent", "reject"})
	   , DISPUTATIVES ("disputatives", new Disputative() ,new String[] {"demur", "dispute", "object", "protest"})
	   , RESPONSIVES ("responsives", new Responsive() ,new String[] {"answer", "reply", "respond", "retort"})
	   , SUGGESTIVES ("suggestives", new Suggestive() ,new String[] {"conjecture", "guess", "speculate", "suggest"})
	   , SUPPOSITIVES ("suppositives", new Suppositive() ,new String[] {"assume", "hypothesize", "postulate", "stipulate", "suppose", "theorize"})
	   , REQUESTIVES ("requestives", new Requestive() ,new String[] {"ask", "beg", "beseech", "implore", "insist", "invite", "petition", "plead", "pray", "request", "solicit", "summon", "supplicate", "urge"})
	   , QUESTIONS ("questions", new Question() ,new String[] {"inquire", "interrogate", "query", "question", "quiz"})
	   , REQUIREMENTS ("requirements", new Requirement() ,new String[] {"bid", "charge", "command", "demand", "dictate", "direct", "enjoin", "instruct", "order", "prescribe", "require"})
	   , PROHIBITIVES ("prohibitives", new Prohibitive() ,new String[] {"forbid", "prohibit", "proscribe", "restrict"})
	   , PERMISSIVES ("permissives", new Permissive() ,new String[] {"allow", "authorize", "bless", "consent to", "dismiss", "excuse", "exempt", "forgive", "grant", "license", "pardon", "release", "sanction"})
	   , ADVISORIES ("advisories", new Advisory() ,new String[] {"admonish", "advise", "caution", "counsel", "propose", "recommend", "suggest", "urge", "warn"})
	   , PROMISES ("promises", new Promisse() ,new String[] {"promise", "swear", "vow"})
	   , OFFERS ("offers", new Offer() ,new String[] {"offer"})
	   , DECISION ("decision", new Decision() ,new String[] {"close", "complete", "normalized", "solved", "agreed", "choosen", "conclude", "determine", "elect", "end", "establish", "rule", "select", "set", "vote", "detail", "diagnostic", "discrete", "procedure"});
	   
	   	private String nome;
	   	private String[] valores;
		private AtoDeFala atoDeFala;

	private VerbosAtosFalaEnum(String nome, AtoDeFala atoDeFala,  String[] valores) {
		this.nome = nome;
		this.atoDeFala = atoDeFala;
		this.valores = valores;
	}
	
	public String toString(){
		return nome; 
	}
	
	public String[] getValores(){
		return valores;
	}
	
	public Boolean isMessageAtoDeFala(String mensagem) {
		atoDeFala.setVerbos(valores);
		atoDeFala.setMensagem(mensagem);
		return atoDeFala.isMessageAtoDeFala();
	}

}
