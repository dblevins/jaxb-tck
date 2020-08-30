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

package com.sun.tgxml.tjtf.processors.taghandlers.impl;
import java.io.IOException;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.impl.ConstantsImpl;

import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * InlineSupportClass_TH - The tag-handler for a Lib tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    InlineSupportClass_TH 
 * ============================================================================================ 
 */ 
public class InlineSupportClass_TH extends TagHandlerImpl  {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 

    
   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 


    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   InlineSupportClass_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public InlineSupportClass_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_inlinesupportclass;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void startTag(org.xml.sax.Attributes attrs) throws SAXException {
	super.startTag(attrs);
	//	try {
	    String lang = ConstantsImpl.ctStr_attr_langtype_enum_java; // default value
	    String targetname = null;
	    String ID = null;
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the langtype
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_classid)) {
			ID = attrs.getValue (i);
			// validateClassID(lang);
		    }  else if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_targetname)) {
			targetname = attrs.getValue (i);
			// validateTargetName(lang);
		    } else  if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_sourcelang)) {
			lang = attrs.getValue (i);
			// validateLangType(lang);
		    }

		    // unknown attribute
		    else 
			// Unknown spec attribute
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownSpecAttr", attrs.getQName (i)));
		}

		Stack testItemStack = getParserHandler().getStack();
		Object tco = testItemStack.peek();
		if (! (tco instanceof CodeSet))
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_codeset));

		CodeSet cs = (CodeSet) tco;
		ArrayList supportClasses = cs.getSupportClasses();
		if (supportClasses == null) {
		    supportClasses = new ArrayList();
		    cs.setSupportClasses(supportClasses);
		}

		InlineSupportClass isc = CodeFactory.createInlineSupportClass();
		isc.setSourceLang(lang);
		if (targetname != null)
		    isc.setTargetName(targetname);
		if (ID != null && ! ID.equals(""))				
		    isc.setClassID(ID);
		supportClasses.add(isc);

		// push the support class to the top of the stack
		testItemStack.push(isc);

	    }
    }
     

     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = getParserHandler().getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof InlineSupportClass))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
	}
    }

     
 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
          
  /**
    *   emit a tags attributes (general function).
    *  <p>
    * @see #endTag
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof InlineSupportClass))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"InlineSupportClass", tdObject.getClass().getName()));

	InlineSupportClass sc = (InlineSupportClass) tdObject;

	String classID = sc.getClassID();
	if (classID != null && ! classID.equals(""))
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_classid, classID);

	if (sc.isExport()) {
	    String targetname = sc.getTargetName();
	    if (targetname != null && ! targetname.equals(""))
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_targetname, targetname);
	}
	
	String lang = sc.getSourceLang();
	if (lang != null && ! lang.equals("")) {
	    // Don't output the lang if it is equal to the default (java)
	    if (lang != ConstantsImpl.ctStr_attr_langtype_enum_java) 
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_sourcelang, lang);
	}

    }

          
          
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof InlineSupportClass))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"InlineSupportClass", tdObject.getClass().getName()));

	InlineSupportClass sc = (InlineSupportClass) tdObject;

	m_EmitterHandler.emit(TagsImpl.ctStr_tag_codesource, sc.getSource());

    }

}
