import javax.sound.sampled.*;
import javax.sound.sampled.ReverbType;

public class Reverb {

	private String name;
	private final int earlyReflectionDelay;
	private final float earlyReflectionIntensity;
	private final int lateReflectionDelay;
	private final float lateReflectionIntensity;
	private final int decayTime;

	protected Reverb(String name, 
			int earlyReflectionDelay, 
			float earlyReflectionIntensity, 
			int lateReflectionDelay, 
			float lateReflectionIntensity, 
			int decayTime) {
		this.name=name;
		this.earlyReflectionDelay=earlyReflectionDelay;
		this.earlyReflectionIntensity=earlyReflectionIntensity;
		this.lateReflectionDelay=lateReflectionDelay;
		this.lateReflectionIntensity=lateReflectionIntensity;
		this.decayTime=decayTime;
	}
	public String getName() {
		return name;
	}
	public final int getEarlyReflectionDelay() {
		return earlyReflectionDelay;
	}
	public final float getEarlyReflectionIntensity() {
		return earlyReflectionIntensity;
	}
	public final int getLateReflectionDelay() {
		return lateReflectionDelay;
	}
	public final float getLateReflectionIntensity(){
		return lateReflectionIntensity;
	}
	public final int getDecayTime() {
		return decayTime;
	}
	public final boolean equals(Object obj) {
		return super.equals(obj);
	}
	public final int hashCode() {
		return super.hashCode();
	}
	public final String toString() {
		return (name + ", early reflection delay " + earlyReflectionDelay + 
				" ms, early reflection intensity " + earlyReflectionIntensity + 
				" dB, late reflection delay " + lateReflectionDelay + 
				" ms, late reflection intensity " + lateReflectionIntensity + 
				" dB, decay time " + decayTime);
	}
}
