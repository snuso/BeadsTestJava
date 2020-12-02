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
            sourceSample = new Sample("bells.wav");
            sampleReady = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        // instantiate a GranularSamplePlayer
        GranularSamplePlayer gsp = new GranularSamplePlayer(ac, sourceSample);

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
            // knobs
            gsp.setPitch(new Static(ac, (float) (synth.getKnobValue(1)*(0.1))));
            gsp.setGrainSize(new Static(synth.getKnobValue(2)));
            gsp.setGrainInterval(new Static((float) (synth.getKnobValue(3)*(0.1))));
            gsp.setRandomness(new Static(synth.getKnobValue(4)));
            gsp.setLoopStart(new Static((float) (synth.getKnobValue(5))*(100)));
            gsp.setLoopEnd(new Static((float) (synth.getKnobValue(6))*(100)));
            gsp.setLoopCrossFade(synth.getKnobValue(7));

            // Pads
            switch(synth.getPadValue()){
                case 44:
                    System.out.println("44 has been triggered");
                    gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
                    synth.setPadValue(0);
                    break;
                case 45:
                    System.out.println("45 has been triggered");
                    gsp.setLoopType(SamplePlayer.LoopType.LOOP_BACKWARDS);
                    synth.setPadValue(0);
                    break;
                case 46:
                    System.out.println("46 has been triggered");
                    gsp.setLoopType(SamplePlayer.LoopType.LOOP_ALTERNATING);
                    synth.setPadValue(0);
                    break;
                case 47:
                    System.out.println("47 has been triggered");
                    gsp.setLoopType(SamplePlayer.LoopType.NO_LOOP_FORWARDS);
                    synth.setPadValue(0);
                    break;
                case 48:
                    System.out.println("48 has been triggered");
                    gsp.setLoopType(SamplePlayer.LoopType.NO_LOOP_BACKWARDS);
                    synth.setPadValue(0);
                    break;
            }
        }
    }
}