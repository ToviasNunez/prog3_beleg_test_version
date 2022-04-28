package observerPatternNoGoodEnought;

public class BeobachterKapazitaet implements Beobachter {

    private Subject subject;
    public BeobachterKapazitaet(ConcreteSubjectCapasity subject){
        subject.meldeAn(this);
    }
    @Override
    public void update(Object obj) {

        System.out.println("The Data media has used more than 90% from the total capacity Datasize:" + obj);
    }
}
