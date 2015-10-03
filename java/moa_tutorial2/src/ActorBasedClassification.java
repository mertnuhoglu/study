import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.streams.clustering.FileStream;
import moa.streams.generators.RandomRBFGenerator;
import weka.core.Instance;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ActorBasedClassification {
    public static void main(String[] args) {
        classifyAllUsers();
    }

    public static void classifyAllUsers() {
        Map<Integer, Classifier> learnerMap = new HashMap(1000);
        FileStream stream = new FileStream();
        stream.arffFileOption.setValue("data.arff");
        stream.prepareForUse();
        int numberSamplesCorrect = 0;
        int numberSamples = 0;
        boolean isTesting = true;
        while (stream.hasMoreInstances()) {
            Instance trainInst = stream.nextInstance();
            int userId = (int) trainInst.value(0);

            Classifier learner = learnerMap.get(userId);
            if (learner == null) {
                learner = new HoeffdingTree();
                learner.setModelContext(stream.getHeader());
                learner.prepareForUse();
                learnerMap.put(userId, learner);
            }
            if (isTesting) {
                if (learner.correctlyClassifies(trainInst)) {
                    numberSamplesCorrect++;
                }
            }
            numberSamples++;
            learner.trainOnInstance(trainInst);
        }
        double accuracy = 100.0 * (double) numberSamplesCorrect / (double) numberSamples;
        System.out.println( numberSamples + "instances processed with " + accuracy + "% accuracy" );
    }

    public static void classifyOneUser() {
        Classifier learner = new HoeffdingTree();
        FileStream stream = new FileStream();
        stream.arffFileOption.setValue("data_user0.arff");
        stream.prepareForUse();
        learner.setModelContext(stream.getHeader());
        learner.prepareForUse();
        int numberSamplesCorrect = 0;
        int numberSamples = 0;
        boolean isTesting = true;
        while (stream.hasMoreInstances()) {
            Instance trainInst = stream.nextInstance();
            if (isTesting) {
                if (learner.correctlyClassifies(trainInst)) {
                    numberSamplesCorrect++;
                }
            }
            numberSamples++;
            learner.trainOnInstance(trainInst);
        }
        double accuracy = 100.0 * (double) numberSamplesCorrect / (double) numberSamples;
        System.out.println( numberSamples + "instances processed with " + accuracy + "% accuracy" );

    }
    public static void createData() throws Exception{

        FileOutputStream fos = new FileOutputStream("data.csv");

        for (int  i = 0; i < 1000; i++){
            int numInstances = 100;
            RandomRBFGenerator stream = new RandomRBFGenerator();
            stream.prepareForUse();
            int numberSamples = 0;
            while (stream.hasMoreInstances() && numberSamples < numInstances) {
                Instance trainInst = stream.nextInstance();
                String sampleStr = (i+"," + trainInst+"\n");
                fos.write(sampleStr.getBytes("UTF-8"));
                numberSamples++;
            }
        }
        fos.close();
    }

    private static void original() {
        int numInstances = 100000;
        Classifier learner = new HoeffdingTree();
        RandomRBFGenerator stream = new RandomRBFGenerator();
        stream.prepareForUse();
        learner.setModelContext(stream.getHeader());
        learner.prepareForUse();
        int numberSamplesCorrect = 0;
        int numberSamples = 0;
        boolean isTesting = true;
        while (stream.hasMoreInstances() && numberSamples < numInstances) {
            Instance trainInst = stream.nextInstance();
            if (isTesting) {
                if (learner.correctlyClassifies(trainInst)) {
                    numberSamplesCorrect++;
                }
            }
            numberSamples++;
            learner.trainOnInstance(trainInst);
        }
        double accuracy = 100.0 * (double) numberSamplesCorrect / (double) numberSamples;
        System.out.println( numberSamples + "instances processed with " + accuracy + "% accuracy" );

    }

}
