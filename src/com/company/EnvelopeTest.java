package com.company;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;


class EnvelopeTest {
    {
        // create an AudioContext
        AudioContext ac = new AudioContext();

        // create an Envelope to control the frequency
        Envelope frequencyEnvelope = new Envelope(ac, 440.0f);

        // create a sine generator
        WavePlayer sine = new WavePlayer(ac, frequencyEnvelope, Buffer.SINE);

        // create an Envelope to control the gain
        Envelope martinsEnvelope = new Envelope(ac, 100.0f);

        // create a Gain to control the sine volume
        Gain sineGain = new Gain(ac, 1, martinsEnvelope);

        // add the sine generator as an input to the Gain
        sineGain.addInput(sine);

        // add the Gain as an input to the master output, ac.out
        ac.out.addInput(sineGain);

        // begin audio processing
        ac.start();

        martinsEnvelope.addSegment(0.3f, 2000);

        // ramp the gain to 0.9f over 500 ms
        martinsEnvelope.addSegment(0.9f, 1000.0f);

        // ramp the gain to 0.0f over 500 ms
        martinsEnvelope.addSegment(0.0f, 1000.0f);

        // ramp the frequency up to 2000Hz over 1000 ms
        frequencyEnvelope.addSegment(1000.0f, 1000.0f);
    }
}
