import moa.cluster.Clustering;
import moa.clusterers.clustream.Clustream;
import moa.evaluation.F1;
import moa.evaluation.MeasureCollection;
import moa.gui.visualization.DataPoint;
import moa.streams.clustering.ClusteringStream;
import moa.streams.clustering.RandomRBFGeneratorEvents;
import weka.core.DenseInstance;
import weka.core.Instance;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by mertnuhoglu on 16/08/15.
 */
public class Main {
    public static void main(String[] args) {
        int numInstances = 100000;
        ClusteringStream m_stream0 = new RandomRBFGeneratorEvents();
        m_stream0.prepareForUse();

        Clustream m_clusterer0 = new Clustream();
        m_clusterer0.setModelContext(m_stream0.getHeader());
        m_clusterer0.prepareForUse();

        int numberSamplesCorrect = 0;
        int numberSamples = 0;
        int timestamp = 0;
        boolean isTesting = true;
        LinkedList<DataPoint> pointBuffer0 = new LinkedList<DataPoint>();
        ArrayList<DataPoint> pointarray0 = null;
        Clustering gtClustering0 = null;
        Clustering macro0 = null;
        Clustering micro0 = null;

        while (m_stream0.hasMoreInstances() && numberSamples < numInstances) {
            timestamp++;
            Instance next0 = m_stream0.nextInstance();
            DataPoint point0 = new DataPoint(next0,timestamp);
            pointBuffer0.add(point0);

            Instance traininst0 = new DenseInstance(point0);
            if(m_clusterer0.keepClassLabel())
                traininst0.setDataset(point0.dataset());
            else
                traininst0.deleteAttributeAt(point0.classIndex());
            m_clusterer0.trainOnInstanceImpl(traininst0);
            pointarray0 = new ArrayList<DataPoint>(pointBuffer0);
            ArrayList<DataPoint> points0 = pointarray0;
            gtClustering0 = new Clustering(points0);
            Clustering evalClustering0 = null;

//            if(gtClustering0!= null){
//                if(m_clusterer0 instanceof ClusterGenerator)
//                    ((ClusterGenerator)m_clusterer0).setSourceClustering(gtClustering0);
//            }

            macro0 = m_clusterer0.getClusteringResult();
            evalClustering0 = macro0;

            //TODO: should we check if micro/macro is being drawn or needed for evaluation and skip otherwise to speed things up?
            if(m_clusterer0.implementsMicroClusterer()){
                micro0 = m_clusterer0.getMicroClusteringResult();
                if(macro0 == null && micro0 != null){
                    //TODO: we need a Macro Clusterer Interface and the option for kmeans to use the non optimal centers
                    macro0 = moa.clusterers.KMeans.gaussianMeans(gtClustering0, micro0);
                }
                if(m_clusterer0.evaluateMicroClusteringOption.isSet())
                    evalClustering0 = micro0;
                else
                    evalClustering0 = macro0;
            }

            evaluateClustering(evalClustering0, gtClustering0, points0, true);

//            if (isTesting) {
//                if (clusterer.(trainInst)) {
//                    numberSamplesCorrect++;
//                }
//            }
//            numberSamples++;
//            learner.trainOnInstance(trainInst);
        }
    }

    private static void evaluateClustering(Clustering found_clustering, Clustering trueClustering, ArrayList<DataPoint> points, boolean algorithm0){
        MeasureCollection[] m_measures0 = new MeasureCollection[1];
        m_measures0[0] = new F1();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m_measures0.length; i++) {
            if(algorithm0){
                if(found_clustering!=null && found_clustering.size() > 0){
                    try {
                        MeasureCollection measure = m_measures0[i];
                        double msec = m_measures0[i].evaluateClusteringPerformance(found_clustering, trueClustering, points);
                        sb.append("mean: " + measure.getMean(0) + "\n");

                        sb.append(m_measures0[i].getClass().getSimpleName()+" took "+msec+"ms (Mean:"+m_measures0[i].getMeanRunningTime()+")");
                        sb.append("\n");

                    } catch (Exception ex) { ex.printStackTrace(); }
                }
                else{
                    for(int j = 0; j < m_measures0[i].getNumMeasures(); j++){
                        m_measures0[i].addEmptyValue(j);
                    }
                }
            }
        }
        System.out.println("sb.toString() = " + sb.toString());
    }
}
