package Util.Delivery;


import Database.DeliveryManagement;

import java.util.ArrayList;

/**
 * Created by Evdal on 14.03.2016.
 */
public class CreateDeliveryRoute {

    /**
     * List of orders ready for delivery.
     * @param startAdress Start address.
     * @param adressNames Address names.
     * @return
     */
/*
    public static ArrayList<String> deliveryListForTest(String startAdress, ArrayList<String> adressNames) {
        ArrayList<String> out = null;
        try {
            TravelingSalesman tsp = new TravelingSalesman(startAdress);
            //  ArrayList<String> adressNames = dm.getAdressReady();
            System.out.println(adressNames);
            ArrayList<double[]> positions = tsp.createPositionsArrayShortened(adressNames);
            tsp.bruteForceFindBestRoute(new ArrayList<>(), positions);
            out = tsp.positionsToAdresses(tsp.getBestRoute(), adressNames);
        } catch (Exception e) {

        }
        return out;
    }*/


    /**
     * List of orders for table.
     * @param startAdress Start address.
     * @param adressNames Address names.
     * @return
     */
    public static ArrayList<Object[]> orderListForTable(String startAdress, ArrayList<String> adressNames, ArrayList<Integer> orderIds){
        ArrayList<Object[]> out;
        DeliveryManagement dev = new DeliveryManagement();
        try {
            TravelingSalesman tsp = new TravelingSalesman(startAdress);
            ArrayList<double[]> positions = tsp.createPositionsArray(adressNames);
            tsp.bruteForceFindBestRoute(new ArrayList<>(), positions);
            ArrayList<Integer> tmp = tsp.positionsToAdresses(tsp.getBestRoute(), orderIds);
            ArrayList<double[]> br = tsp.getBestRoute();
            out = dev.getDeliveryInfo(tmp);

        }
        catch (Exception e){
            System.err.println("Issue with creating delivery route.");
            return null;
        }

        return out;
    }


    /**
     * Calculates the fastest route for the driver.
     * @param startAdress Start address.
     * @param adressNames Address names.
     * @return Returns the addresses as latitudes and longitudes instead of actual addresses.
     */
    public static ArrayList<double[]> UseReadyOrdersLatLng(String startAdress, ArrayList<String> adressNames){

        ArrayList<double[]> out;
        try {
            TravelingSalesman tsp = new TravelingSalesman(startAdress);
            DeliveryManagement dm = new DeliveryManagement();
            ArrayList<double[]> positions = tsp.createPositionsArray(adressNames);
            tsp.bruteForceFindBestRoute(new ArrayList<>(), positions);
            out = tsp.getBestRoute();
        }
        catch (Exception e){
            System.err.println("Issue with creating delivery route.");
            return null;
        }
        return out;
    }
}
