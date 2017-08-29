package home.sandbox.myspring;

public class Main {
    public static void main(String[] args) {
       /* IRobot iRobot = ObjectFactory.getInstance().createObject(IRobot.class);
        iRobot.cleanRoom();*/

        Worker worker = ObjectFactory.getInstance().createObject(Worker.class);
        worker.drinkBeer();
        worker.drinkBeer(2);
        worker.work();

        System.out.println(worker.getClass());
        PopupSpeaker speaker = ObjectFactory.getInstance().createObject(PopupSpeaker.class);
        System.out.println(speaker.getClass());

    }
}
