package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.packt.pfblueprints.dao.AccountsDAO;
import com.packt.pfblueprints.model.AccountSummary;

@ManagedBean
@ViewScoped
public class AccountSummaryController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<AccountSummary> accountsInfo = new ArrayList<AccountSummary>();

	private AccountSummary accountobj = new AccountSummary();
	AccountsDAO dao = new AccountsDAO();
	private LazyDataModel<AccountSummary> lazyAccSummaryDataModel;
	private Boolean direction = false;
	private String directionSupport = "ltr";

	@PostConstruct
	public void init() {

		lazyAccSummaryDataModel = new LazyAccountSummaryDataModel() {
			@Override
			public List<AccountSummary> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				String sortOrderValue = null;
				if (sortField == null) {
					sortField = "investorName";
				}
				if (sortOrder.ASCENDING.equals("A")) {
					sortOrderValue = "ASC";
				} else if (sortOrder.DESCENDING.equals("D")) {
					sortOrderValue = "DSC";
				} else {
					sortOrderValue = "default";
				}

				accountsInfo = dao.getAllAccounts(first, pageSize, sortField,
						sortOrderValue, filters);
				this.setRowCount(20);
				return accountsInfo;
			}
		};
	}

	public void addRTLsupport() {
		if (direction) {
			directionSupport = "rtl";
		}else{
			directionSupport = "ltr";
		}
	}

	public LazyDataModel<AccountSummary> getLazyAccSummaryDataModel() {
		return lazyAccSummaryDataModel;
	}

	public void setLazyAccSummaryDataModel(
			LazyDataModel<AccountSummary> lazyAccSummaryDataModel) {
		this.lazyAccSummaryDataModel = lazyAccSummaryDataModel;
	}

	public AccountSummary getAccountobj() {
		return accountobj;
	}

	public void setAccountobj(AccountSummary accountobj) {
		this.accountobj = accountobj;
	}

	public Boolean getDirection() {
		return direction;
	}

	public void setDirection(Boolean direction) {
		this.direction = direction;
	}

	public String getDirectionSupport() {
		return directionSupport;
	}

	public void setDirectionSupport(String directionSupport) {
		this.directionSupport = directionSupport;
	}

}
