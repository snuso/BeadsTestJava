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
        gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);

        // connect gsp to ac
        ac.out.addInput(gsp);
        ac.start();

        // instantiate synth and midikeyboard
        Synth synth = new Synth();
        MidiKeyboard midiKeyboard = new MidiKeyboard(synth);

        // while-loop to configure modifiers live
        while(sampleReady){

            gsp.setGrainSize(new Static(ac, 500));

            //knob 1
            gsp.setGrainSize(new Static(ac, synth.pin1Value()));
            //knob 2
            gsp.setPitch(new Static(ac, synth.pin2Value()));
            //knob 3
            gsp.setGrainInterval(new Static(ac, synth.pin3Value()));

        }
    }
}