/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.List;

import decisaocomatosdefala.model.Impressao;
import decisaocomatosdefala.model.PontoDecisao;
import decisaocomatosdefala.model.TicketsComMensagens;
import decisaocomatosdefala.util.FileUtil;

/**
 *
 * @author edveloso
 */
public class Main {

    public static void main(String args[]) throws Exception{

    		List<TicketsComMensagens> ticketLimpos = FileUtil.loadTicketsFromFile("arquivos"+  File.separator +   "Dataset01.csv");//Dataset01.csv  LogMessage.csv

        List<TicketsComMensagens> ticketsComVerbos = PontoDecisao.buscandoVerbosEmMensagens(ticketLimpos);

        List<Impressao> decisoesEncontradas = FileUtil.extractDecisionPoints(ticketsComVerbos);

        List<Impressao> mensagensAnteriores = FileUtil.listMensagensParaImpressao(ticketsComVerbos, decisoesEncontradas);
        
        FileUtil.imprimeNaConsoleENoArquivo(decisoesEncontradas, mensagensAnteriores);
    }

}
