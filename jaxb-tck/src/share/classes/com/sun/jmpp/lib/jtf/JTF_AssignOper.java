/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.jmpp.lib.jtf;
/**
 * <i>Jmpp Test Factory</i>
 * 
 * This class realizes a Java assignment operator (see JLS 15.25). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_AssignOper.java 1.8 02/04/01
 */
public class JTF_AssignOper extends JTF_Slot {
	protected static final String[][] data = {
/* 0 */		{"=", "="},
/* 1 */		{"*=", "*="},
/* 2 */		{"/=", "/="},
/* 3 */		{"%=", "%="},
/* 4 */		{"+=", "+="},
/* 5 */		{"-=", "-="},
/* 6 */		{">>=", "&gt;&gt;="},
/* 7 */		{"<<=", "&lt;&lt;="},
/* 8 */		{">>>=", "&gt;&gt;&gt;="},
/* 9 */		{"&=", "&amp;="},
/* 10 */	{"^=", "^="},
/* 11 */	{"|=", "&#124;="}
	};
		/** the operator is arithmetic */
	public static final String ARITHM      = "1-5";
		/** the operator is not arithmetic */
	public static final String NON_ARITHM  = "0,6-11";
		/** the operator is shift operator */
	public static final String SHIFT       = "6-8";
		/** the operator is not shift operator */
	public static final String NON_SHIFT   = "1-5";
		/** the operator is bitwise operator */
	public static final String BITWISE     = "9-11";
		/** the operator is not bitwise operator */
	public static final String NON_BITWISE = "0-8";
		/** the operator is additive */
	public static final String ADD     = "4";
		/** the operator is not additive */
	public static final String NON_ADD = "0-3,5-11";
		/** the operator is simple */
	public static final String EQU     = "0";
		/** the operator is compound */
	public static final String NON_EQU = "1-11";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isArithm() {
		return sp.parse(ARITHM).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isShift() {
		return sp.parse(SHIFT).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isBitwise() {
		return sp.parse(BITWISE).contains(curVal());
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		res[contexts_total] = data[val_num][0];
		res[contexts_total + 1] = data[val_num][1];
		res0 = res[contexts_total];
	}
		/** @return the operator */
	public String oper() {
		return res0;
	}
		/** @return description of the operator */
	public String desc() {
		return res[contexts_total + 1];
	}
/*------------- Constructors ---------------*/
	public JTF_AssignOper() {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_AssignOper(String combs) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
