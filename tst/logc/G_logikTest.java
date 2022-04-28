package logc;

import mediaDB.Items;
import mediaDB.Tag;
import observerPatternNoGoodEnought.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class G_logikTest  {
    G_logik g_logik;
    String exceptionMessage = "";
    Darstellung_logik client ;
    UploaderImpl uploader, uploader1;
    InteractiveVideoImpl interactiveVideo5;
    Beobachter beobachterKapazitaet;
    Beobachter beobachterTags;
    ConcreteSubjectCapasity concreteSubjectCapasity;
    ConcreteSubjectTags concreteSubjectTags;
    @BeforeEach
    void setUp() throws Exception {
        client = new Darstellung_logik();
        g_logik = new G_logik();

        uploader = new UploaderImpl("Tovias Nunez");
        uploader1 = new UploaderImpl("Ameli Nunez");
        g_logik.createProduzenten(uploader);
        interactiveVideo5 = new InteractiveVideoImpl();
        interactiveVideo5.setUploader(uploader);
        interactiveVideo5.setTags(Collections.singleton(Tag.Animal));
        client.addItems(interactiveVideo5);


    }

    @Test
    void cretaeProduzenten() throws Exception {

        g_logik.createProduzenten(uploader);


    }

    /**
     * list the creator with the amount a product
     *
     * @throws Exception
     */
    @Test
    void getListProduzenten() throws Exception {

        InteractiveVideoImpl interactiveVideo = new InteractiveVideoImpl();
        InteractiveVideoImpl interactiveVideo1 = new InteractiveVideoImpl();
        InteractiveVideoImpl interactiveVideo2 = new InteractiveVideoImpl();
        InteractiveVideoImpl interactiveVideo3 = new InteractiveVideoImpl();

        LicensedAudioImpl licensedAudio1 = new LicensedAudioImpl();
        LicensedAudioImpl licensedAudio2 = new LicensedAudioImpl();

        LicensedAudioVideoImpl licensedAudioVideo = new LicensedAudioVideoImpl();
        LicensedAudioVideoImpl licensedAudioVideo2 = new LicensedAudioVideoImpl();


        interactiveVideo.setUploader(new UploaderImpl("Mathias"));
        //   g_logik.addItems(interactiveVideo);
        interactiveVideo1.setUploader(uploader);
        interactiveVideo3.setUploader(uploader);
        interactiveVideo.setUploader(uploader);
        interactiveVideo2.setUploader(uploader);

        licensedAudio1.setUploader(uploader);
        licensedAudio2.setUploader(uploader);


        licensedAudioVideo.setUploader(uploader1);
        licensedAudioVideo2.setUploader(uploader1);

        assertTrue(client.addItems(interactiveVideo));
        assertTrue(client.addItems(interactiveVideo2));
        assertTrue(client.addItems(interactiveVideo3));
        assertTrue(client.addItems(interactiveVideo1));
        assertTrue(client.addItems(licensedAudio1));
        assertTrue(client.addItems(licensedAudio2));

        assertTrue(client.addItems(licensedAudioVideo));
        assertTrue(client.addItems(licensedAudioVideo2));
        assertTrue(client.addItems(licensedAudio2));


      System.out.println(client.getItemsList().size());
      System.out.println(g_logik.getListProduzenten(client.getItemsList()));

    }

    @Test
    void testPreufenObProduzenterExitiert() {
        assertFalse(g_logik.preufenObProduzenterExitiert(uploader1));
    }

    @Test
    void testPrueftGesamtkapazitaet() {
        try {
            g_logik.prueftGesamtkapazitaet(BigDecimal.valueOf(101));
        } catch (IllegalArgumentException exception) {
            exceptionMessage = exception.getMessage();
        }
        assertEquals("Kapazitat wurde ueberschritten", exceptionMessage);

    }

    @Test
    void testPrueftGesamtkapazitaet2() {

            g_logik.prueftGesamtkapazitaet(BigDecimal.valueOf(92.000001));

    }


    @Test
    void loeschenProduzenten() {

        assertFalse(g_logik.loeschenProduzenten(uploader1));
        assertTrue(g_logik.loeschenProduzenten(uploader));
        g_logik.getListProduzenten(client.getItemsList());
    }

    @Test
    void removeItems() throws Exception {
        LicensedAudioImpl licensedAudio = new LicensedAudioImpl();
        assertTrue(client.addItems(licensedAudio));
      //  client.getItemsList().stream().forEach(x -> System.out.println(x));

        assertTrue(client.removeItems(licensedAudio));

        client.getItemsList().stream().forEach(x -> System.out.println(x));
    }

    @Test
    void testgetList() throws Exception {

        InteractiveVideoImpl interactiveVideo = new InteractiveVideoImpl();
        LicensedAudioImpl licensedAudio = new LicensedAudioImpl();

        interactiveVideo.setUploader(uploader1);
        client.addItems(interactiveVideo);


        client.addItems(new LicensedAudioImpl());
        licensedAudio.setAddress("test address");
        client.addItems(licensedAudio);

        System.out.println(g_logik.getList(Items.LICENSEDAUDIO , client.getItemsList()));
        //  System.out.println(g_logik.getItemsAndIDList());


    }


    @Test
    void preufenObProduzenterExitiert() {
        assertTrue(g_logik.preufenObProduzenterExitiert(uploader));
    }



    @Test
    void hochladen() throws Exception {

        interactiveVideo5.setUploader(new UploaderImpl("Karl"));
        interactiveVideo5.setSize(BigDecimal.valueOf(80));
        interactiveVideo5.setAddress("google.com");
        interactiveVideo5.setAccessCount(12345);
        assertFalse(g_logik.hochladen(interactiveVideo5));
        interactiveVideo5.setUploader(uploader);
        assertTrue(g_logik.hochladen(interactiveVideo5));
    }


    @Test
    void getItemsList() {

        client.getItemsList().stream().forEach(v -> System.out.println(v));


    }

    @Test
    void getList() {
        System.out.println( g_logik.getList(Items.INTERACTIVEVIDEO , client.getItemsList())) ;
    }

    @Test
    void getAccount() {
        interactiveVideo5.setAccessCount(12345);
      assertEquals( 12345,g_logik.getAccount(interactiveVideo5.getAccessCount()));

    }


    @Test
    void testGetNichtvergebenenTags(){
     g_logik.getNichtvergebenenTags(client.getItemsList()).stream().forEach(x -> System.out.println(x));

    }

// dont work
    @Test
    void observerFuction() throws Exception {

        ConcreteSubjectTags concreteSubjectTags1 = new ConcreteSubjectTags();
        BeobachterTags beobachterTags1 = new BeobachterTags(concreteSubjectTags1);
        InteractiveVideoImpl interactiveVideo1 = new InteractiveVideoImpl();

        interactiveVideo1.setTags(Collections.singleton(Tag.Animal));


        interactiveVideo1.setTags(Collections.singleton(Tag.Animal));

        interactiveVideo1.setTags(Collections.singleton(Tag.Animal));


        client.addItems(interactiveVideo1);

        interactiveVideo1.setTags(Collections.singleton(Tag.Animal));
        interactiveVideo1.setTags(Collections.singleton(Tag.News));

       concreteSubjectTags1.notificate(interactiveVideo1.getTags());


    }

}