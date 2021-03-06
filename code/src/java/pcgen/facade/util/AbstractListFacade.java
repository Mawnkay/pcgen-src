/*
 * AbstractListFacade.java
 * Copyright 2010 Connor Petty <cpmeister@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Created on Apr 25, 2010, 2:46:15 PM
 */
package pcgen.facade.util;

import java.util.AbstractList;
import java.util.Iterator;

import javax.swing.event.EventListenerList;

import pcgen.facade.util.event.ListEvent;
import pcgen.facade.util.event.ListListener;

/**
 *
 * @author Connor Petty <cpmeister@users.sourceforge.net>
 */
public abstract class AbstractListFacade<E> implements ListFacade<E>
{

	protected EventListenerList listenerList = new EventListenerList();
	private Iterable<E> iteratorWrapper = null;

    @Override
	public void addListListener(ListListener<? super E> listener)
	{
		listenerList.add(ListListener.class, listener);
	}

    @Override
	public void removeListListener(ListListener<? super E> listener)
	{
		listenerList.remove(ListListener.class, listener);
	}

    @Override
	public boolean isEmpty()
	{
		return getSize() == 0;
	}

    @Override
	public boolean containsElement(E element)
	{
		for (Object object : this)
		{
			if (object.equals(element))
			{
				return true;
			}
		}
		return false;
	}

    @Override
	public Iterator<E> iterator()
	{
		if (iteratorWrapper == null)
		{
			iteratorWrapper = new AbstractList<E>()
			{

				@Override
				public E get(int index)
				{
					return getElementAt(index);
				}

				@Override
				public int size()
				{
					return getSize();
				}

			};
		}
		return iteratorWrapper.iterator();
	}

	/**
	 * <code>AbstractListFacade</code> subclasses must call this method
	 * <b>after</b> one element is added from the model.
	 * <code>index</code> is the index that has been added.
	 *
	 * @param source the <code>ListFacade</code> that changed, typically "this"
	 * @param element the element that was added
	 * @param index the index of the element that was added.
	 * @see EventListenerList
	 */
	protected void fireElementAdded(Object source, E element, int index)
	{
		Object[] listeners = listenerList.getListenerList();
		ListEvent<E> e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ListListener.class)
			{
				if (e == null)
				{
					e = new ListEvent<E>(source, ListEvent.ELEMENT_ADDED, element, index);
				}
				((ListListener) listeners[i + 1]).elementAdded(e);
			}
		}
	}

	/**
	 * <code>AbstractListFacade</code> subclasses must call this method
	 * <b>after</b> one element is removed from the model.
	 * <code>index</code> is the index that has been removed.
	 *
	 * @param source the <code>ListFacade</code> that changed, typically "this"
	 * @param element the element that was removed
	 * @param index the index of the element that was removed.
	 * @see EventListenerList
	 */
	protected void fireElementRemoved(Object source, E element, int index)
	{
		Object[] listeners = listenerList.getListenerList();
		ListEvent<E> e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ListListener.class)
			{
				if (e == null)
				{
					e = new ListEvent<E>(source, ListEvent.ELEMENT_REMOVED, element, index);
				}
				((ListListener) listeners[i + 1]).elementRemoved(e);
			}
		}
	}

	/**
	 * <code>AbstractListFacade</code> subclasses must call this method
	 * <b>after</b> the contents of this list have greatly changed.
	 *
	 * @param source the <code>ListFacade</code> that changed, typically "this"
	 * @see EventListenerList
	 */
	protected void fireElementsChanged(Object source)
	{
		Object[] listeners = listenerList.getListenerList();
		ListEvent<E> e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ListListener.class)
			{
				if (e == null)
				{
					e = new ListEvent<E>(source);
				}
				((ListListener) listeners[i + 1]).elementsChanged(e);
			}
		}
	}

	/**
	 * <code>AbstractListFacade</code> subclasses must call this method
	 * <b>after</b> an element in the model has had its contents changed.
	 * <code>index</code> is the index that has been modified.
	 *
	 * @param source the <code>ListFacade</code> that changed, typically "this"
	 * @param element the element that was modified
	 * @param index the index of the element that was modified.
	 * @see EventListenerList
	 */
	protected void fireElementModified(Object source, E element, int index)
	{
		Object[] listeners = listenerList.getListenerList();
		ListEvent<E> e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ListListener.class)
			{
				if (e == null)
				{
					e = new ListEvent<E>(source, ListEvent.ELEMENT_MODIFIED, element, index);
				}
				((ListListener) listeners[i + 1]).elementModified(e);
			}
		}
	}

}
