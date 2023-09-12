package drummachine;

import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class MiniMusicPlayer {

    public static void main(String[] args) {
        var app = new MiniMusicPlayer();
        app.play();
    }

    public void play() {
        try {
            var sequencer = MidiSystem.getSequencer();
            sequencer.open();

            var sequence = new Sequence(Sequence.PPQ, 4);
            var track = sequence.createTrack();

            var msg_1 = new ShortMessage();
            msg_1.setMessage(NOTE_ON, 1, 44, 100);
            var noteOnEvent = new MidiEvent(msg_1, 1);
            track.add(noteOnEvent);

            var msg_2 = new ShortMessage();
            msg_2.setMessage(NOTE_ON, 1, 44, 100);
            var noteOffEvent = new MidiEvent(msg_2, 16);
            track.add(noteOffEvent);

            sequencer.setSequence(sequence);
            sequencer.start();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
