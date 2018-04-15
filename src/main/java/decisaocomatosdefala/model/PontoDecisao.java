package decisaocomatosdefala.model;

import java.util.ArrayList;
import java.util.List;

import decisaocomatosdefala.atosdefala.VerbosAtosFalaEnum;
import decisaocomatosdefala.nlp.InfinitivoHelper;

public class PontoDecisao {


    public TicketsComMensagens buscarPontosDeDecisao(TicketsComMensagens ticket) {
        TicketsComMensagens ticketPonto = new TicketsComMensagens();
        ticketPonto = buscandoVerbosEmMensagens(ticket);
        return ticketPonto;
    }
    
    
    public static TicketsComMensagens buscandoVerbosEmMensagens(TicketsComMensagens ticket) {
        List<Mensagem> mensagensComVerbos = new ArrayList<>();
        for (Mensagem msg : ticket.getMensagens()) {
        		insertVerbInMessage(mensagensComVerbos, msg);
        }
        Mensagem msgNovo = null;
        TicketsComMensagens ticketNovo = null;
        ticketNovo = new TicketsComMensagens();
        ticketNovo.setTicketId(ticket.getTicketId());
        ticketNovo.setMensagens(mensagensComVerbos);
        return ticketNovo;
    }


	private static void insertVerbInMessage(List<Mensagem> mensagensComVerbos, Mensagem msg) {
		Mensagem msgNovo;
		List<Verbo> verbos;
		verbos = buscandoVerbos(msg.getMensagem());
		msgNovo = new Mensagem();
		msgNovo.setMensagem(msg.getMensagem());
		msgNovo.setMsgId(msg.getMsgId());
		msgNovo.setVerbos(verbos);
		mensagensComVerbos.add(msgNovo);
	}
	
    public static List<TicketsComMensagens> buscandoVerbosEmMensagens(List<TicketsComMensagens> tickets) {
        List<TicketsComMensagens> ticketsComVerbos = new ArrayList<>();
        List<Mensagem> mensagensComVerbos = new ArrayList<>();
        Mensagem msgNovo = null;
        TicketsComMensagens ticketNovo = null;
        
        for (TicketsComMensagens ticket : tickets) {
            ticketNovo = new TicketsComMensagens();
            for (Mensagem msg : ticket.getMensagens()) {
            	insertVerbInMessage(mensagensComVerbos, msg);
            }
            ticketNovo.setTicketId(ticket.getTicketId());
            ticketNovo.setMensagens(mensagensComVerbos);
            ticketsComVerbos.add(ticketNovo);
            mensagensComVerbos = new ArrayList<>();
        }
        return ticketsComVerbos;
    }
    
    public static List<Verbo> buscandoVerbos(String mensagem) {
        List<Verbo> verbos = new ArrayList<Verbo>();
       
        Verbo verboItem = null;
        String verbo = mensagem.toLowerCase().trim();
        
        for(VerbosAtosFalaEnum verbs : VerbosAtosFalaEnum.values()){
	        	for (String item : verbs.getValores()) {
	        		String copyOfItem = item;
	        		item = InfinitivoHelper.convertendoParaInfinitivo(item);
	        		if (item.equals("")) {
	        			item = copyOfItem;
	        		}
	        		if (verbo.contains(item.trim().toLowerCase())) {
	        			verboItem = new Verbo(copyOfItem, verbs.toString());
	        			verbos.add(verboItem);
	        		}
	        	}
        }

        return verbos;
    }
    
}
