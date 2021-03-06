package pl.edu.agh.ftj.datamaining;

import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 * Klasa odpowiedzialna za komunikacje z biblioteka Weki
 * @author Bartłomiej Wojas, Adrian Kremblewski
 * @version 0.9.1
 */
public class WekaAlgorithm {
    /**
     * Obiekt przechowujący dane do analizy.
     */
    private Instances data = null;

    /**
     * Typ algorytmu jaki ma zostac uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
     */
    private int algorithmType = 0;

    /**
     * Tablica parametrów wg. których ma funkcjonować algorytm.
     */
    private String[] options;

    /**
     * Obiekt zawierajace dane zwracajane przez Weke
     */
    private WekaAnswer wekaAnswer = new WekaAnswer();

    /**
     * Tablica z nazwami udostępnianych algorytmów
     */
    private String[] algorithms = {
        "SimpleKMeans",
        "EM",
        "HierarchicalClusterer",
        "Cobweb"
    };

    /**
     * Metoda zwracająca obiekt z danymi będącymi wynikiem działania algorytmu.
     * @return Obiekt z danymi.
     */
    public WekaAnswer getData() {
        return wekaAnswer;
    }

    /**
     * Metoda rozpoczynająca proces przetwarzania. Inicjuje i uruchamia odpowiednie algorytmy.  
     */
    public void run() {
        switch(algorithmType) {
            case 1: runSimpleKMeans();
                    break;
            case 2: runEM();
                    break;
            case 3: runHierarchicalClusterer();
                    break;
            case 4: runCobweb();
                    break;
            default: wekaAnswer.setAlgorithmName("Unknown");
        }
    }

    /**
     * Metoda ustawiająca typ algorytmu jaki ma zostać użyty
     * @param algorithmType Typ algorytmu jaki ma zostac uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
     */
    public void setAlgorithmType(int algorithmType) {
        this.algorithmType = algorithmType;
    }

    /**
     * Ustawia dane, które mają zostać przetworzone.
     * @param data Dane, które mają zostać poddane przetworzeniu.
     */
    public void setData(Instances data) {
        this.data = data;
    }

   /**
    * Ustawia opcje dla algorytmu.
    * Opis parametrów oraz algorytmów znajduje się na TracWiki projektu.
    * URL: http://prgzsp.ftj.agh.edu.pl/trac/P3-DataMining/wiki/Algorytmy
    * @param options Opcje wg. których ma działać algorytm.
    */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
     * Metoda zwracająca dostępne nazwy algorytmów.
     * @return Tablica z nazwami algorytmów.
     */
    public String[] getAlgorithms() {
        return algorithms;
    }

    /**
     * Uruchamia algorytm SimpleKMeans.
     */
    private void runSimpleKMeans() {
        wekaAnswer.setAlgorithmType(1);
        wekaAnswer.setAlgorithmName(algorithms[0]);
        SimpleKMeans skm = new SimpleKMeans();

        try {
            skm.setOptions(options);
            skm.buildClusterer(data);
            //rozpoczęcie budowania obiektu z danymi
            wekaAnswer.setAssignments(skm.getAssignments());
            wekaAnswer.setCapabilities(skm.getCapabilities());
            wekaAnswer.setClusterCentroids(skm.getClusterCentroids());
            wekaAnswer.setClusterNominalCounts(skm.getClusterNominalCounts());
            wekaAnswer.setClusterSizes(skm.getClusterSizes());
            wekaAnswer.setClusterStandardDevs(skm.getClusterStandardDevs());
            wekaAnswer.setDistanceFunction(skm.getDistanceFunction());
            wekaAnswer.setMaxIterations(skm.getMaxIterations());
            wekaAnswer.setNumClusters(skm.getNumClusters());
            wekaAnswer.setOptions(options);
            wekaAnswer.setRevision(skm.getRevision());
            wekaAnswer.setSquaredError(skm.getSquaredError());
            wekaAnswer.setNumberOfClusters(skm.numberOfClusters());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uruchamia algorytm EM.
     */
    private void runEM() {
        wekaAnswer.setAlgorithmName(algorithms[1]);
        EM em = new EM();
        
        try {
            em.setOptions(options);
            em.buildClusterer(data);
            //rozpoczęcie budowania obiektu z danymi
            wekaAnswer.setClusterPriors(em.getClusterPriors());
            wekaAnswer.setClusterModelsNumericAtts(em.getClusterModelsNumericAtts());
            wekaAnswer.setMinStdDev(em.getMinStdDev());
            wekaAnswer.setCapabilities(em.getCapabilities());
            wekaAnswer.setMaxIterations(em.getMaxIterations());
            wekaAnswer.setNumClusters(em.getNumClusters());
            wekaAnswer.setOptions(em.getOptions());
            wekaAnswer.setRevision(em.getRevision());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uruchamia algorytm HierarchicalClusterer.
     */
    private void runHierarchicalClusterer() {
        wekaAnswer.setAlgorithmName(algorithms[2]);
        HierarchicalClusterer hc = new HierarchicalClusterer();
        
        try {
            hc.setOptions(options);
            hc.buildClusterer(data);
            //rozpoczęcie budowania obiektu z danymi
            wekaAnswer.setCapabilities(hc.getCapabilities());
            wekaAnswer.setDistanceFunction(hc.getDistanceFunction());
            wekaAnswer.setLinkType(hc.getLinkType());
            wekaAnswer.setNumClusters(hc.getNumClusters());
            wekaAnswer.setOptions(options);
            wekaAnswer.setPrintNewick(hc.getPrintNewick());
            wekaAnswer.setRevision(hc.getRevision());
            wekaAnswer.setGraph(hc.graph());
            wekaAnswer.setGraphType(hc.graphType());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uruchamia algorytm Cobweb.
     */
    private void runCobweb() {
        wekaAnswer.setAlgorithmName(algorithms[3]);
        Cobweb cw = new Cobweb();
        
        try {
            cw.setOptions(options);
            cw.buildClusterer(data);
            //rozpoczęcie budowania obiektu z danymi
            wekaAnswer.setAcuity(cw.getAcuity());
            wekaAnswer.setCapabilities(cw.getCapabilities());
            wekaAnswer.setCutoff(cw.getCutoff());
            wekaAnswer.setOptions(options);
            wekaAnswer.setRevision(cw.getRevision());
            wekaAnswer.setGraph(cw.graph());
            wekaAnswer.setGraphType(cw.graphType());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
