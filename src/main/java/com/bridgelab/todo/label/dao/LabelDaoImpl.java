/**
 * 
 */
package com.bridgelab.todo.label.dao;

import java.util.List;import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
@Repository
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
		Session session=sessionFactory.openSession();
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
	@Override
	public void addLabelOnNote(Notes note) {


		Session session = sessionFactory.getCurrentSession();
		/*Query query = (Query) session.createQuery("insert into label_note (note)");
		//Query query = (Query) session.createQuery("insert into label_note l_note where l_note.noteId=:noteId,l_note.labelId=:labelId");
		//session.save(noteId,id);
		query.executeUpdate();*/
		session.save(note);
	}
	@Override
	public Label getLabelById(int labelId) {
		Session session;
		String sqlQuery="from Label where labelId=:labelId";
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Label.class);
		criteria.add(Restrictions.eq("labelId", labelId));
		Label label = (Label) criteria.uniqueResult();
		return label;


	}
	@Override
	public void deleteLabelFromNotes(int labelId, int noteId) {
		Session session=sessionFactory.getCurrentSession();
		String hql="delete from label_note  where noteId=:noteId,labelId=:labelId";
		Query query=session.createQuery(hql);
		query.setParameter("noteId", noteId);
		query.setParameter("labelId", labelId);

		query.executeUpdate();
	}


}
