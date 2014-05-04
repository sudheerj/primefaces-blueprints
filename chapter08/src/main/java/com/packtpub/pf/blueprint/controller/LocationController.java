package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.Location;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 3/30/14
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class LocationController implements Serializable {

    private static final Logger log = Logger.getLogger(LocationController.class.getName());

    @PostConstruct
    public void init() {
        log.info ("Current Lati & longi : $currentLati  &  $currentLong" );
        populateLocationCoordinates();
    }

    DAOService ds = new DAOService();

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        location = ds.getLocationByFranchiseeNo((Long) marker.getData());
    }

    private void populateLocationCoordinates(){
        List<Location> locations = ds.getAllLocations();
        if(locations == null) {
            return;
        }
        centerMap = "";
        locationMap = new DefaultMapModel();
        location = null;

        log.info("Too MAny Map Locations: "+locations.size());

        for(Location loc: locations){
            LatLng ll = new LatLng( loc.getLatitude(), loc.getLongitude());
            locationMap.addOverlay(new Marker(ll, loc.getStreet1(), loc.getFranchiseeNo()));
        }
    }

    @Getter @Setter Location location;
    @Getter @Setter String centerMap;

    @Getter @Setter MapModel locationMap;

    @Getter @Setter Marker marker;

    @Getter @Setter Location newLocation;

    public void prepareNewLocation(){
        newLocation = new Location();

    }

    public void addNewLocation(){
        ds.addOrUpdateEntity(newLocation);
        prepareNewLocation();
    }


}
