package home.sandbox.myspring;
import javax.annotation.PostConstruct;

@Benchmark
public class IRobot {
    private Speaker speaker;

    @PostConstruct
    public void init() {
        System.out.println(speaker.getClass());
    }

    @InjectByType
    private Cleaner cleaner;

    @InjectByType
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public void cleanRoom() {
        speaker.speak("я начал уборку");
        cleaner.clean();
        speaker.speak("я закончил уборку");
    }

}
