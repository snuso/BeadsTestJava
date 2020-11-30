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


            if(synth.getPadValue(44) == 44){
                gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
                System.out.println("Loop set to " + gsp.getLoopType());
            }
            if(synth.getPadValue(45) == 45){
                gsp.setLoopType(SamplePlayer.LoopType.LOOP_BACKWARDS);
                System.out.println("Loop set to " + gsp.getLoopType());
            }

            // System.out.println(synth.getPadValue(44));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}