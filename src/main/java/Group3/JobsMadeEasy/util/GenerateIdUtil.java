package Group3.JobsMadeEasy.util;


import java.util.concurrent.ThreadLocalRandom;

public class GenerateIdUtil {

    public int generateRandomId(){
        return ThreadLocalRandom.current().nextInt(1, 10000 + 1);
    }
}
