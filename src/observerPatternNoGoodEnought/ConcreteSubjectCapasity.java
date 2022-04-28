package observerPatternNoGoodEnought;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubjectCapasity implements Subject {


  private List<Beobachter> beobachterList = new ArrayList<>();

    @Override
    public void meldeAn(Beobachter beobachter) {
        beobachterList.add(beobachter);
    }

    @Override
    public void meldeAb(Beobachter beobachter) {
 beobachterList.remove(beobachter);
    }

    @Override
    public void notificate(Object obj) {

        for (Beobachter beobachter: beobachterList){
            beobachter.update(obj);
        }
    }
}
