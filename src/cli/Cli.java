package cli;

import logc.G_logik;
import logc.UploaderImpl;
import mediaDB.Uploader;

import java.util.HashSet;
import java.util.Set;

/**
 * create a cli for  new producer , two different data , remove one from them
 */
public class Cli {

    // create dataName
    //create producerName

    public static void main(String[] args) throws Exception {
        Command command = null;
        Items items = null;
        G_logik g_logik = new G_logik();

        Set<Uploader> producerList = new HashSet<>();
        Set<Object> mediaList = new HashSet<>();

        switch (command){
            case create:
                // if the data is a producer create producer
                switch (items){
                    case producer:
                        System.out.println("create producer ");
                        g_logik.createProduzenten(new UploaderImpl("tovias"));
                    case interactiveV:
                        System.out.println("create interactive video");
                       mediaList.add("interactive");
                    case licensedA:
                        System.out.println("create licensed Audio");
                        mediaList.add("licensed audio");
                    case licensedV:
                        System.out.println("create licensed video");
                        mediaList.add("licensed Video");
                    case licensedVA:
                        System.out.println("create licensed audio video");


                }

            case deleted:
                System.out.println("deleted data ");
                switch (items){
                    case producer:

                }



        }



    }



}


enum Items{
    interactiveV , licensedV, licensedVA, licensedA , producer
}