package com.company;
import java.util.HashMap;

public class Synth {
    int[] knobValues = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    int[] padValues = new int[]{0};

    int pad1Value = 44;
    int pad2Value = 45;
    int pad3Value = 46;
    int pad4Value = 47;
    int pad5Value = 48;

    //byte[] synthByte;
    public Synth() {
    }

    public void receiveKnobMidi(byte[] a) {
        if (a[1] > 0 && a[1] <= knobValues.length) {
            knobValues[a[1]] = a[2];
            System.out.println("Knob " + a[1] + " value is set to " + knobValues[a[1]]);
        } else {
            System.out.println("Something went wrong");
        }
    }

    public float getKnobValue(int index) {
        if (index > 0 && index <= knobValues.length) {
            return knobValues[index];
        } else {
            return 0;
        }
    }

    public int getPad1Value() {
        return pad1Value;
    }

    public int getPad2Value() {
        return pad2Value;
    }

    public int getPad3Value() {
        return pad3Value;
    }

    public int getPad4Value() {
        return pad4Value;
    }

    public int getPad5Value() {
        return pad5Value;
    }

    public void receivePadMidi(byte[] a) {
        if (a[1] > 43 && a[1] < 49) {
            System.out.println(a[1]);
            padValues[0] = a[1];
            System.out.println("Active pad is " + padValues[0]);

        } else {
            System.out.println("Something went wrong");
        }
    }

    public int getPadValue() {
        return padValues[0];
    }
    public void setPadValue(int i){
        padValues[0] = i;
    }

}