package util;

import java.io.IOException; 
import com.fazecast.jSerialComm.SerialPort; 

public class Arduino {
	private static Integer comando = -1;
	
    public static void main(String[] args) throws IOException, InterruptedException
    {
    	
    	
        // Configura porta
        SerialPort sp = SerialPort.getCommPort("ttyUSB0");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        // Abre porta
        if (sp.openPort())
            System.out.println("Porta aberta com sucesso!");
        else {
            System.out.println("Não foi possível abrir a porta.");
            return;
        }

        	// Acesso Liberado
        	if(comando == 1) {
        		
        		sp.getOutputStream().write('1');
        		Thread.sleep(800); 
        		sp.getOutputStream().write('0'); 
        		Thread.sleep(800);
        		
        	} else { // Acesso Negado
        		sp.getOutputStream().write('1');
        		Thread.sleep(800); 
        		sp.getOutputStream().write('0'); 
        		Thread.sleep(800);
        		
        	}
        		
        
            
          
        

        sp.closePort(); // Fecha porta

    }

    public void recebeAcesso(Integer bin) {
    	if(bin == 1) 
    		comando = 1;
    	else
    		comando = 0;
    }
    
  

}