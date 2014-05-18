package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.UserPost;
import org.primefaces.model.LazyDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by psramkumar on 5/18/14.
 */
public class LazyDataUserPostModel extends LazyDataModel<UserPost> {

    private List<UserPost> datasource;

    public LazyDataUserPostModel(List<UserPost> datasource) {
        this.datasource = datasource;
    }

    @Override
    public UserPost getRowData(String rowKey) {
        for (UserPost car : datasource) {
            if (car.getId().toString().equals(rowKey)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(UserPost car) {
        return car.getId();
    }

    @Override
    public java.util.List<UserPost> load(int first, int pageSize, java.lang.String sortField, org.primefaces.model.SortOrder sortOrder, java.util.Map<java.lang.String,java.lang.Object> filters) {
        List<UserPost> data = new ArrayList<UserPost>();
        for (UserPost car : datasource) {
            boolean match = true;
            for(String filterProperty: filters.keySet()){
                try {
                    String filterValue = filters.get(filterProperty).toString();

                    String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

                    if (filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (Exception e) {
                    match = false;
                }
            }

            if (match) {
                data.add(car);
            }
        }

//sort
        /*if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }*/

//rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

//paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}