
package morseled;

/**
 *
 * @author JoseMaria
 */
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;




public class MorseLed {

    private static final String FILENAME = "/home/pi/MorseLed/Texto.txt";
    
    enum MorseCode{
        A(".-"), B("-..."), C("-.-.") , D("-.."), E("."), F("..-."), G("--."), H("...."), I(".."), J(".---"), K("-.-"), L(".-.."), M("--"),
        N("-."), O("---"), P(".--."), Q("--.-"), R(".-."), S("..."), T("-"), U("..-"), V("...-"), W(".--"), X("-..-"), Y("-.--"), Z("--.."),
        ONE(".----"), TWO("..---"), THREE("...--"), FOUR("....-"), FIVE("....."), SIX("-...."), SEVEN("--..."), EIGHT("---.."), NINE("----."), ZERO("-----"), SPACE("|") ;
    
        String letter;
    
        private MorseCode(String word) {
            this.letter = word;
        }
    
        @Override
        public String toString() {
            return letter;
        }
    }
    
    private static StringBuilder readFile(){
        StringBuilder word = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
                                word.append(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        return word;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Morse Translator...");
        StringBuilder string = new StringBuilder();
        String cadena;
        final GpioController gpioLED = GpioFactory.getInstance();
        final GpioPinDigitalOutput led = gpioLED.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        led.low();    
        string = readFile();
        cadena = translate(string);
        ledShow(led, cadena);
        System.out.println(cadena);
        gpioLED.shutdown();
        System.out.println("String sent.");
    }
    
    private static void ledShow(GpioPinDigitalOutput led, String cadena) throws Exception{
        for(int i = 0; i < cadena.length(); i++){
            switch (cadena.charAt(i)) {
                case '.':
                    led.pulse(250, true);
                    Thread.sleep(750);
                    break;
                case '-':
                    led.pulse(750, true);
                    Thread.sleep(750);
                    break;
                default:
                    Thread.sleep(1250);
                    break;
            } 
        }
    }
    
    
    private static String translate(StringBuilder cadena){
        String morseString = new String();
        char letra;
        for(int i = 0; i < cadena.length(); i++){
            letra = cadena.charAt(i);
            switch (letra) {
                case 'a': morseString += (MorseCode.A);
                         break;
                case 'b': morseString += (MorseCode.B);
                         break;
                case 'c': morseString += (MorseCode.C);
                         break;
                case 'd': morseString += (MorseCode.D);
                         break;
                case 'e': morseString += (MorseCode.E);
                         break;
                case 'f': morseString += (MorseCode.F);
                         break;
                case 'g': morseString += (MorseCode.G);
                         break;
                case 'h': morseString += (MorseCode.H);
                         break;
                case 'i': morseString += (MorseCode.I);
                         break;
                case 'j': morseString += (MorseCode.J);
                         break;
                case 'k': morseString += (MorseCode.K);
                         break;
                case 'l': morseString += (MorseCode.L);
                         break;
                case 'm': morseString += (MorseCode.M);
                         break;
                case 'n': morseString += (MorseCode.N);
                         break;
                case 'o': morseString += (MorseCode.O);
                         break;
                case 'p': morseString += (MorseCode.P);
                         break;
                case 'q': morseString += (MorseCode.Q);
                         break;
                case 'r': morseString += (MorseCode.R);
                         break;
                case 's': morseString += (MorseCode.S);
                         break;
                case 't': morseString += (MorseCode.T);
                         break;
                case 'u': morseString += (MorseCode.U);
                         break;
                case 'v': morseString += (MorseCode.V);
                         break;
                case 'w': morseString += (MorseCode.W);
                         break;
                case 'x': morseString += (MorseCode.X);
                         break;
                case 'y': morseString += (MorseCode.Y);
                         break;
                case 'z': morseString += (MorseCode.Z);
                         break;
                case '1': morseString += (MorseCode.ONE);
                         break;
                case '2': morseString += (MorseCode.TWO);
                         break;
                case '3': morseString += (MorseCode.THREE);
                         break;
                case '4': morseString += (MorseCode.FOUR);
                         break;
                case '5': morseString += (MorseCode.FIVE);
                         break;
                case '6': morseString += (MorseCode.SIX);
                         break;
                case '7': morseString += (MorseCode.SEVEN);
                         break;       
                case '8': morseString += (MorseCode.EIGHT);
                         break;       
                case '9': morseString += (MorseCode.NINE);
                         break;       
                case '0': morseString += (MorseCode.ZERO);
                         break;       
                default:  morseString += (MorseCode.SPACE);;
                         break;                                    
            }
        }
                  
        return morseString; 
    }
/*    private static void sleep() throws InterruptedException {
        for (;;) {
            Thread.sleep(500);  
        }
    }*/
}
