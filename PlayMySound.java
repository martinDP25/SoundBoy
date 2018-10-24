package com.jsyn.examples;

import java.io.File;
import java.io.IOException;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.data.FloatSample;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.VariableRateDataReader;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.SampleLoader;

public class PlayMySound {
	public static final double LOOP_START=0.2;
	public Synthesizer mySynth;
	public LineOut lineOut;
	public VariableRateDataReader samplePlayer;
	public FloatSample floSample;
	public int loopStartFrame;

	public void test() {

		File mySample;
		try {
			mySample= new File("/Users/home/Desktop/BirdsnShit.wav");
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}

		mySynth=JSyn.createSynthesizer();


		try {
			mySynth.add(lineOut=new LineOut());
			SampleLoader.setJavaSoundPreferred(false);

			floSample=SampleLoader.loadFloatSample(mySample);

			System.out.println("Sample Directory: " + mySample);
			System.out.println("Sample Rate: " + floSample.getFrameRate());
			System.out.println("Sample Pitch: " + floSample.getPitch());
			//System.out.println("Sample Length: " + floSample.);

			if(floSample.getChannelsPerFrame()==1) 
			{
				mySynth.add(samplePlayer=new VariableRateMonoReader());
				samplePlayer.output.connect(0, lineOut.input, 0);
			}else if(floSample.getChannelsPerFrame()==2)
			{
				mySynth.add(samplePlayer=new VariableRateStereoReader());
				samplePlayer.output.connect(0, lineOut.input, 0);
				samplePlayer.output.connect(1, lineOut.input, 1);
			}else
			{
				throw new RuntimeException("Can only play mono or stereo samples.");
			}
			mySynth.start();

			samplePlayer.rate.set(floSample.getFrameRate());

			lineOut.start();

			loopStartFrame = (int) (floSample.getNumFrames() * LOOP_START);

			if (floSample.getSustainBegin() < 0) {
				//System.out.println("queue the sample");
				samplePlayer.dataQueue.queue(floSample);
			} else {
				//System.out.println("queueOn the sample");
				samplePlayer.dataQueue.queueOn(floSample);
				mySynth.sleepFor(8.0);
				//System.out.println("queueOff the sample");
				samplePlayer.dataQueue.queueOff(floSample);
			}

			do {
				mySynth.sleepFor(1.0);
			} while (samplePlayer.dataQueue.hasMore());

			mySynth.sleepFor(0.5);

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mySynth.stop();
	}
	public static void main(String[] args) {
		new PlayMySound().test();
	}

}
