package com.company;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.GranularSamplePlayer;
import net.beadsproject.beads.ugens.SamplePlayer;
import net.beadsproject.beads.ugens.Static;

public class Main{
    public static void main(String[] args){
        AudioContext ac = new AudioContext();

        // load the source sample from a file
        Sample sourceSample = null;
        boolean sampleReady = false;
        try{
            sourceSample = new Sample("alarm05.wav");
            sampleReady = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // instantiate a GranularSamplePlayer
        GranularSamplePlayer gsp = new GranularSamplePlayer(ac, sourceSample);

        // which loop?

        // connect gsp to ac
        ac.out.addInput(gsp);
        gsp.setPitch(new Static(ac, 1));


        // instantiate synth and midikeyboard
        Synth synth = new Synth();
        MidiKeyboard midiKeyboard = new MidiKeyboard(synth);

        gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);

        ac.start();
        // while-loop to configure modifiers live
        while(sampleReady){

            // gsp.setGrainSize(new Static(ac, 1000));
            // knob
            gsp.setPitch(new Static(ac, (float) (synth.getKnobValue(1)*(0.1))));

            if(synth.getPad1Value() == 44){
                System.out.println("44 has been triggered");
                gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
                return;
            }
            if(synth.getPad2Value() == 45){
                System.out.println("45 has been triggered");
                gsp.setLoopType(SamplePlayer.LoopType.LOOP_BACKWARDS);
                return;
            }
            if(synth.getPad3Value() == 46){
                System.out.println("46 has been triggered");
                gsp.setLoopType(SamplePlayer.LoopType.LOOP_ALTERNATING);
                return;
            }
            if(synth.getPad4Value() == 47){
                System.out.println("47 has been triggered");
                gsp.setLoopType(SamplePlayer.LoopType.NO_LOOP_FORWARDS);
                return;
            }
            if(synth.getPad5Value() == 48){
                System.out.println("48 has been triggered");
                gsp.setLoopType(SamplePlayer.LoopType.NO_LOOP_BACKWARDS);
                return;
            }
        }
    }
}