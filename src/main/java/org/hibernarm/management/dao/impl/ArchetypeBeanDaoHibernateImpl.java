package org.hibernarm.management.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ArchetypeBeanDaoHibernateImpl implements ArchetypeBeanDao {
    private static Logger logger=Logger.getLogger(ArchetypeBeanDaoHibernateImpl.class.getName());
	public List<ArchetypeBean> matchProbableByName(String name) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		List<ArchetypeBean> list = query.list();
		return list;
	}

	public List<ArchetypeBean> matchProbableByNamePaging(String name,
			int sequenceOfPage, int perPageAmount) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		query.setFirstResult((sequenceOfPage - 1) * perPageAmount);
		query.setMaxResults(perPageAmount);
		List<ArchetypeBean> list = query.list();
		return list;
	}

	public List<ArchetypeBean> matchProbableByNamePart(String name) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("select new ArchetypeBean(atb.name,atb.description) from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		List<ArchetypeBean> list = query.list();
		return list;
	}

	public List<ArchetypeBean> matchProbableByNamePagingPart(String name,
			int sequenceOfPage, int perPageAmount) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("select new ArchetypeBean(atb.name,atb.description) from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		query.setFirstResult((sequenceOfPage - 1) * perPageAmount);
		query.setMaxResults(perPageAmount);
		List<ArchetypeBean> list = query.list();
		return list;

	}

	public void saveOrUpdate(ArchetypeBean bean) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Query query = session
					.createQuery("from ArchetypeBean as atb where atb.name=:conditionname");
			query.setString("conditionname", bean.getName());
			ArchetypeBean existBean = (ArchetypeBean) query.uniqueResult();
			if (existBean != null) {
				existBean.setContent(bean.getContent());
				existBean.setModifyTime(bean.getModifyTime());
			} else {
				session.save(bean);
			}
			tx.commit();
		} catch (Exception e) {
	        tx.rollback();
	        logger.error("error when save or update archetypeBean:"+e.getMessage());
			throw new RuntimeException("error when save or update archetypeBean"+e.getMessage());    
		}
		// TODO Auto-generated method stub

	}

	public ArchetypeBean selectByName(String name) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ArchetypeBean as atb where atb.name = :conditionname");
		query.setString("conditionname", name);
		ArchetypeBean archetypeBean = (ArchetypeBean) query.uniqueResult();
		return archetypeBean;
	}

	public List<ArchetypeBean> selectAll() {
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery("from ArchetypeBean");
		List<ArchetypeBean> archetypes = query.list();
		return archetypes;
	}

}
