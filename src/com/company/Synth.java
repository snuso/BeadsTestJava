package com.company;

public class Synth {
    final int pitchSlider = 1;
    int pitch = 0;
    byte[] synthByte;

    public Synth() {

    }

    public void receiveMidi(byte[] a) {
        if (a[1] == pitchSlider) {
            pitch = a[2];
            //System.out.println("Pitch was set at " + pitch);
            synthByte = a;
        }
    }

    public float getPin(){
        return pitch;

    }
}