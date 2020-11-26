package com.company;


import com.sun.org.apache.xml.internal.utils.StringToIntTable;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.GranularSamplePlayer;
import net.beadsproject.beads.ugens.SamplePlayer;
import net.beadsproject.beads.ugens.Static;

import javax.sound.midi.MidiSystem;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        // instantiate the AudioContext
        AudioContext ac = new AudioContext();

        // load the source sample from a file
        Sample sourceSample = null;
        boolean sampleReady = false;
        try{
            sourceSample = new Sample("alarm05.wav");
        }
        catch(Exception e){
            /*
             * If the program exits with an error message,
             * then it most likely can't find the file
             * or can't open it. Make sure it is in the
             * root folder of your project in Eclipse.
             * Also make sure that it is a 16-bit,
             * 44.1kHz audio file. These can be created
             * using Audacity.
             */
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // instantiate a GranularSamplePlayer
        GranularSamplePlayer gsp = new GranularSamplePlayer(ac, sourceSample);

        // tell gsp to loop the file
        gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);

        // set the grain size to a fixed 10ms

        // tell gsp to behave somewhat randomly
        // gsp.setRandomness(new Static(ac, 1000.0f));

        // connect gsp to ac
        ac.out.addInput(gsp);
        ac.start();
        sampleReady = true;

        Synth synth = new Synth();
        MidiKeyboard midiKeyboard = new MidiKeyboard(synth);
        while(sampleReady){

            System.out.println("ScannerFloat is set to: " + synth.getPin());
            System.out.println();
            gsp.setGrainSize(new Static(ac, 500));

            gsp.setGrainSize(new Static(ac, synth.getPin() ));
            try {
                Thread.sleep(500);
            }catch(InterruptedException e){

            }
        }
    }
}