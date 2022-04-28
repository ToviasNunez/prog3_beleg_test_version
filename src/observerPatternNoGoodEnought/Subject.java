package observerPatternNoGoodEnought;


public interface  Subject {



   public void meldeAn(Beobachter beobachter);

    public void meldeAb(Beobachter beobachter);

   public  void notificate(Object obj);

}
