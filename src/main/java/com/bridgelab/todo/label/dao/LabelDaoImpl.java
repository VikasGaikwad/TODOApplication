/**
 * 
 */
package com.bridgelab.todo.label.dao;

import java.util.List;import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public class LabelDaoImpl implements ILabelDao{

	@Autowired

	private SessionFactory sessionFactory;
	@Override
	public void addLabel(Label label) {
		Session session=sessionFactory.getCurrentSession();
		
		session.save(label);		
	}
	@Override
	public List<Label> isLabelPresent(String label) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Label.class);
		criteria.add(Restrictions.eq("labelId", "labelId"));
		List<Label> labelList=criteria.list();
		return labelList;
	}
	@Override
	public List<Label> readLabel(User user) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Label.class);
		criteria.add(Restrictions.eq("user", user));
		@SuppressWarnings("unchecked")
		List<Label> label=criteria.list();
		for (Label label2 : label) {
			System.out.println("labelId:-"+label2.getLabelId());
		}
		return label;

	}
	@Override
	public int deleteLabels(Label label, int id) {
		Session session=sessionFactory.getCurrentSession();
		String hql="delete from Label label where label.labelId=:labelId";
		Query query=session.createQuery(hql);
		query.setParameter("labelId", label);
		int row=query.executeUpdate();

		return row;
	}

}
