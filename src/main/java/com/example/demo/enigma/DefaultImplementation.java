package com.example.demo.enigma;

import com.example.demo.enigma.dto.DecryptRequest;
import com.example.demo.enigma.dto.EncryptRequest;
import com.example.demo.enigma.dto.Output;
import com.example.demo.enigma.dto.Settings;
import com.example.demo.services.EnigmaServices;
import lombok.var;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("/")
@RestController
public class DefaultImplementation implements EnigmaServices {
    private static final int MAX_VALUE = 26;
    private static String MAP = "ABCZEFGHIJKLMNOPQRSTUVWXYD";
    private static int[] r1, r2, r3;
    private static int pos1 = 0, pos2 = 0;
    private static int[] prevStates;

    public DefaultImplementation() {
        r1 = new int[MAX_VALUE];
        r2 = new int[MAX_VALUE];
        r3 = new int[MAX_VALUE];
        prevStates = new int[3];
        init(r1);
        init(r2);
        init(r3);
    }

    @Override
    public Output enigmaEncrypt(EncryptRequest val) {
        StringBuilder out = new StringBuilder();
        prevStates[0] = r1[0];
        prevStates[1] = r2[0];
        prevStates[2] = r3[0];
        for (int i = 0; i < val.getMessage().length(); i++) {
            out.append(getEnigma(val.getMessage().charAt(i), r1, r2, r3));
            rotate(r1);
            pos1++;
            if (pos1 > MAX_VALUE) {
                pos1 = 0;
                rotate(r2);
                pos2++;
            }
            if (pos2 > MAX_VALUE) {
                pos2 = 0;
                rotate(r3);
            }
        }
        Output finalOut = new Output();
        finalOut.setBody(out.toString());
        finalOut.setInitialValues(Arrays.asList(prevStates[0], prevStates[1], prevStates[2]));
        finalOut.setMapping(MAP);
        return finalOut;
    }

    @Override
    public String setConfig(Settings set) {
        MAP = set.getMapping();
        for (int i = 0; i < MAX_VALUE; i++) {
            r1[i] = (set.getInitialValues().get(0) + i) % 26;
            if (r1[i] == 0) r1[i] = 26;
        }
        for (int i = 0; i < MAX_VALUE; i++) {
            r2[i] = (set.getInitialValues().get(1) + i) % 26;
            if (r2[i] == 0) r2[i] = 26;
        }
        for (int i = 0; i < MAX_VALUE; i++) {
            r3[i] = (set.getInitialValues().get(2) + i) % 26;
            if (r3[i] == 0) r3[i] = 26;
        }
        return print(r1) + print(r2) + print(r3);
    }

    @Override
    public String enigmaDecrypt(DecryptRequest decryptRequest) {
        var settings = new Settings();
        settings.setInitialValues(decryptRequest.getInitialValues());
        settings.setMapping(decryptRequest.getMapping());
        setConfig(settings);
        return enigmaEncrypt(new EncryptRequest(decryptRequest.getMessage())).getBody();
    }

    static char getEnigma(char input, final int[] r1, final int[] r2, final int[] r3) {
        if (input >= 97 && input <= 123 || input >= 65 && input <= 91) {
        } else {
            return input;
        }
        if (input >= 97) {
            input = (char) (input - 32);
        }
        int val = charMapGet(input) + 1;

        int curPos = val - 1;

        val = whatsAt(r1, curPos);

        curPos = whereIs(r2, val);

        val = whatsAt(r2, curPos);

        curPos = whereIs(r3, val);

        curPos = comp(curPos);

        val = whatsAt(r3, curPos);

        curPos = whereIs(r2, val);

        val = whatsAt(r2, curPos);

        curPos = whereIs(r1, val);

        return charMapGet(curPos);
    }

    static char charMapGet(int val) {
        char[] curSetting = MAP.toCharArray();
        return curSetting[val];
    }

    static int charMapGet(char val) {
        char[] curSetting = MAP.toCharArray();
        for (int i = 0; i < MAP.length(); i++) {
            if (curSetting[i] == val) return i;
        }
        return -1;
    }

    static void init(final int[] rotor) {
        for (int i = 0; i < MAX_VALUE; i++) {
            rotor[i] = i + 1;
        }
    }

    static void rotate(final int[] rotor) {
        int val = rotor[MAX_VALUE - 1];
        for (int i = MAX_VALUE - 2; i >= 0; i--) {
            rotor[i + 1] = rotor[i];
        }
        rotor[0] = val;
    }

    static String print(final int[] rotor) {
        String out = "";
        //    System.out.print("[ ");
        out = "[";
        for (int i = 0; i < MAX_VALUE; i++) {
            //  System.out.print(rotor[i] + " , ");
            out += rotor[i] + ",";
        }
        //   System.out.println(" ]");
        out += "]\n";
        return out;
    }

    static int whereIs(int[] rotor, int val) {
        for (int i = 0; i < MAX_VALUE; i++) {
            if (rotor[i] == val) return i;
        }
        return -1;
    }

    static int whatsAt(int[] rotor, int index) {
        return rotor[index];
    }

    static int comp(int val) {
        return MAX_VALUE - val - 1;
    }
}



