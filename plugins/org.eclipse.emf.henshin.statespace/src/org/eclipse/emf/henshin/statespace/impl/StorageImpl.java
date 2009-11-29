/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.henshin.statespace.impl;

import java.util.Arrays;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.henshin.statespace.Storage;

/**
 * @generated
 */
public class StorageImpl extends MinimalEObjectImpl implements Storage {
	
	/**
	 * @generated NOT
	 */
	public static int[] EMPTY_DATA = new int[0];
	
	/**
	 * @generated NOT
	 */
	public int[] getData() {
		return (data!=null) ? data : EMPTY_DATA;
	}

	/**
	 * @generated NOT
	 */
	public int getData(int index) {
		return (data==null || data.length<=index) ? 0 : data[index];
	}

	/**
	 * @generated NOT
	 */
	public int[] getData(int beginIndex, int endIndex) {
		if (data==null) {
			return new int[endIndex-beginIndex];
		} else {
			int[] result = new int[endIndex-beginIndex];
			int end = Math.min(endIndex, data.length);
			for (int i=beginIndex; i<end; i++) {
				result[i-beginIndex] = data[i];
			}
			return result;	
		}
	}

	/**
	 * @generated NOT
	 */
	public void setData(int index, int value) {
		
		// Determine the new size of the data array:
		int size = minSize(data);
		if (value!=0 && index>=size) size=index+1;
		
		// Set the new data:
		if (size==0) {
			setData(null);
		} else {
			int[] newData = resized(size);
			if (index<size) newData[index] = value;
			setData(newData);
		}
		
	}

	/**
	 * @generated NOT
	 */
	public void setData(int beginIndex, int[] value) {

		// Determine the new size of the data array:
		int size = Math.max(beginIndex + minSize(value), minSize(data));
		
		// Set the new data:
		if (size==0) {
			setData(null);
		} else {
			int[] newData = resized(size);
			for (int i=beginIndex; i<size; i++) {
				newData[i] = value[i-beginIndex];
			}
			setData(newData);
		}
		
	}
	
	/*
	 * Private helper for generating resized copies
	 * of the data storage array.
	 */
	private int[] resized(int size) {
		if (data==null) return new int[size];
		else return Arrays.copyOf(data, size);
	}
	
	/*
	 * Private helper for determining the minimum size of
	 * a data storage array.
	 */
	private int minSize(int[] data) {
		if (data==null) return 0;
		for (int i=data.length-1; i>=0; i--) {
			if (data[i]!=0) return i+1;
		}
		return 0;
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The default value of the '{@link #getData() <em>Data</em>}' attribute.
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected static final int[] DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' attribute.
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected int[] data = DATA_EDEFAULT;

	/**
	 * @generated
	 */
	protected StorageImpl() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StateSpacePackageImpl.Literals.STORAGE;
	}

	/**
	 * @generated
	 */
	public void setData(int[] newData) {
		int[] oldData = data;
		data = newData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateSpacePackageImpl.STORAGE__DATA, oldData, data));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateSpacePackageImpl.STORAGE__DATA:
				return getData();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StateSpacePackageImpl.STORAGE__DATA:
				setData((int[])newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StateSpacePackageImpl.STORAGE__DATA:
				setData(DATA_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StateSpacePackageImpl.STORAGE__DATA:
				return DATA_EDEFAULT == null ? data != null : !DATA_EDEFAULT.equals(data);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (data: ");
		result.append(data);
		result.append(')');
		return result.toString();
	}

} //StorageImpl