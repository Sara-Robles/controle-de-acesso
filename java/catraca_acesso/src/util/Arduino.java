package util;

import java.io.IOException; 
import com.fazecast.jSerialComm.SerialPort; 

public class Arduino {
    private boolean comando;
    private SerialPort sp;

    public Arduino(String portDescription) {
        sp = SerialPort.getCommPort(portDescription);
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort())
            System.out.println("Arduino conectado!");
        else {
            System.out.println("Não foi possível conectar a porta.");
        }
    }

    public void setComando(boolean comando) {
        this.comando = comando;
        try {
            if (comando) 
                sp.getOutputStream().write('1');
            else 
                sp.getOutputStream().write('0');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        sp.closePort();
    }

}
