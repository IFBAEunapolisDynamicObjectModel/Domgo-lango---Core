package com.unity.domgo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.unity.domgo.model.InstanciaSistema;
import com.unity.domgo.model.Sistema;

/**
 * Backing bean for InstanciaSistema entities.
 * <p/>
 * This class provides CRUD functionality for all InstanciaSistema entities. It
 * focuses purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt>
 * for state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class InstanciaSistemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving InstanciaSistema entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private InstanciaSistema instanciaSistema;

	public InstanciaSistema getInstanciaSistema() {
		return this.instanciaSistema;
	}

	public void setInstanciaSistema(InstanciaSistema instanciaSistema) {
		this.instanciaSistema = instanciaSistema;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(unitName = "Domgo-persistence-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		this.conversation.setTimeout(1800000L);
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversation.setTimeout(1800000L);
		}

		if (this.id == null) {
			this.instanciaSistema = this.example;
		} else {
			this.instanciaSistema = findById(getId());
		}
	}

	public InstanciaSistema findById(Long id) {

		return this.entityManager.find(InstanciaSistema.class, id);
	}

	/*
	 * Support updating and deleting InstanciaSistema entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.instanciaSistema);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.instanciaSistema);
				return "view?faces-redirect=true&id="
						+ this.instanciaSistema.getId();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			InstanciaSistema deletableEntity = findById(getId());

			this.entityManager.remove(deletableEntity);
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching InstanciaSistema entities with pagination
	 */

	private int page;
	private long count;
	private List<InstanciaSistema> pageItems;

	private InstanciaSistema example = new InstanciaSistema();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public InstanciaSistema getExample() {
		return this.example;
	}

	public void setExample(InstanciaSistema example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<InstanciaSistema> root = countCriteria
				.from(InstanciaSistema.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<InstanciaSistema> criteria = builder
				.createQuery(InstanciaSistema.class);
		root = criteria.from(InstanciaSistema.class);
		TypedQuery<InstanciaSistema> query = this.entityManager
				.createQuery(criteria.select(root).where(
						getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<InstanciaSistema> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		Sistema sistema = this.example.getSistema();
		if (sistema != null) {
			predicatesList.add(builder.equal(root.get("sistema"), sistema));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<InstanciaSistema> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back InstanciaSistema entities (e.g. from
	 * inside an HtmlSelectOneMenu)
	 */

	public List<InstanciaSistema> getAll() {

		CriteriaQuery<InstanciaSistema> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(InstanciaSistema.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(InstanciaSistema.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final InstanciaSistemaBean ejbProxy = this.sessionContext
				.getBusinessObject(InstanciaSistemaBean.class);

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ejbProxy.findById(Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((InstanciaSistema) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private InstanciaSistema add = new InstanciaSistema();

	public InstanciaSistema getAdd() {
		return this.add;
	}

	public InstanciaSistema getAdded() {
		InstanciaSistema added = this.add;
		this.add = new InstanciaSistema();
		return added;
	}
}
