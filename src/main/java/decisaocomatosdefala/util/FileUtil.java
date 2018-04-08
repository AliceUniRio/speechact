package decisaocomatosdefala.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import decisaocomatosdefala.execucao.AtosDeFalaDecisao;
import decisaocomatosdefala.model.Impressao;
import decisaocomatosdefala.model.Mensagem;
import decisaocomatosdefala.model.PontoDecisao;
import decisaocomatosdefala.model.TicketsComMensagens;
import decisaocomatosdefala.model.Verbo;
import decisaocomatosdefala.nlp.StopWords;
import decisaocomatosdefala.tickets.TicketsHelper;

public class FileUtil {
	
	public static File loadFileFromResource(String caminho) {
		InputStream fstream = AtosDeFalaDecisao.class.getResourceAsStream(File.separator + caminho);
		try {
			File tempFile = File.createTempFile("temp-"+ caminho.split("\\.")[0], caminho.split("\\.")[1]);
			tempFile.deleteOnExit();
		    FileOutputStream out = new FileOutputStream(tempFile);
		    IOUtils.copy(FileUtil.class.getResourceAsStream(File.separator + caminho), out);
		    return tempFile;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 public static void execucaoDoArquivo(String caminho) throws IOException, FileNotFoundException, ParseException {
	        //Leitura do Arquivo
	        List<TicketsComMensagens> tickets = TicketsHelper.leituraDoArquivoCSV(caminho);
	        //Limpeza das Mensagens
	        List<TicketsComMensagens> ticketLimpos = new ArrayList<TicketsComMensagens>();
	        List<Mensagem> mensagens = new ArrayList<Mensagem>();
	        TicketsComMensagens ticketLimpo = null;
	        if (tickets.isEmpty() == false) {
	            for (TicketsComMensagens ticket : tickets) {
	                ticketLimpo = new TicketsComMensagens();
	                ticketLimpo = ticket;
	                Mensagem mensagemLimpa = null;
	                for (Mensagem msg : ticket.getMensagens()) {
	                    mensagemLimpa = new Mensagem();
	                    mensagemLimpa = msg;
	                    mensagemLimpa.setMensagem(StopWords.removendoCaracter(mensagemLimpa.getMensagem()));
	                    mensagens.add(mensagemLimpa);
	                }
	                ticketLimpo.setMensagens(mensagens);
	                ticketLimpos.add(ticketLimpo);

	                mensagens = new ArrayList<Mensagem>();
	            }
	        }

	        List<Impressao> decisoesEncontradas = new ArrayList<>();
	        List<TicketsComMensagens> ticketsComVerbos = PontoDecisao.buscandoVerbosEmMensagens(ticketLimpos);
	        //Listar somente pontos de decisão

	        for (TicketsComMensagens ticket : ticketsComVerbos) {
	            for (Mensagem msg : ticket.getMensagens()) {
	                for (Verbo verbo : msg.getVerbos()) {
	                    Impressao impressao = null;
	                    if (verbo.getTipoVerbo().equals("decision")) {
	                        impressao = new Impressao(ticket.getTicketId(), msg.getMsgId(), verbo.getVerbo(), verbo.getTipoVerbo(), msg.getMensagem(), msg.getDatahora());
	                        decisoesEncontradas.add(impressao);
	                    }
	                }
	            }
	        }

	        List<Impressao> mensagensAnteriores = new ArrayList<>();

	        int msgid = 0;
	        Impressao impressao = null;
	        for (TicketsComMensagens ticket : ticketsComVerbos) {
	            for (Mensagem mensagem : ticket.getMensagens()) {
	                Boolean achou = false;
	                Verbo verboAchado = null;
	                Mensagem mensagemAchada = null;
	                for (Impressao impDecisao : decisoesEncontradas) {
	                    if (impDecisao.getTicketId().toString().equals(ticket.getTicketId().toString())) {
	                        if (mensagem.getMsgId().toString().equals(impDecisao.getMsgId())) {
	                        } else {
	                            if (Long.parseLong(mensagem.getMsgId()) < Long.parseLong(impDecisao.getMsgId())) {
	                                for (Verbo verbo : mensagem.getVerbos()) {
	                                    if (!verbo.getTipoVerbo().equals("decision")) {
	                                        achou = true;
	                                        verboAchado = new Verbo(verbo.getTipoVerbo(), verbo.getVerbo());
	                                        mensagemAchada = new Mensagem();
	                                        break;
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	                if (achou == true) {
	                    impressao = new Impressao(ticket.getTicketId(), mensagem.getMsgId(), verboAchado.getVerbo(), verboAchado.getTipoVerbo(), mensagem.getMensagem(), mensagem.getDatahora());
	                    mensagensAnteriores.add(impressao);
	                }

	            }
	        }
	        FileWriter writer = new FileWriter(FileUtil.loadFileFromResource("arquivos"+  File.separator +   "resultadoLog.csv"));

	        System.out.println("PONTOS DE DECISÃO");
	        System.out.println("==========================================================================");
	        for (Impressao imp : decisoesEncontradas) {
	            System.out.println(imp.getTicketId() + ";" + imp.getMsgId() + ";" + imp.getVerbo() + ";" + imp.getTipoVerbo() + ";" + imp.getMensagem());
	            writer.append(imp.getTicketId() + ";" + imp.getMsgId() + ";" + imp.getVerbo() + ";" + imp.getTipoVerbo() + ";" + imp.getMensagem() + "\";");
	            writer.append(System.lineSeparator());
	        }
	        System.out.println("Mensagens anteriores =" + mensagensAnteriores.size());
	        System.out.println("==========================================================================");
	        for (Impressao imp : mensagensAnteriores) {
	            System.out.println(imp.getTicketId() + ";" + imp.getMsgId() + ";" + imp.getTipoVerbo() + ";" + imp.getVerbo() + ";" + imp.getMensagem());
	            writer.append(imp.getTicketId() + ";" + imp.getMsgId() + ";" + imp.getTipoVerbo() + ";" + imp.getVerbo() + ";" + imp.getMensagem() + "\";");
	            writer.append(System.lineSeparator());
	        }
	        System.out.println("==========================================================================");
	        writer.flush();
	        writer.close();

	    }
	
}
