package logc;

import mediaDB.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class G_logik {

    private final Set<UploaderImpl> produzenten = new HashSet<>();

    private final AtomicBoolean atomicBoolean = new AtomicBoolean();

    private static int maxsize = 100;
    private  static double  alertsize = maxsize * 90 / 100;
    private final static BigDecimal MAXKAPAZITAET = BigDecimal.valueOf(maxsize);
    private final static BigDecimal ALERTSIZE = BigDecimal.valueOf(alertsize);

    private final StringBuilder sb = new StringBuilder();


    /**
     * @param uploader
     * @throws Exception should no be default or null
     */
    public void createProduzenten(UploaderImpl uploader) throws Exception {
        if (uploader == null || uploader.getName().equals("MUSTERMAN"))
            throw new IllegalArgumentException("Uploader should not be null oder MUSTERMAN");
        else {

            this.addProduzenten(uploader);
        }

    }



    /**
     * adding unique uploader date to the list
     *
     * @param uploader
     */
    private void addProduzenten(UploaderImpl uploader) {

        if (!produzenten.isEmpty() && !produzenten.contains(uploader)) {
            if (!produzenten.stream().anyMatch(creator -> uploader.getName().equals(creator.getName()))) {
                produzenten.add(uploader);
            }
        } else
            produzenten.add(uploader);
    }


    /**
     * only the creator that has been register.
     * @return list from creator with the amount of item have to make for everyone
     */

    public String getListProduzenten(List<?> databank) {

        List<Uploader> uploaderFromData = new ArrayList<>();
        List<Uploader> producer = new ArrayList<>();
        databank.stream().filter(v -> v instanceof InteractiveVideoImpl).map(v -> uploaderFromData.add(((InteractiveVideoImpl) v).getUploader())).collect(Collectors.toList());
        databank.stream().filter(v -> v instanceof LicensedVideoImpl).map(v -> uploaderFromData.add(((LicensedVideoImpl) v).getUploader())).collect(Collectors.toList());
        databank.stream().filter(v -> v instanceof LicensedAudioImpl).map(v -> uploaderFromData.add(((LicensedAudioImpl) v).getUploader())).collect(Collectors.toList());
        databank.stream().filter(v -> v instanceof LicensedAudioVideoImpl).map(v -> uploaderFromData.add(((LicensedAudioVideoImpl) v).getUploader())).collect(Collectors.toList());

         uploaderFromData.stream().filter(produzenten::contains).map( x -> producer.add(x)).collect(Collectors.toList());

        return countFrequnecies(producer);
    }

    /**
     * @param list
     * @return the frequency value with the amount of the frequency
     * list the creator with amount of the products
     */
    private static String countFrequnecies(List<?> list) {

        StringBuilder stringBuffer = new StringBuilder();

        Map<Object, Integer> lagerverwaltung = new HashMap<>();

        for (Object objects : list) {

            Integer j = lagerverwaltung.get(objects);
            lagerverwaltung.put(objects, (j == null) ? 1 : j + 1);
        }

        for (Map.Entry<Object, Integer> val : lagerverwaltung.entrySet()) {

            stringBuffer.append(val).append(System.lineSeparator());
        }

        return stringBuffer.toString();

    }


    /**
     * check if the uploader is in the list
     *
     * @param uploader produzenten
     * @return if the uploader ist in the list
     */
    public boolean preufenObProduzenterExitiert(UploaderImpl uploader) {
        atomicBoolean.set(produzenten.stream().anyMatch(produzenter -> uploader.getName().equals(produzenter.getName())));
        return atomicBoolean.get();
    }




    /**
     * check if the capacity are no over rider
     *
     * @param kapazitaet the max size that the data can be
     * @return if the check it is correct
     */
    public BigDecimal prueftGesamtkapazitaet(BigDecimal kapazitaet) {
        //atomicBoolean.set(false);
        if (kapazitaet.compareTo(MAXKAPAZITAET) <= -1 ) {

            if ( kapazitaet.compareTo(ALERTSIZE) == 1 ){

            }
            return kapazitaet;
        } else
            throw new IllegalArgumentException("Kapazitat wurde ueberschritten");

    }


    public boolean hochladen(Object obj) {

        if (obj instanceof InteractiveVideoImpl) {

            if (preufenObProduzenterExitiert((UploaderImpl) ((InteractiveVideo) obj)
                    .getUploader())) {
                synchronized (this) {
                    ((InteractiveVideo) obj).getAddress();
                }

                this.prueftGesamtkapazitaet(((InteractiveVideoImpl) obj).getSize());
                ((InteractiveVideo) obj).getUploadDate();
            }
        }
        if (obj instanceof Licensed) {

            if (obj instanceof LicensedAudio) {

                if (preufenObProduzenterExitiert((UploaderImpl) ((LicensedAudio) obj).getUploader())) {
                    synchronized (this) {
                        ((LicensedAudio) obj).getAddress();
                    }
                    this.prueftGesamtkapazitaet(((LicensedAudio) obj).getSize());

                    ((LicensedAudio) obj).getUploadDate();
                }
            }
            if (obj instanceof LicensedVideo) {

                if (preufenObProduzenterExitiert((UploaderImpl) ((LicensedVideo) obj).getUploader())) {
                    synchronized (this) {
                        ((LicensedVideo) obj).getAddress();
                    }

                    this.prueftGesamtkapazitaet(((LicensedVideo) obj).getSize());
                    ((LicensedVideo) obj).getUploadDate();
                }
            }
            if (obj instanceof LicensedAudioVideo) {

                if ( preufenObProduzenterExitiert((UploaderImpl) ((LicensedAudioVideo) obj).getUploader())) {
                    synchronized (this) {
                        ((LicensedAudioVideo) obj).getAddress();
                    }

                    this.prueftGesamtkapazitaet(((LicensedAudioVideo) obj)
                            .getSize());

                    ((LicensedAudioVideo) obj).getUploadDate();
                }
            }

        }

        return atomicBoolean.get();
    }


    /**
     * @param uploader deleted the uploader from the list
     * @return true or false if the uploader was deleted
     */
    public boolean loeschenProduzenten(UploaderImpl uploader) {

        if (produzenten.contains(uploader)) {
            produzenten.remove(uploader);
            atomicBoolean.set(true);
        } else
            atomicBoolean.set(false);
        return atomicBoolean.get();
    }


    /**
     * @return list  from type of media
     */
    /**
     * @return list  from type of media
     */
    public String getList(Items items , List<?> list) {

        switch (items) {
            case INTERACTIVEVIDEO:
                sb.append(list.stream().filter(x -> x instanceof InteractiveVideo).collect(Collectors.toList()));
            case LICENSEDVIDEO:

                sb.append(list.stream().filter(x -> x instanceof LicensedVideo).collect(Collectors.toList()));
            case LICENSEDAUDIO:

                sb.append(list.stream().filter(x -> x instanceof LicensedAudio).collect(Collectors.toList()));
            case LICENSEDAUDIOVIDEO:

                sb.append(list.stream().filter(x -> x instanceof LicensedAudioVideo).collect(Collectors.toList()));
        }

        return sb.toString();
    }

    /**
     * @param accessCount form the datamedia
     * @return access count
     */
    public long getAccount(long accessCount) {
        return accessCount;
    }

    /**
     * get all the tags the are not used ob the program
     *
     * @return nicht gegebenen tags
     */
    public List<Collection<Tag>> getNichtvergebenenTags(List<?> itemsList) {

        List<Collection<Tag>> tags = new ArrayList<>();
        List<Collection<Tag>> usedTagList = new ArrayList<>();


        itemsList.stream().filter(v -> v instanceof InteractiveVideo).map(v -> usedTagList.add(((InteractiveVideo) v).getTags())).collect(Collectors.toList());
        itemsList.stream().filter(v -> v instanceof Licensed).map(v -> usedTagList.add(((Licensed) v).getTags())).collect(Collectors.toList());


        for (Collection<Tag> tag : usedTagList) {
            Arrays.stream(Tag.values())
                    .filter(obj -> !tag.contains(obj))
                    .map(o -> tags.add(Collections.singleton(o))).collect(Collectors.toList());

        }


        return tags;
    }



}
