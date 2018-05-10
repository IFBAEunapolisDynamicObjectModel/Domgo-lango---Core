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

import com.unity.domgo.model.InstanciaEntidade;
import com.unity.domgo.model.Entidade;
import com.unity.domgo.model.InstanciaAtributo;

/**
 * Backing bean for InstanciaEntidade entities.
 * <p/>
 * This class provides CRUD functionality for all InstanciaEntidade entities. It
 * focuses purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt>
 * for state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class InstanciaEntidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving InstanciaEntidade entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private InstanciaEntidade instanciaEntidade;

	public InstanciaEntidade getInstanciaEntidade() {
		return this.instanciaEntidade;
	}

	public void setInstanciaEntidade(InstanciaEntidade instanciaEntidade) {
		this.instanciaEntidade = instanciaEntidade;
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
			this.instanciaEntidade = this.example;
		} else {
			this.instanciaEntidade = findById(getId());
		}
	}

	public InstanciaEntidade findById(Long id) {

		return this.entityManager.find(InstanciaEntidade.class, id);
	}

	/*
	 * Support updating and deleting InstanciaEntidade entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.instanciaEntidade);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.instanciaEntidade);
				return "view?faces-redirect=true&id="
						+ this.instanciaEntidade.getId();
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
			InstanciaEntidade deletableEntity = findById(getId());

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
	 * Support searching InstanciaEntidade entities with pagination
	 */

	private int page;
	private long count;
	private List<InstanciaEntidade> pageItems;

	private InstanciaEntidade example = new InstanciaEntidade();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public InstanciaEntidade getExample() {
		return this.example;
	}

	public void setExample(InstanciaEntidade example) {
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
		Root<InstanciaEntidade> root = countCriteria
				.from(InstanciaEntidade.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<InstanciaEntidade> criteria = builder
				.createQuery(InstanciaEntidade.class);
		root = criteria.from(InstanciaEntidade.class);
		TypedQuery<InstanciaEntidade> query = this.entityManager
				.createQuery(criteria.select(root).where(
						getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<InstanciaEntidade> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		Entidade entidades = this.example.getEntidades();
		if (entidades != null) {
			predicatesList.add(builder.equal(root.get("entidades"), entidades));
		}
		InstanciaAtributo instanciaAtributo = this.example
				.getInstanciaAtributo();
		if (instanciaAtributo != null) {
			predicatesList.add(builder.equal(root.get("instanciaAtributo"),
					instanciaAtributo));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<InstanciaEntidade> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back InstanciaEntidade entities (e.g. from
	 * inside an HtmlSelectOneMenu)
	 */

	public List<InstanciaEntidade> getAll() {

		CriteriaQuery<InstanciaEntidade> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(InstanciaEntidade.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(InstanciaEntidade.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final InstanciaEntidadeBean ejbProxy = this.sessionContext
				.getBusinessObject(InstanciaEntidadeBean.class);

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

				return String.valueOf(((InstanciaEntidade) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private InstanciaEntidade add = new InstanciaEntidade();

	public InstanciaEntidade getAdd() {
		return this.add;
	}

	public InstanciaEntidade getAdded() {
		InstanciaEntidade added = this.add;
		this.add = new InstanciaEntidade();
		return added;
	}
}
