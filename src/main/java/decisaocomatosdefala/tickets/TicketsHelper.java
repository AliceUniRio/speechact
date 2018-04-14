package decisaocomatosdefala.tickets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import decisaocomatosdefala.execucao.AtosDeFalaDecisao;
import decisaocomatosdefala.model.Mensagem;
import decisaocomatosdefala.model.TicketsComMensagens;
import decisaocomatosdefala.nlp.StopWords;

public class TicketsHelper {

	private final static String CSV_DIVISOR = ";";

	public List<TicketsComMensagens> leituraDoArquivoCSV(String caminho) throws FileNotFoundException, IOException, ParseException {
		BufferedReader br = getBufferedReaderFrom(caminho);
		Map<String, TicketsComMensagens> ticketRepository = new TreeMap<String, TicketsComMensagens>();
		String linha = null;
		
		while ((linha = br.readLine()) != null) {
			try {
				MessageFileDetail mfd = new MessageFileDetail(linha);
				if(ticketRepository.get(mfd.getTicketId()) == null){
					TicketsComMensagens ticket = new TicketsComMensagens(mfd.getTicketId());
					ticket.adicionarMensagem(mfd);
					ticketRepository.put(mfd.getTicketId(), ticket);
				}else{ 
					ticketRepository.get(mfd.getTicketId()).adicionarMensagem(mfd);
				}
			} catch (Exception e) {
				System.out.println("Erro ao recuperar tickets do arquivo: "+ e.getMessage());
			}
		}
		return new ArrayList<TicketsComMensagens>(ticketRepository.values());
	}

	private BufferedReader getBufferedReaderFrom(String caminho) {
		InputStream fstream = AtosDeFalaDecisao.class.getResourceAsStream(File.separator + caminho);
		DataInputStream in = new DataInputStream(fstream);
		return new BufferedReader(new InputStreamReader(in));
	}

	private class MessageFileDetail extends Mensagem {

		private String[] colunas;

		public MessageFileDetail(String linha) {
			colunas = linha.split(CSV_DIVISOR);
		}

		public String getTicketId() {
			return (colunas[0]); //0 1
		}

		public String getMensagem() {
			return StopWords.removendoCaracter(colunas[5]);//5 2
		}

		public String getMsgId() {
			return (colunas[1]);//1 0
		}
		
		@Override
		public Date getDatahora() {
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy HH:MM");
			try {
				return sdf.parse(colunas[2]);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

}
