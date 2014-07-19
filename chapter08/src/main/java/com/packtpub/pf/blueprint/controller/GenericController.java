package com.packtpub.pf.blueprint.controller;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="genericController")
@SessionScoped
public class GenericController implements Serializable{

	private static final long serialVersionUID = -1890125026548028469L;

    @Getter
    @Setter
    private TagCloudModel model;

    @Getter
    @Setter
    private List<String> images;

    @Getter
    @Setter
    private MeterGaugeChartModel meterGaugeModel;

    @PostConstruct
    public void init() {
        model = new DefaultTagCloudModel();
        model.addTag(new DefaultTagCloudItem("Book Printing", 1));
        model.addTag(new DefaultTagCloudItem("Print Now", "location.jsf?faces-redirect=true", 3));
        model.addTag(new DefaultTagCloudItem("Ink Jet ", 2));
        model.addTag(new DefaultTagCloudItem("Dot Matrix", "location.jsf?faces-redirect=true", 5));
        model.addTag(new DefaultTagCloudItem("NextGen Printing", 4));
        model.addTag(new DefaultTagCloudItem("Printing Orders", "location.jsf?faces-redirect=true", 2));
        model.addTag(new DefaultTagCloudItem("Laser Print", 5));
        model.addTag(new DefaultTagCloudItem("Flex Printing",  3));
        model.addTag(new DefaultTagCloudItem("Vinyl Printing", "location.jsf?faces-redirect=true", 4));
        model.addTag(new DefaultTagCloudItem("Request Print", "location.jsf?faces-redirect=true", 1));

        createMeterGaugeModels();

        images = new ArrayList<>();
        images.add("ph1.png");
        images.add("ph2.png");
        images.add("ph3.png");
        images.add("ph4.jpg");
        images.add("ph5.png");

    }

    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>(){{
            add(20);
            add(50);
            add(120);
            add(220);
        }};

        return new MeterGaugeChartModel(140, intervals);
    }

    private void createMeterGaugeModels() {
        meterGaugeModel = initMeterGaugeModel();
        meterGaugeModel.setTitle("Visitor Chart");
        meterGaugeModel.setGaugeLabel("Visitors/h");
        meterGaugeModel.setGaugeLabelPosition("bottom");
        meterGaugeModel.setShowTickLabels(false);
        meterGaugeModel.setLabelHeightAdjust(110);
        meterGaugeModel.setIntervalOuterRadius(130);

    }


    public void onSelect(SelectEvent event) {
        TagCloudItem item = (TagCloudItem) event.getObject();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", item.getLabel());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
