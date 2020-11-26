package com.company;

public class Synth {
    final int Pin1 = 1;
    int pin1Value = 0;
    final int Pin2 = 2;
    int pin2Value = 0;
    final int Pin3 = 3;
    int pin3Value = 0;
    final int Pin4 = 4;
    int pin4Value = 0;

    //byte[] synthByte;

    public Synth() {

    }

    public void receiveMidi(byte[] a) {


        switch(a[1]){
            case 1:
                pin1Value = a[2];
                System.out.println("Knob 1 value is set to: " + pin1Value);
                break;
            case 2:
                pin2Value = a[2];
                System.out.println("Knob 2 value is set to: " + pin2Value);
                break;
            case 3:
                pin3Value = a[2];
                System.out.println("Knob 3 value is set to: " + pin3Value);
                break;
            case 4:
                pin4Value = a[2];
                System.out.println("Knob 4 value is set to: " + pin4Value);
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }

        /*
        if (a[1] == Pin1) {
            pin1Value = a[2];
            //System.out.println("Pitch was set at " + pitch);
            synthByte = a;
        }*/
    }

    public float pin1Value(){
        return pin1Value;

    }
    public float pin2Value(){
        return pin2Value;

    }
    public float pin3Value(){
        return pin3Value;

    }
    public float pin4Value(){
        return pin4Value;

    }

}