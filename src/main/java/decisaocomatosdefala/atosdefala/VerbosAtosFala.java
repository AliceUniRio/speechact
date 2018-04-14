package decisaocomatosdefala.atosdefala;

public enum VerbosAtosFala {
	ASSERTIVE("assertive",new String[]{"affirm", "allege", "assert", "aver", "avow", "claim", "declare", "indicate", "maintain", "propound", "say", "state", "submit"} )
	   ,PREDICTIVE ("predictive", new String[]{"forecast", "predict", "prophesy"})
	   ,RETRODICTIVES ("retrodictives", new String[] {"recount"})
	   ,DESCRIPTIVES ("descriptives", new String[] {"call", "categorize", "characterize", "classify", "date", "describe", "diagnose", "evaluate", "grade", "identify", "portray", "rank"})
	   ,ASCRIPTIVES ("ascriptives", new String[] {"ascribe", "attribute", "predicate"})
	   ,INFORMATIVES ("informatives", new String[] {"announce", "apprise", "disclose", "inform", "insist", "notify", "point out", "report", "reveal", "tell"})
	   , CONFIRMATIVES ("confirmatives", new String[] {"appraise", "assess", "bear witness", "certify", "conclude", "confirm", "corroborate", "find", "judge", "substantiate", "testif", "validate", "verif", "vouch for"})
	   , CONCESSIVES ("concessives" , new String[] {"acknowledge", "admit", "agree", "concede", "concur"})
	   , RETRACTIVES("retractives", new String[] {"abjure", "correct", "deny", "disavow", "disclaim", "disown", "recant", "renounce", "repudiate", "retract", "take back", "withdraw"})
	   , ASSENTIVES ("assentives", new String[] {"accept", "assent", "concur"})
	   , DISSENTIVES ("dissentives", new String[] {"differ", "disagree", "dissent", "reject"})
	   , DISPUTATIVES ("disputatives", new String[] {"demur", "dispute", "object", "protest"})
	   , RESPONSIVES ("responsives", new String[] {"answer", "reply", "respond", "retort"})
	   , SUGGESTIVES ("suggestives", new String[] {"conjecture", "guess", "speculate", "suggest"})
	   , SUPPOSITIVES ("suppositives", new String[] {"assume", "hypothesize", "postulate", "stipulate", "suppose", "theorize"})
	   , REQUESTIVES ("requestives", new String[] {"ask", "beg", "beseech", "implore", "insist", "invite", "petition", "plead", "pray", "request", "solicit", "summon", "supplicate", "urge"})
	   , QUESTIONS ("questions", new String[] {"inquire", "interrogate", "query", "question", "quiz"})
	   , REQUIREMENTS ("requirements", new String[] {"bid", "charge", "command", "demand", "dictate", "direct", "enjoin", "instruct", "order", "prescribe", "require"})
	   , PROHIBITIVES ("prohibitives", new String[] {"forbid", "prohibit", "proscribe", "restrict"})
	   , PERMISSIVES ("permissives", new String[] {"allow", "authorize", "bless", "consent to", "dismiss", "excuse", "exempt", "forgive", "grant", "license", "pardon", "release", "sanction"})
	   , ADVISORIES ("advisories", new String[] {"admonish", "advise", "caution", "counsel", "propose", "recommend", "suggest", "urge", "warn"})
	   , PROMISES ("promises", new String[] {"promise", "swear", "vow"})
	   , OFFERS ("offers", new String[] {"offer"})
	   , DECISION ("decision", new String[] {"close", "complete", "normalized", "solved", "agreed", "choosen", "conclude", "determine", "elect", "end", "establish", "rule", "select", "set", "vote", "detail", "diagnostic", "discrete", "procedure"});
	   
	   	private String nome;
	private String[] valores;

	private VerbosAtosFala(String nome, String[] valores) {
		this.nome = nome;
		this.valores = valores;
	}
	
	public String toString(){
		return nome; 
	}
	
	public String[] getValores(){
		return valores;
	}

}
